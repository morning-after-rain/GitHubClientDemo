package com.ken.githubclient.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ken.githubclient.GithubApplication
import com.ken.githubclient.R
import com.ken.githubclient.net.model.RepoEntity

@Composable
fun RepoItem(repoData: RepoEntity, navigateToDetail: (Long) -> Unit) {
//    Divider(color = Color.LightGray, thickness = 0.5.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                Toast
                    .makeText(GithubApplication.instance, "todo", Toast.LENGTH_SHORT)
                    .show()
//                navigateToDetail(repoData.id)
            }
            .background(Color(0xDDDDDDDD), shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Avatar
                Image(
                    painter = painterResource(id = R.mipmap.ic_github),
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                        .clip(CircleShape)
                )
                // Owner Name
                Text(
                    text = repoData.owner.login ?: "testName",
                    color = colorResource(id = R.color.black),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        top = 8.dp,
                        end = 12.dp,
                        bottom = 5.dp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .padding(top = 5.dp)
            ) {
                // Repo Name
                Text(
                    text = repoData.name ?: "",
                    color = colorResource(id = R.color.half_black),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .padding(top = 5.dp)
            ) {
                // Repo Description
                Text(
                    text = repoData.description ?: "",
                    color = colorResource(id = R.color.half_black),
                    fontSize = 12.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .padding(top = 5.dp)
            ) {
                // Star Icon Count updateTime
                Row(
                    modifier = Modifier.padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //Star Icon
                    Image(
                        painter = painterResource(id = R.mipmap.ic_star_yellow_light),
                        contentDescription = null,
                        modifier = Modifier
                            .size(15.dp),
                        contentScale = ContentScale.Fit
                    )
                    //  Count
                    Text(
                        text = repoData.stargazers_count.toString(),
                        color = colorResource(id = R.color.half_black),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.CenterVertically)
                    )
                    // lan
                    Text(
                        text = repoData.language ?: "kotlin",
                        color = colorResource(id = R.color.half_black),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.CenterVertically)
                    )
                    // Event Time
                    Text(
                        text = repoData.updated_at ?: "12345",
                        color = colorResource(id = R.color.half_black),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
//        Divider(color = Color.LightGray, thickness = 0.5.dp)
    }
}