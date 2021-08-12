package com.danilo.newsapp.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.danilo.newsapp.R
import com.danilo.newsapp.databinding.ArticleItemBinding
import com.danilo.newsapp.domain.model.Article
import com.squareup.picasso.Picasso

class NewsViewHolder(private val itemBinding: ArticleItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(article: Article) {
        itemBinding.tvDescricao.text = article.description
        itemBinding.tvTitulo.text = article.title
        Picasso.get().load(article.urlToImage).placeholder(R.drawable.ic_baseline_article_24).error(R.drawable.ic_baseline_article_24).into(itemBinding.articleImagem)
    }
}

