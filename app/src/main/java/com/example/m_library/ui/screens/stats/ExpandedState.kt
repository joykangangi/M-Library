package com.example.m_library.ui.screens.stats



data class ExpandedState(
   val isFinishExpanded: Boolean = false,
   val isReadingExpanded: Boolean = false,
   val isToReadExpanded: Boolean = false,
)

sealed class ExpandedEvents {
   object ExpandFinish : ExpandedEvents()
   object ExpandReading : ExpandedEvents()
   object ExpandToRead : ExpandedEvents()
}