package com.example.propertymanagement.ui.property

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagement.databinding.RowAdapterPropertyBinding
import com.example.propertymanagement.models.Property
import com.example.propertymanagement.models.PropertyResponse

class AdapterAddProperty (private var context: Context):
        RecyclerView.Adapter<AdapterAddProperty.MyViewHolder>(){

    private var propertyList = ArrayList<Property>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowAdapterPropertyBinding.inflate(LayoutInflater.from(context))
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return propertyList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var property = propertyList[position]
        holder.bind(property)
    }

    inner class MyViewHolder(private val binding: RowAdapterPropertyBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(property: Property){
            binding.property = property
            binding.executePendingBindings()
            binding.adapter = this@AdapterAddProperty
        }
    }

    fun setData(list: ArrayList<Property>){
        propertyList = list
        notifyDataSetChanged()
    }
}