package com.example.ebookstore

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ebookstore.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setViewItems()
    }

    private fun setViewItems() {
        setTitle(R.string.bibliotheque)
        displayBookList(SharedPreferencesManager().getLocalBookStorage(this).localBooks)

    }

    fun displayBookList(books: MutableList<Items>) {
        val adapter = BookListViewAdapter(books, object : BookItemCallback {
            override fun onCellClick(book: Items) {
            }

            override fun onSaveBook(book: Items) {
                deleteBook(book)
            }
        })
        binding.bookRv.adapter = adapter
        binding.bookRv.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun deleteBook(book: Items) {
        SharedPreferencesManager().deleteBook(book, this)
        displayBookList(SharedPreferencesManager().getLocalBookStorage(this).localBooks)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}