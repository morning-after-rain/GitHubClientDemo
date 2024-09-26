package com.ken.githubclient.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(onSearchClicked: (String) -> Unit, onTextChanged: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.Black, shape = RoundedCornerShape(10.dp))
            .clickable { }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = CenterVertically
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = text,
                    onValueChange = { newText: String ->
                        text = newText
                        onTextChanged(newText)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .onFocusChanged {
                            isFocused = it.isFocused
                        },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.White
                    ),
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 12.sp),
                    placeholder = {
                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                text = "输入关键词搜索仓库",
                                color = Color(0xAAFFFFFF),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(start = 8.dp),
                            )
                        }

                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .padding(end = 16.dp)
                        .clickable {
                            onSearchClicked(text)
                            isFocused = false
                        }
                        .align(CenterVertically)
                ) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}
