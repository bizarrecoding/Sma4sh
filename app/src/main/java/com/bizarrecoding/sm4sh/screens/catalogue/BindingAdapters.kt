package com.bizarrecoding.sm4sh.screens.catalogue

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bizarrecoding.sm4sh.R
import com.bizarrecoding.sm4sh.adapters.ProductAdapter
import com.bizarrecoding.sm4sh.models.Product
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, listData: List<Product>?) {
    val adapter = recyclerView.adapter as ProductAdapter
    adapter.submitList(listData)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground))
            .into(imgView)
    }
}