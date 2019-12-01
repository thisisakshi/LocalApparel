package com.example.myapplication.Objects

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_layout.view.*



class RecyclerView_Adapter (val myItemsList: List<Items>,val mCtx: Context,savedInstanceState: Bundle?
) : RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder>() {

    val thisOIS = savedInstanceState
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val theItemNameTV = itemView.findViewById<TextView>(com.example.myapplication.R.id.recycler_layout_itemName_id)
        val theItemPriceTV = itemView.findViewById<TextView>(com.example.myapplication.R.id.recycler_layout_itemPrice_id)
        val theItemSizeTV = itemView.findViewById<TextView>(com.example.myapplication.R.id.recycler_layout_itemSize_id)
        val theItemEmailTV = itemView.findViewById<TextView>(com.example.myapplication.R.id.recycler_layout_itemEmail_id)
        val theDetailsButton = itemView.findViewById<Button>(com.example.myapplication.R.id.recycler_layout_DetailsButton_id)
        val theMessageButton = itemView.findViewById<Button>(com.example.myapplication.R.id.recycler_layout_MessageButton_id)
        val theWishListButton = itemView.findViewById<Button>(com.example.myapplication.R.id.recycler_layout_WIshListButton_id)
        val thePicture = itemView.findViewById<ImageView>(com.example.myapplication.R.id.recycler_layout_imageView_id)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(com.example.myapplication.R.layout.recycler_layout,parent,false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return myItemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Items = myItemsList[position]

        holder.theItemNameTV.text = "Item Name: " +item.itemName
        holder.theItemPriceTV.text = "$" + item.itemPrice
        holder.theItemSizeTV.text = "Size: " +item.itemSize
        holder.theItemEmailTV.text = "Email: " +item.itemEmail
        Picasso.get().load(item.itemUrl).into(holder.thePicture.recycler_layout_imageView_id)


        holder.theDetailsButton.setOnClickListener {
            Toast.makeText(mCtx,"You Want Details?",Toast.LENGTH_LONG).show()
            //showItemPageDialog(theItem)
        }

        holder.theMessageButton.setOnClickListener {
            val emailSubject = "Hello, I would like to buy Your "+item.itemName

            val uriText = "mailto:"+item.itemEmail +
                    "?subject=" + Uri.encode(emailSubject)
            val uri = Uri.parse(uriText)

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = uri
            try {
                startActivity(mCtx, emailIntent, thisOIS)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(mCtx,e.message,Toast.LENGTH_LONG)
            }

        }


        holder.theWishListButton.setOnClickListener {
            Toast.makeText(mCtx,"You Want Wishlist?",Toast.LENGTH_LONG).show()
        }

    }
}