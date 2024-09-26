package com.ken.githubclient.ui.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun UserCenterScreen() {
    val textList = listOf(
        "登录",
        "仓库",
        "关注者",
        "开源许可",
        "设置"
    )

    Column {
        textList.forEachIndexed { index, text ->
            Column {
                Text(
                    text = text,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // 在这里添加点击事件的处理逻辑
                            when (index) {
                                0 -> {
                                    // 处理文案 1 的点击事件
                                }

                                1 -> {
                                    // 处理文案 2 的点击事件
                                }

                                2 -> {
                                    // 处理文案 3 的点击事件
                                }

                                3 -> {
                                    // 处理文案 4 的点击事件
                                }

                                4 -> {
                                    // 处理文案 5 的点击事件
                                }
                            }
                        }
                        .padding(16.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(5.dp))
                        .padding(10.dp)
                )
                if (index < textList.size - 1) {
                    Divider(
                        color = Color.Transparent,
                        thickness = 0.dp,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }
}