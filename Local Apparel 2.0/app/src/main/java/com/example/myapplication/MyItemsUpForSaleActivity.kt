package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Objects.Items
import com.example.myapplication.Objects.MyItemsUpForSaleListAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_items_up_for_sale.*

class MyItemsUpForSaleActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var myItemsUpForSaleList: MutableList<Items>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_up_for_sale)

        myItemsUpForSaleList = mutableListOf()
        val fragPref = this.getSharedPreferences("MY_SHARED_PREFERENCES", Context.MODE_PRIVATE)
        val userEmail = fragPref.getString("EMAIL","Loser").toString()


        ref = FirebaseDatabase.getInstance().getReference("mainShop")

        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
               // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    myItemsUpForSaleList.clear()
                    for(h in p0.children){
                        val thisItem = h.getValue(Items::class.java)
                        if(thisItem!!.itemEmail == userEmail){
                            myItemsUpForSaleList.add(thisItem!!)
                        }

                    }

                    val myAdapter = MyItemsUpForSaleListAdapter(this@MyItemsUpForSaleActivity,R.layout.my_items_up_for_sale_layout,myItemsUpForSaleList)
                    my_IUFS_listView_id.adapter = myAdapter
                }
            }

        })

    }
}
