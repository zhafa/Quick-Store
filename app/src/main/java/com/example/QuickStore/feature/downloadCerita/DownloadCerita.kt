package com.example.QuickStore.feature.downloadCerita

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.example.QuickStore.model.cerita.CeritaModel
import java.io.File
import android.content.ContentValues
import android.os.Build
import android.provider.MediaStore
import java.io.FileInputStream
import java.io.OutputStream

fun DownloadCerita(context: Context, cerita: CeritaModel?) {
    cerita?.let { ceritaModel ->
        val storage = FirebaseStorage.getInstance()

        // Jika `gambar` adalah URL lengkap
        val storageRef = storage.getReferenceFromUrl(ceritaModel.gambar)

        // Mendownload file sementara ke direktori lokal
        val localFile = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "${ceritaModel.judul}.jpg")

        storageRef.getFile(localFile)
            .addOnSuccessListener { taskSnapshot ->
                // Setelah berhasil diunduh, simpan file ke galeri
                saveImageToGallery(context, localFile, ceritaModel.judul)
                Toast.makeText(context, "Gambar berhasil disimpan ke galeri.", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Gagal mengunduh gambar: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }
}

fun saveImageToGallery(context: Context, file: File, title: String) {
    val resolver = context.contentResolver
    val imageCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
    } else {
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    }

    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "$title.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
    }

    val imageUri = resolver.insert(imageCollection, contentValues)
    imageUri?.let { uri ->
        resolver.openOutputStream(uri).use { outputStream ->
            FileInputStream(file).use { inputStream ->
                inputStream.copyTo(outputStream as OutputStream)
            }
        }
    }
}
