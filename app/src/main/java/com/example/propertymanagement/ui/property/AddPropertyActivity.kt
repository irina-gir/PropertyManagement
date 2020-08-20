package com.example.propertymanagement.ui.property

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.api.ApiClient
import com.example.propertymanagement.databinding.ActivityAddPropertyBinding
import com.example.propertymanagement.helpers.SessionManager
import com.example.propertymanagement.helpers.toast
import com.example.propertymanagement.helpers.toolbar
import com.example.propertymanagement.models.UploadResponse
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_add_property.*
import kotlinx.android.synthetic.main.activity_add_property.view.*
import kotlinx.android.synthetic.main.activity_add_property.view.image_property
import kotlinx.android.synthetic.main.bottom_sheet.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.jar.Manifest

class AddPropertyActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var propertyViewModel: AddPropertyViewModel
    private lateinit var binding: ActivityAddPropertyBinding
    private lateinit var sheetDialog: BottomSheetDialog
    private val CAMERA_REQUEST_CODE = 102
    private val GALLERY_REQUEST_CODE = 103
    private lateinit var location: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_property)

        propertyViewModel = ViewModelProvider(this,
            AddPropertyViewModelFactory(AddPropertyRepository(ApiClient.getApiEndPoint())))
            .get(AddPropertyViewModel::class.java)

        binding.viewModel = propertyViewModel
        SessionManager.init(this)

        this.toolbar("Add Property")

        init(binding.root)
    }

    private fun init(root: View) {
        observerData()
        root.image_property.visibility = View.GONE
        root.button_add_picture.setOnClickListener(this)
        root.button_save_property.setOnClickListener(this)
    }

    private fun observerData() {
        propertyViewModel.getAddPropertyRepObserver().observe(this, Observer {
            if(it.isSuccessful){
                startActivity(Intent(this, ActivityProperties::class.java))
            }
        })
    }

    private fun onMultiplePermissions() {
        Dexter.withActivity(this)
            .withPermissions(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object: MultiplePermissionsListener{
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    //check if all permissions are granted
                    if(report!!.areAllPermissionsGranted()){
                    }
                    //check for permanent
                    if(report.isAnyPermissionPermanentlyDenied){
                        toast("Permission denied permanently")
                        showDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token!!.continuePermissionRequest()
                }
            })
            .onSameThread()
            .check()
    }

    private fun showDialog() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Permission needed")
        builder.setMessage("In order for you to be able to upload the picture, we need your permission.")
        builder.setPositiveButton("Go to Settings", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                dialog?.dismiss()
                openSettings()
            }
        })
        builder.setNegativeButton("Cancel", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                dialog?.dismiss()
            }
        })
        builder.show()
    }

    private fun openSettings() {
        var intentSettings = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        var uri = Uri.fromParts("package", packageName, null )
        startActivityForResult(intentSettings, CAMERA_REQUEST_CODE)
    }

    private fun openGallery() {
        var intentGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intentGallery, GALLERY_REQUEST_CODE)
    }

    private fun openCamera() {
        var intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intentCamera, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_CODE){
            var image = data!!.getParcelableExtra<Bitmap>("data")
            image_property.visibility = View.VISIBLE
            image_property.setImageBitmap(image)
            var uri = getImageUri(this, image!!)
            var path = getRealPathFromURI(uri)
            uploadImage(path!!)
        }
        else if(requestCode == GALLERY_REQUEST_CODE){
            var contentUri = data!!.data
            var image = MediaStore.Images.Media.getBitmap(this.contentResolver, contentUri)
            image_property.visibility = View.VISIBLE
            image_property.setImageBitmap(image)
            var uri = getImageUri(this, image)
            var path = getRealPathFromURI(uri)
            uploadImage(path!!)
        }
    }

    //get URI from bitmap
    fun getImageUri(context: Context, image: Bitmap): Uri?{
        val bytes = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, image, "Title", null)
        return Uri.parse(path)
    }

    //get actual path
    fun getRealPathFromURI(uri: Uri?): String?{
        val cursor: Cursor? = contentResolver.query(uri!!, null, null, null, null)
        cursor!!.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        return cursor.getString(idx)
    }

    //using retrofit upload image
    fun uploadImage(path: String){
        var file = File(path)
        var apiService = ApiClient.getApiEndPoint()

        var requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file)
        var body = MultipartBody.Part.createFormData("image", file.name, requestFile)

        var call = apiService.postImageProperty(body)
        call.enqueue(object: Callback<UploadResponse>{
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                toast(t.message.toString())
            }

            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                var uploadResponse = response.body()?.data
                location = uploadResponse?.location!!
                toast("Uploaded")
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button_add_picture ->{
                onMultiplePermissions()
                sheetDialog = BottomSheetDialog(this, R.style.Theme_Design_BottomSheetDialog)

                var buttonSheetView = layoutInflater.inflate(R.layout.bottom_sheet, null)
                sheetDialog.setContentView(buttonSheetView)
                sheetDialog.show()

                buttonSheetView.button_camera.setOnClickListener {
                    openCamera()
                }
                buttonSheetView.button_gallery.setOnClickListener {
                    openGallery()
                }
            }
            R.id.button_save_property ->{
                propertyViewModel.onButtonSaveProperty(location)
            }
        }
    }
}