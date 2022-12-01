package com.demo.statehoistinginjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.statehoistinginjetpackcompose.ui.theme.StateHoistingInJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateHoistingInJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    ParentContent1()
                }
            }
        }
    }
}

//state hoisting
//stateful---which contains state.eg-TextField
//stateless--which doesn't contain any state.eg-Text("Hello world")
//state hoisting-- only parent composable function keep state in it.
// any child composable function will never keep state in it.

@Composable
fun Stateful(){

    var name by remember { mutableStateOf("") }
    TextField(value = name, onValueChange = {
        name=it
    })

}

@Composable
fun Stateless(){

    Text("Hello World")

}

//functions without state hoisting
@Composable
fun ParentContent(){
    val name = remember { mutableStateOf("") }
    ChildContent(name = name)
}

@Composable
fun ChildContent(name:MutableState<String>){
    Text("hello ${name.value}")
    Spacer(modifier = Modifier.height(20.dp))
    TextField(value = name.value, onValueChange = {
        name.value=it
    } )
}

//functions without state hoisting
@Composable
fun ParentContent1(){
    var name by remember { mutableStateOf("") }
    ChildContent1(name = name) {
        name = it
    }
}

@Composable
fun ChildContent1(name:String, onChange:(String)->Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("hello ${name}")
        Spacer(modifier = Modifier.height(20.dp))
        TextField(value = name, onValueChange = {
             onChange(it)
        },
        placeholder = { Text(text = "Type something...")}
        )
    }

}
