package com.randomuser.app.fragments.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.randomuser.app.data.User
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UserDetailUI (user: User) {
    Scaffold(
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val state = remember {
                    MutableTransitionState(false).apply {
                        // Start the animation immediately.
                        targetState = true
                    }
                }
                AnimatedVisibility(
                    visibleState = state,
                    enter = slideInVertically(),
                    exit = slideOutVertically(),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        UserBigImage(user.picture.large)
                        Text(text = user.name.title + " " + user.name.first + " " + user.name.last, style = MaterialTheme.typography.h6)
                        Text(text = user.email, style = MaterialTheme.typography.caption)
                        Text(text = user.login.username, style = MaterialTheme.typography.caption)
                        Text(text = user.phone, style = MaterialTheme.typography.caption)
                        Text(text = user.cell, style = MaterialTheme.typography.caption)
                        Text(text = user.gender, style = MaterialTheme.typography.caption)
                        Text(text = user.location.city + " " + user.location.state + " " + user.location.city,
                            style = MaterialTheme.typography.caption)
                        Text(text = user.dob.age.toString() + " years old", style = MaterialTheme.typography.caption)
                    }
                }
            }
        }
    )
}

@Composable
fun UserBigImage(url: String) {
    GlideImage(
        imageModel = url,
        // Crop, Fit, Inside, FillHeight, FillWidth, None
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(300.dp)
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