package com.example.officecleanalert

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    val REQUEST_IMAGE_CAPTURE = Bundle()

    lateinit var takePictureButton: Button
    lateinit var sendWhatsAppButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        takePictureButton = findViewById(R.id.takePictureButton)
        sendWhatsAppButton = findViewById(R.id.sendWhatsAppButton)

        takePictureButton.setOnClickListener {
            dispatchTakePictureIntent()


        }


        sendWhatsAppButton.setOnClickListener {
            sendWhatsAppMessage()
        }


    }

    private fun dispatchTakePictureIntent() {
        // Create an Intent to open the camera app for capturing an image
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // Check if there's a camera app available to handle the intent
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Start the camera activity and wait for the result
            startActivity(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }


    // Implement the method to handle the result from the camera intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Handle the captured image, e.g., save it to a file
            val imageUri: Uri? = data?.data
            // Save the image URI or perform other necessary actions
        }
    }

    // Implement the method to send a WhatsApp message
    private fun sendWhatsAppMessage() {
        val message = "Cleanliness issue - please check the attached image."
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://api.whatsapp.com/send?text=$message")
        startActivity(intent)
    }
}