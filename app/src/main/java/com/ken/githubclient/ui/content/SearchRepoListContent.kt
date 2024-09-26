package com.ken.githubclient.ui.content


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.ken.githubclient.common.LoadStatus
import com.ken.githubclient.ui.components.RepoItem
import com.ken.githubclient.ui.components.SearchBar
import com.ken.githubclient.ui.navigation.ClientRoute
import com.ken.githubclient.ui.viewmodel.SearchResultViewModel
import com.ken.githubclient.utls.Logger
import kotlinx.coroutines.flow.distinctUntilChanged


@Composable
fun SearchScreen() {
    val TAG = "SearchScreen"
    val navController = rememberNavController()
    val viewModel: SearchResultViewModel = viewModel()
    val listState = rememberLazyListState()
    var isButtonLoading by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(onSearchClicked = { text ->
            // 处理搜索点击的逻辑
            viewModel.search(text)
            Logger.d(TAG, "点击搜索，输入的文本: $text")
        }, onTextChanged = { newText ->
            Logger.d(TAG, "点击搜索，输入的文本: $newText")
        })
        //repoList
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Logger.d(TAG, "start show isLoading:{${uiState.loadStatus}}")
            if (uiState.loadStatus == LoadStatus.LOADING && uiState.repoList.isEmpty()) {
                Logger.d(TAG, "show loading")
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (uiState.repoList.isEmpty()) {
                Logger.d(TAG, "show empty")
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
                        isButtonLoading = true // 点击时设置为加载中
                        viewModel.reload()
                    }) {
                        Text("加载出错，请重试")
                    }
                    Column {
                        if (isButtonLoading) {
                            CircularProgressIndicator() // 按钮点击时显示加载指示器
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(), state = listState
                ) {
                    Logger.d(TAG, "show list")
                    itemsIndexed(uiState.repoList) { index, repoData ->
                        RepoItem(repoData) {
                            navController.navigate("${ClientRoute.REPO_DETAIL}/${repoData.id}")
                        }
                    }
                    if (uiState.isLoadMore) {
                        item {
                            Text(
                                text = "加载更多...",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        LaunchedEffect(listState) {
            Logger.d(TAG, "listState change")
            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == uiState.repoList.size - 1 }.distinctUntilChanged()
                .collect {
                    if (!uiState.isLoadMore) {
                        viewModel.loadMore()
                    }
                }
        }
    }
}