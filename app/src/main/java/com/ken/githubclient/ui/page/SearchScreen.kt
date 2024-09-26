package com.ken.githubclient.ui.page


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ken.githubclient.ui.components.RepoList
import com.ken.githubclient.ui.components.SearchBar
import com.ken.githubclient.utls.Logger

@Composable
fun SearchScreen() {
    val DEFAULT_KEY_WORD = "stars:>1"
    val TAG = "SearchPage"
    val searchStr = remember { mutableStateOf(DEFAULT_KEY_WORD) }  // 使用 MutableState

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    color = Color.Black
//                    , shape = RoundedCornerShape(10.dp)
                )
        ) {
            SearchBar(onSearchClicked = { text ->
                // 处理搜索点击的逻辑
                searchStr.value = text
                Logger.d(TAG, "点击搜索，输入的文本: $text")
            }, onTextChanged = { newText ->
//                    searchStr = newText
                Logger.d(TAG, "点击搜索，输入的文本: $newText")
            })
        }
        RepoList(searchStr.value)
    }


}

