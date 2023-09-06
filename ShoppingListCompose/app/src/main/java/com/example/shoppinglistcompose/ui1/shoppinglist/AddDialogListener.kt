package com.example.shoppinglistcompose.ui1.shoppinglist

import com.example.shoppinglistcompose.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}