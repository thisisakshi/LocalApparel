package com.example.myapplication.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.Objects.Items
import com.example.myapplication.Objects.StoreItemsListAdapter
import com.example.myapplication.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_buy.*

/**
 * A simple [Fragment] subclass.
 */
private const val MYTAG = "myBuyFrag"
class BuyFragment : Fragment() {

    lateinit var ref: DatabaseReference
    lateinit var storeItemsList: MutableList<Items>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storeItemsList = mutableListOf()

        ref = FirebaseDatabase.getInstance().getReference("mainShop")

        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(mainShop: DataSnapshot) {
                Log.i(MYTAG,"You are looking at snapshots")
                if(mainShop.exists()){
                    storeItemsList.clear()
                    for(items in mainShop.children){
                        Log.i("myBuyFrag","You are looking at snapshots AGAIN!")
                        val thisItem = items.getValue(Items::class.java)
                        //val itemName = thisItem!!.itemName.toString()
                        //Log.i(MYTAG, "THE ITEM NAME IS $itemName")
                        storeItemsList.add(thisItem!!)
                    }

                    val myAdapter = StoreItemsListAdapter(requireContext(),R.layout.store_items_layout,storeItemsList,savedInstanceState)
                    buy_items_LV_id.adapter = myAdapter
                }
            }

        })

    }
}
