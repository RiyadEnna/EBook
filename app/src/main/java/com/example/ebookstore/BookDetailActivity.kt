package com.example.ebookstore

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.ebookstore.databinding.ActivityBookDetailBinding


class BookDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setViewItems()
    }

    private fun setViewItems() {
        val bookTitle = intent.getStringExtra(MainActivity.bookKey)
        val bookImage = intent.getStringExtra(MainActivity.imageKey)

        binding.bookTitleTv.text = bookTitle
        Glide.with(binding.bookIv.context).load(bookImage)
            .into(binding.bookIv)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
