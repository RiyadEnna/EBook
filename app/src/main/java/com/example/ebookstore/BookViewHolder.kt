package com.example.ebookstore

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BookViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var bookImageIV: ImageView
    private var bookTitleTV: TextView
    private var containerCL: ConstraintLayout
    private var bookmark: ImageView

    init {
        bookImageIV = itemView.findViewById(R.id.book_iv)
        bookTitleTV = itemView.findViewById(R.id.book_title_tv)
        containerCL = itemView.findViewById(R.id.container)
        bookmark = itemView.findViewById(R.id.bookmark)
    }

    fun bind(book: Items, bookItemCallback: BookItemCallback) {
        bookTitleTV.text = book.volumeInfo?.title
        Glide.with(bookImageIV.context).load(book.volumeInfo?.imageLinks?.thumbnail)
            .into(bookImageIV)
        containerCL.setOnClickListener {
            bookItemCallback.onCellClick(book)
        }
        bookmark.setOnClickListener {
            bookItemCallback.onSaveBook(book)
        }
    }
}