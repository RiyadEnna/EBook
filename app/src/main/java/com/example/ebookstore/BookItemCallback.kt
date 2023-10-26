package com.example.ebookstore

interface BookItemCallback {
    fun onCellClick(book:Items)
    fun onSaveBook(book:Items)
}