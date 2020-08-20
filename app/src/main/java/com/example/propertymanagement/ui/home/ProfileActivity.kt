package com.example.propertymanagement.ui.home

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.propertymanagement.R
import com.example.propertymanagement.helpers.SessionManager
import com.example.propertymanagement.helpers.toast
import com.example.propertymanagement.helpers.toolbar
import com.example.propertymanagement.ui.auth.FirstPage
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_add_property.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.bottom_sheet.view.*

class ProfileActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var sheetDialog: BottomSheetDialog
    private val CAMERA_REQUEST_CODE = 104
    private val GALLERY_REQUEST_CODE = 105

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        this.toolbar("Profile")

        init()
    }

    private fun init() {
        SessionManager.init(this)
        var user = SessionManager.getUser()

        tv_name.text = user.name
        tv_email.text = user.email
        tv_account_type.text = user.type

        button_logout.setOnClickListener(this)
        button_home_page.setOnClickListener(this)
        button_edit_picture.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button_logout ->{
                var snackbar = Snackbar.make(layout, "Leaving Already?", Snackbar.LENGTH_LONG)
                    .setAction("Yes"){
                        SessionManager.logout()
                        startActivity(Intent(this, FirstPage::class.java))
                    }
                snackbar.show()
            }
            R.id.button_home_page ->{
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.button_edit_picture -> {
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
        }
    }

    private fun openGallery() {
        var intentGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intentGallery, GALLERY_REQUEST_CODE)
    }

    private fun openCamera() {
        var intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intentCamera, CAMERA_REQUEST_CODE)
    }

    private fun onMultiplePermissions() {
        Dexter.withActivity(this)
            .withPermissions(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object: MultiplePermissionsListener {
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
        builder.setPositiveButton("Go to Settings"
        ) { dialog, p1 ->
            dialog?.dismiss()
            openSettings()
        }
        builder.setNegativeButton("Cancel"
        ) { dialog, p1 -> dialog?.dismiss() }
        builder.show()
    }

    private fun openSettings() {
        var intentSettings = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        var uri = Uri.fromParts("package", packageName, null )
        startActivityForResult(intentSettings, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_CODE){
            var image = data!!.getParcelableExtra<Bitmap>("data")
            image_user1.setImageBitmap(image)
        }
        else if(requestCode == GALLERY_REQUEST_CODE){
            var contentUri = data!!.data
            var image = MediaStore.Images.Media.getBitmap(this.contentResolver, contentUri)
            image_user1.setImageBitmap(image)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }
}