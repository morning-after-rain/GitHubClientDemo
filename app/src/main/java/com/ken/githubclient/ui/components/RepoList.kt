package com.ken.githubclient.ui.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.ken.githubclient.common.LoadStatus
import com.ken.githubclient.net.model.RepoEntity
import com.ken.githubclient.net.repository.SearchResultRepository.Companion.PARAM_TYPE_REPO
import com.ken.githubclient.ui.navigation.ClientRoute
import com.ken.githubclient.ui.viewmodel.SearchResultViewModel
import com.ken.githubclient.utls.Logger
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun RepoList(searchStr: String = "stars:>1") {
    val TAG = "HomePage"
    val navController = rememberNavController()
    val viewModel: SearchResultViewModel = viewModel()

    var repoListState by remember { mutableStateOf(emptyList<RepoEntity>()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    var isButtonLoading by remember { mutableStateOf(false) } // 新增：用于控制按钮点击时的加载状态
    var loadMore by remember {
        mutableStateOf(false)
    }
//
//    val shouldReload by remember(searchStr) {
//        derivedStateOf { true }  // 这里表示只要 searchStr 变化就应该重新加载
//    }


    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logger.d(TAG, "start show isLoading:{$isLoading}")
        if (isLoading) {
            Logger.d(TAG, "show loading")
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (repoListState.isEmpty() || viewModel.loadFirstPageStatus.value == LoadStatus.ERROR) {
            Logger.d(TAG, "show empty")
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    isButtonLoading = true // 点击时设置为加载中
                    viewModel.search(type = PARAM_TYPE_REPO)
                }) {
                    Text("加载出错，请重试")
                }
                if (isButtonLoading) {
                    CircularProgressIndicator() // 按钮点击时显示加载指示器
                }
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }


        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), state = listState
            ) {
                Logger.d(TAG, "show list")
                itemsIndexed(repoListState) { index, repoData ->
                    RepoItem(repoData) {
                        navController.navigate("${ClientRoute.REPO_DETAIL}/${repoData.id}")
                    }
                }
                if (loadMore) {
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
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == repoListState.size - 1 }.distinctUntilChanged()
            .collect {
                if (!loadMore) {
                    //todo 滑动到底部显示文本提示并终止loadMore的触发
                    loadMore = true
                    viewModel.loadMore()
                }
            }
    }

    LaunchedEffect(Unit) {
        try {
            Logger.d(TAG, "load data")
            isLoading = true
            viewModel.search(searchStr, type = PARAM_TYPE_REPO)
        } catch (e: Exception) {
            viewModel.loadFirstPageStatus.value = LoadStatus.ERROR
        } finally {
            Logger.d(TAG, "load finish")
        }
    }

    LaunchedEffect(viewModel.repoList) {
        viewModel.repoList.observeForever { newRepoList ->
            Logger.d(TAG, "repoList change")
            if (newRepoList.isEmpty() && repoListState.isNotEmpty()) {
                //do nothing
            } else {
                repoListState = newRepoList ?: emptyList()
            }
            isLoading = false
            loadMore = false
        }
    }

    LaunchedEffect(searchStr) {
        try {
            Logger.d(TAG, "load data")
            isLoading = true
            viewModel.search(searchStr, type = PARAM_TYPE_REPO)
        } catch (e: Exception) {
            viewModel.loadFirstPageStatus.value = LoadStatus.ERROR
        } finally {
            Logger.d(TAG, "load finish")
        }
    }

}