package com.example.storage

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@ExperimentalCoilApi
@Composable
fun HomeScreen() {

    var file by remember{ mutableStateOf<Uri?>(null)}

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts
        .GetContent()){ uri ->
            if(uri != null){
                file = uri
            }
        }

    launcher.launch("image/*")

    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ){
        val img = if(file != null) file else R.drawable.image
        Image(
            rememberImagePainter(img),
            "Image",
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            // for launching storage view
            launcher.launch("image/*")
        }) {
            Text("Select", fontSize = 25.sp)
        }
    }

}
