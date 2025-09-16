package com.rhymi.view.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rhymi.service.room.ClassEntity
import com.rhymi.utils.DateExtensions
import com.rhymi.viewmodel.AddClassViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClassScreen(
    navController: NavController
) {
    val viewModel: AddClassViewModel = hiltViewModel()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    var style by remember { mutableStateOf("") }
    var teacherName by remember { mutableStateOf("") }
    var song by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    val videoPath = remember { mutableStateOf<Uri?>(null) }
    val videoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        videoPath.value = it
    }
    var selectedDate = remember { mutableStateOf<Long?>(null) }
    var showDatePickerModal = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Dance Class",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate("home") }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            DatePickerFieldToModal(
                selectedDate = selectedDate,
                showModal = showDatePickerModal
            )
            LabeledTextField(
                label = "Style",
                value = style,
                onValueChange = { style = it }
            )
            LabeledTextField(
                label = "Teacher",
                value = teacherName,
                onValueChange =  { teacherName = it }
            )
            LabeledTextField(
                label = "Song",
                value = song,
                onValueChange = { song = it }
            )
            Text(
                text = "Video(s)",
                style = MaterialTheme.typography.bodySmall
            )
            AddVideoButton(
                onClick = {
                    videoLauncher.launch(
                        PickVisualMediaRequest(
                            mediaType = ActivityResultContracts.PickVisualMedia.VideoOnly
                        )
                    )
                },
                videoPath = videoPath
            )
            NotesTextField(
                label = "Notes",
                value = notes,
                onValueChange = { notes = it }
            )
            Spacer(Modifier.height(16.dp))
            SaveButton(
                onClick = {
                    viewModel.saveDanceClass(
                        classEntity = ClassEntity(
                            date = selectedDate.value ?: 0L,
                            style = style,
                            teacherName = teacherName,
                            songName = song,
                            videoUrl = videoPath.value.toString(),
                            notes = notes
                        )
                    )
                    navController.navigate("home")
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun DatePickerFieldToModal(
    selectedDate: MutableState<Long?>,
    showModal: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Date",
        style = MaterialTheme.typography.bodySmall
    )
    OutlinedTextField(
        value = selectedDate.value?.let { DateExtensions.convertMillisToDate(it) } ?: "",
        onValueChange = { },
        placeholder = { Text("MM/DD/YYYY") },
        trailingIcon = {
            Icon(Icons.Default.DateRange, contentDescription = "Select date")
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .pointerInput(selectedDate.value) {
                awaitEachGesture {
                    // Modifier.clickable doesn't work for text fields, so we use Modifier.pointerInput
                    // in the Initial pass to observe events before the text field consumes them
                    // in the Main pass.
                    awaitFirstDown(pass = PointerEventPass.Initial)
                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                    if (upEvent != null) {
                        showModal.value = true
                    }
                }
            },
        singleLine = true
    )

    if (showModal.value) {
        DatePickerModal(
            onDateSelected = { selectedDate.value = it },
            onDismiss = { showModal.value = false },
            modifier = modifier
        )
    }
}

@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodySmall
    )
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        singleLine = true
    )
}

@Composable
fun NotesTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodySmall
    )
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        minLines = 3,
        maxLines = 3
    )
}


@Composable
fun AddVideoButton(
    onClick: () -> Unit,
    videoPath: MutableState<Uri?>,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
    ) {
        Icon(Icons.Default.Add, contentDescription = null)
        Spacer(Modifier.width(8.dp))
        Text("Add Video")
    }

    videoPath.value?.let { image ->
        Text(text = "Video Path: " + image.path.toString())
    }
}

@Composable
fun SaveButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = "Save",
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LabeledTextFieldPreview() {
    LabeledTextField(
        label = "Style",
        value = "Hip Hop",
        onValueChange = {}
    )
}

@Preview(showBackground = true)
@Composable
fun NotesTextFieldPreview() {
    NotesTextField(
        label = "Notes",
        value = "Practice choreography",
        onValueChange = {}
    )
}

@Preview(showBackground = true)
@Composable
fun AddVideoButtonPreview() {
    val mockVideoPath = remember { mutableStateOf<Uri?>(null) }
    AddVideoButton(
        onClick = {},
        videoPath = mockVideoPath
    )
}

@Preview(showBackground = true)
@Composable
fun SaveButtonPreview() {
    SaveButton(onClick = {})
}

@Preview(showBackground = true)
@Composable
fun DatePickerFieldPreview() {
    val selectedDate = remember { mutableStateOf<Long?>(System.currentTimeMillis()) }
    val showModal = remember { mutableStateOf(false) }

    DatePickerFieldToModal(
        selectedDate = selectedDate,
        showModal = showModal
    )
}
