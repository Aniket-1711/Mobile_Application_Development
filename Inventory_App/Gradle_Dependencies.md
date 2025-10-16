## In Plugins add the following
```
   kotlin("kapt")
```

   
  ## Add the following dependencies
  
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")

    kapt("androidx.room:room-compiler${room_version}")  

    implementation("androidx.room:room-kts:${room_version}")
