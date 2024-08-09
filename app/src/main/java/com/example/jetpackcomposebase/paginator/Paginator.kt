package com.example.jetpackcomposebase.paginator

interface Paginator<Key, Item> {
    suspend fun loadNextItems(isShowLoaderVisible:Boolean = true)
    fun reset()
}