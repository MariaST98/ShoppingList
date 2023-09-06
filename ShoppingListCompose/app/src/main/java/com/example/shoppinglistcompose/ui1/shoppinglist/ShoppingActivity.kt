package com.example.shoppinglistcompose.ui1.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglistcompose.R
import com.example.shoppinglistcompose.data.db.ShoppingDatabase
import com.example.shoppinglistcompose.data.db.entities.ShoppingItem
import com.example.shoppinglistcompose.data.repositories.ShoppingRepository
import com.example.shoppinglistcompose.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database=ShoppingDatabase(this)
        val repository=ShoppingRepository(database)
        val factory=ShoppingViewModelFactory(repository)

        val viewModel=ViewModelProvider(this,factory).get(ShoppingViewModel::class.java)

        val adapter=ShoppingItemAdapter(listOf(),viewModel)

        rvShoppingItems.layoutManager=LinearLayoutManager(this)
        rvShoppingItems.adapter=adapter

        viewModel.getAllShoppingItem().observe(this, Observer {
       adapter.items=it
            adapter.notifyDataSetChanged()
        })
        fab.setOnClickListener{
            AddShoppingItemDialog(this,
           object:AddDialogListener {
               override fun onAddButtonClicked(item: ShoppingItem) {
                 viewModel.upsert(item)
               }

           }).show()
        }
       }
    }

