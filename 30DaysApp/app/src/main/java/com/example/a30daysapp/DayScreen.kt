package com.example.a30daysapp

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap.Companion.Square
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30daysapp.data.DayRepository
import com.example.a30daysapp.model.Day
import kotlin.time.Duration.Companion.days

@Composable
fun DayItem(day: Day, modifier : Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )


    Card(
        modifier = modifier
            .padding(16.dp, 8.dp)
            .clickable {
                expanded = !expanded
            }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
                )
                .background(color = color)
        ) {
            Text(
                text = stringResource(day.dayRes),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(top = 8.dp, start = 16.dp)
            )
            Text(
                text = stringResource(day.titleRes),
                fontSize = 20.sp,
                modifier = modifier.padding(start = 16.dp)
            )
            Image(
                painter = painterResource(day.imageRes),
                contentDescription = null,
                modifier = modifier
                    .width(400.dp)
                    .padding(start = 16.dp, top = 8.dp, bottom = 16.dp, end = 16.dp),
                contentScale = ContentScale.Crop
            )
            if (expanded) {
                Text(
                    text = stringResource(day.descriptionRes),
                    modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun DayList(days: List<Day>, modifier: Modifier = Modifier) {
    Scaffold (
        topBar = {
            DayTopBar()
        }
    ) { it ->
        LazyColumn(
            contentPadding = it,
        ) {
            items(days) {
                DayItem(
                    day = it,
                )
            }
        }
    }
}

@Composable
fun DayTopBar(modifier: Modifier = Modifier) {
    Text(
        text = "30 Days Of Wellness",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(top = 16.dp, start = 16.dp)
    )
}


@Composable
@Preview
fun DayItemPreview() {
    DayList(DayRepository.days)
}