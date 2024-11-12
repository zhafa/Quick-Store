package com.example.QuickStore.data

import android.net.Uri
import com.example.QuickStore.model.cerita.CeritaModel
import com.example.QuickStore.model.user.UserModel
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

@Suppress("UNCHECKED_CAST")
class Repository constructor(
    val auth : FirebaseAuth,
    private val firestore : FirebaseFirestore,
    val storage: FirebaseStorage = FirebaseStorage.getInstance(),
    val storageRef: StorageReference = storage.reference
){
    fun signUp(
        email : String,
        password : String,
        nama: String,
        onSuccess: () -> Unit,
        onFailed: (Exception) -> Unit
    ){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                firestore
                    .collection("user")
                    .document(it.user?.uid ?: "")
                    .set(
                        UserModel(
                            uid = it.user?.uid ?: "",
                            nama = nama,
                            biodata = "",
                            phqScore = -1,
                            premium = false
                        )
                    )
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {
                        onFailed(it)
                    }
            }
            .addOnFailureListener{
                onFailed(it)
            }
    }

    fun signIn(
        email: String,
        password: String,
        onSuccess: (Boolean) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess(true)
            }
            .addOnFailureListener {
                onFailed(it)
            }
    }

    fun getUser(
        uid: String,
        onSuccess: (UserModel) -> Unit,
        onFailed: (Exception) -> Unit,
    ){
        firestore
            .collection("user")
            .document(uid)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                }
                value?.let { doc ->
                    onSuccess(
                        UserModel(
                            uid = auth?.uid ?: "",
                            nama = doc["nama"] as String,
                            biodata = doc["biodata"] as String,
                            phqScore = doc["phqScore"] as Long,
                            premium = doc["premium"] as Boolean
                        )
                    )
                    return@addSnapshotListener
                }
            }
    }



    fun createCerita(
        gambar: String?,
        isi: String,
        judul: String,
        kategori: List<String>,
        onSuccess: (Boolean) -> Unit,
        onFailed: (Exception) -> Unit
    ) {
        val imageUploadTask = if (gambar != null) {
            val imageRef = storageRef.child("images/${System.currentTimeMillis()}_${gambar.split('/').last()}")
            imageRef.putFile(Uri.parse(gambar)).continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                imageRef.downloadUrl
            }
        } else {
            Tasks.forResult(null)
        }

        imageUploadTask.addOnSuccessListener { uri ->
            val imageUrl = uri?.toString()
            val id = UUID.randomUUID().toString()
            val uid = auth.currentUser?.uid ?: ""

            firestore.collection("user").document(uid).get().addOnSuccessListener { document ->
                firestore.collection("cerita")
                    .document(id)
                    .set(CeritaModel(
                        gambar = imageUrl ?: "",
                        penulis = document.getString("nama") ?: "",
                        id = id,
                        isi = isi,
                        judul = judul,
                        kategori = kategori,
                        user_id = auth.currentUser?.uid ?: ""
                    ))
                    .addOnSuccessListener {
                        onSuccess(true)
                    }
                    .addOnFailureListener { exception ->
                        onFailed(exception)
                    }
            }
        }.addOnFailureListener { exception ->
            onFailed(exception)
        }
    }

    fun editCerita(
        ceritaId: String,
        gambar: String?,
        isi: String,
        judul: String,
        kategori: List<String>,
        onSuccess: (Boolean) -> Unit,
        onFailed: (Exception) -> Unit
    ) {
        val imageUploadTask = if (gambar != null) {
            val imageRef = storageRef.child("images/${System.currentTimeMillis()}_${gambar.split('/').last()}")
            imageRef.putFile(Uri.parse(gambar)).continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                imageRef.downloadUrl
            }
        } else {
            Tasks.forResult(null)
        }

        imageUploadTask.addOnSuccessListener { uri ->
            val imageUrl = uri?.toString()
            val uid = auth.currentUser?.uid ?: ""

            firestore.collection("cerita")
                .document(ceritaId)
                .update(
                    mapOf(
                        "gambar" to (imageUrl ?: ""),
                        "isi" to isi,
                        "judul" to judul,
                        "kategori" to kategori,
                        "user_id" to uid
                    )
                )
                .addOnSuccessListener {
                    onSuccess(true)
                }
                .addOnFailureListener { exception ->
                    onFailed(exception)
                }
        }.addOnFailureListener { exception ->
            onFailed(exception)
        }
    }



    fun getAllCerita(
        onSuccess: (List<CeritaModel>) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        firestore
            .collection("cerita")
            .addSnapshotListener{value, error ->
                if (error != null) {
                    onFailed(error)
                    return@addSnapshotListener
                }
                value?.let {
                    onSuccess(
                        it.documents.map {
                                doc ->
                            CeritaModel(
                                gambar = doc.getString("gambar") ?: "",
                                penulis = doc.getString("penulis") ?: "",
                                id = doc.getString("id") ?: "",
                                isi = doc.getString("isi") ?: "",
                                judul = doc.getString("judul") ?: "",
                                kategori = doc.get("kategori") as? List<String> ?: listOf(),
                                user_id = doc.getString("user_id") ?: ""
                            )
                        }
                    )
                    return@addSnapshotListener
                }
            }
    }

    fun getCeritabyKategori(
        kategori: String,
        onSuccess: (List<CeritaModel>) -> Unit,
        onFailed: (Exception) -> Unit
    ) {
        firestore
            .collection("cerita")
            .whereArrayContains("kategori", kategori)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                    return@addSnapshotListener
                }
                value?.let {
                    onSuccess(
                        it.documents.map { doc ->
                            CeritaModel(
                                gambar = doc.getString("gambar") ?: "",
                                penulis = doc.getString("penulis") ?: "",
                                id = doc.getString("id") ?: "",
                                isi = doc.getString("isi") ?: "",
                                judul = doc.getString("judul") ?: "",
                                kategori = doc.get("kategori") as? List<String> ?: listOf(),
                                user_id = doc.getString("user_id") ?: ""
                            )
                        }
                    )
                    return@addSnapshotListener
                }
            }
    }

    fun getCeritabyId(
        id: String,
        onSuccess: (CeritaModel) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        firestore
            .collection("cerita")
            .document(id)
            .addSnapshotListener{value, error ->
                if (error != null) {
                    onFailed(error)
                    return@addSnapshotListener
                }
                value?.let { doc ->
                    onSuccess(
                        CeritaModel(
                            gambar = doc.getString("gambar") ?: "",
                            penulis = doc.getString("penulis") ?: "",
                            id = doc.getString("id") ?: "",
                            isi = doc.getString("isi") ?: "",
                            judul = doc.getString("judul") ?: "",
                            kategori = doc.get("kategori") as? List<String> ?: listOf(),
                            user_id = doc.getString("user_id") ?: ""
                        )
                    )
                    return@addSnapshotListener
                }
            }
    }

    fun getAllUserCerita(
        onSuccess: (List<CeritaModel>) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        firestore
            .collection("cerita")
            .whereEqualTo("user_id", auth.currentUser?.uid ?: "")
            .addSnapshotListener{value, error ->
                if (error != null) {
                    onFailed(error)
                    return@addSnapshotListener
                }
                value?.let {
                    onSuccess(
                        it.documents.map {
                                doc ->
                            CeritaModel(
                                gambar = doc.getString("gambar") ?: "",
                                penulis = doc.getString("penulis") ?: "",
                                id = doc.getString("id") ?: "",
                                isi = doc.getString("isi") ?: "",
                                judul = doc.getString("judul") ?: "",
                                kategori = doc.get("kategori") as? List<String> ?: listOf(),
                                user_id = doc.getString("user_id") ?: ""
                            )
                        }
                    )
                    return@addSnapshotListener
                }
            }
    }

    fun getUserCeritabyKategori(
        kategori: String,
        onSuccess: (List<CeritaModel>) -> Unit,
        onFailed: (Exception) -> Unit
    ) {
        firestore
            .collection("cerita")
            .whereArrayContains("kategori", kategori)
            .whereEqualTo("user_id", auth.currentUser?.uid ?: "")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                    return@addSnapshotListener
                }
                value?.let {
                    onSuccess(
                        it.documents.map { doc ->
                            CeritaModel(
                                gambar = doc.getString("gambar") ?: "",
                                penulis = doc.getString("penulis") ?: "",
                                id = doc.getString("id") ?: "",
                                isi = doc.getString("isi") ?: "",
                                judul = doc.getString("judul") ?: "",
                                kategori = doc.get("kategori") as? List<String> ?: listOf(),
                                user_id = doc.getString("user_id") ?: ""
                            )
                        }
                    )
                    return@addSnapshotListener
                }
            }
    }

}