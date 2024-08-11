package com.randomuser.app.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import com.randomuser.app.R
import com.randomuser.app.data.User
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ListingScreenUI (
    viewModel: ListingOfUsersViewModel,
    onNavigate: (Int, Bundle) -> Unit
) {
    Scaffold(
        content = {
            if(viewModel.loading.value) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if (viewModel.errorMessage.isEmpty()) {
                    LazyColumn {
                        items(viewModel.usersList) { user ->
                            Box(Modifier.clickable(onClick = {
//                                Log.d("RandomUser", "Clicked")
                                val bundle = bundleOf("user" to user)
                                onNavigate(R.id.action_listingOfUsersFragment_to_userDetailFragment, bundle)
                            })) {
                                UserCard(user)
                            }
                        }
                    }
                } else {
                    Text(viewModel.errorMessage)
                }
            }
        }
    )
}

@Composable
fun UserCard(user: User) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row {
            UserImage(user.picture.thumbnail)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)) {
                Text(text = user.name.title + " " + user.name.first + " " + user.name.last, style = typography.h6)
                Text(text = user.gender, style = typography.caption)
                Text(text = user.location.city, style = typography.caption)
            }
        }
    }
}

@Composable
fun UserImage(url: String) {
    GlideImage(
        imageModel = url,
        // Crop, Fit, Inside, FillHeight, FillWidth, None
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
        shimmerParams = ShimmerParams(
            baseColor = MaterialTheme.colors.background,
            highlightColor = MaterialTheme.colors.primary,
            durationMillis = 350,
            dropOff = 0.65f,
            tilt = 20f),
        failure = {
            Text(text = "Image not available")
        }
    )
}