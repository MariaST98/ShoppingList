package com.example.shoppinglistcompose.ui1.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglistcompose.R
import com.example.shoppinglistcompose.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(context:Context, var addDialogListener: AddDialogListener):AppCompatDialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)
        tvAdd.setOnClickListener {
            val name =etName.text.toString()
            val amount=etAmount.text.toString()
            if(name.isEmpty()|| amount.isEmpty()){
                Toast.makeText(context,"Please enter all the information", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val item=ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }
        tvCancel.setOnClickListener{
            cancel()
        }
    }
    }