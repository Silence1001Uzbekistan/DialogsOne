package com.example.dialogsone.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dialogsone.CLASS.SClass
import com.example.dialogsone.R

class SnackbarAdapter(var snackbarList: ArrayList<SClass>,var onMyItemClickListener: OnMyItemClickListener) :
    RecyclerView.Adapter<SnackbarAdapter.Vh>() {

    inner class Vh(var itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(snackbarClass: SClass, position: Int) {

            itemView.findViewById<ImageView>(R.id.sImageId).setImageResource(snackbarList[position].image!!)
            itemView.findViewById<TextView>(R.id.sTextId).text = snackbarList[position].name

            itemView.setOnClickListener {

                onMyItemClickListener.onDeleteItem(snackbarClass, position,itemView)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {

        return Vh(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.snackbar_sheet_dialog, parent, false)
        )

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(snackbarList[position], position)
    }

    override fun getItemCount(): Int {

        return snackbarList.size

    }

    interface OnMyItemClickListener{

        fun onDeleteItem(snackbarClass: SClass,position: Int,itemView: View)

    }

}