package com.rhymi.view.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rhymi.Utility.convertMillisToDate
import com.rhymi.service.room.ClassEntity
import com.rhymi.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    addClassButtonOnClick: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val classes by viewModel.classes.collectAsState(initial = emptyList())

    Scaffold (
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Rhymi"
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Home"
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(innerPadding)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Dance Progress",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp),
                    text = "${classes.size} dances this week"
                )
                AddClassButton(
                    modifier = Modifier.padding(8.dp),
                    onClick = addClassButtonOnClick
                )
            }

            Text(
                modifier = Modifier.padding(8.dp),
                text = "Recent Classes",
                style = MaterialTheme.typography.titleMedium
            )

            LazyColumn (
                modifier = Modifier.fillMaxSize()
            ) {
                items(classes) { classItem ->
                    DanceCard(classItem)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun AddClassButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        Icon(Icons.Default.Add, contentDescription = null)
        Spacer(Modifier.width(8.dp))
        Text("Add New Class")
    }
}

@Composable
fun DanceCard(classEntity: ClassEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(classEntity.date?.let { convertMillisToDate(it) } ?: "")
            Text(classEntity.style, style = MaterialTheme.typography.titleMedium)
            Text("${classEntity.teacherName}")
            Text(classEntity.songName)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddClassButtonPreview() {
    AddClassButton(onClick = {})
}

@Preview(showBackground = true)
@Composable
fun DanceCardPreview() {
    val sampleClass = ClassEntity(
        id = 1,
        date = System.currentTimeMillis(),
        style = "Hip Hop",
        teacherName = "Chris",
        songName = "Dance Song",
        videoUrl = "",
        notes = " "
    )

    DanceCard(classEntity = sampleClass)
}
