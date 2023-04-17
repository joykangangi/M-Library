package com.example.m_library.model

import kotlinx.collections.immutable.ImmutableList

data class ExpandableCardModel(
    val title: String,
    val number: String,
    val body: ImmutableList<Book>
)
