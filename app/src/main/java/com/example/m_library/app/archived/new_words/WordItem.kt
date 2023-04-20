//package com.example.m_library.app.archived.new_words
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.m_library.app.data.local.NewWord
//
//@Composable
//fun WordItem(
//    modifier: Modifier = Modifier,
//    newWord: NewWord
//) {
//    Card(
//        modifier = modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(8.dp),
//        elevation = 10.dp
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(4.dp),
//            verticalArrangement = Arrangement.spacedBy(10.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = newWord.newWord, style = MaterialTheme.typography.h6)
//
//            Text(text = newWord.meaning, style = MaterialTheme.typography.body1, maxLines = 3)
//        }
//
//    }
//
//}