
package com.example.inventoryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Insert
import com.example.inventoryapp.ui.theme.InventoryAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.mutableIntStateOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val database= UserDatabase.getInstance(this)
            InsertRecord(database)
        }
    }
}

@Composable
fun InsertRecord(database: UserDatabase){
    val context= LocalContext.current
    var name by remember { mutableStateOf("") }
//    var phonenum by remember { mutableIntStateOf(0) }
        var phonenum by remember { mutableStateOf("") }

    val userdao=database.userDao()
    val scope=rememberCoroutineScope()

    Column (modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)){
        Text("Enter the user Details", fontSize = (30.sp),
            color=Color.Red)
        Spacer(modifier= Modifier.height(35.dp))

        OutlinedTextField(
            value=name,
            onValueChange = {name=it},
            label = {Text("Enter User Name")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(35.dp))

        OutlinedTextField(
            value=phonenum.toString(),
            onValueChange = {phonenum=it},
            label = {Text("Enter Phone Number")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(35.dp))
        Button(
            onClick = {
                scope.launch(Dispatchers.IO) {
                    userdao.insert(User(userName = name, userPhone = phonenum))
                }

                Toast.makeText(context, "Inserted Successfully", Toast.LENGTH_LONG).show()
            },
        ) {
            Text("Insert New")
        }
    }

}

