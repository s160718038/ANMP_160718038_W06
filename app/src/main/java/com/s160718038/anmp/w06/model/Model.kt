package com.s160718038.anmp.w06.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id: String?,
    @SerializedName("student_name") val fullName: String?,
    @SerializedName("birth_of_date") val dateOfBirth: String?,
    @SerializedName("phone") val phoneNumber: String?,
    @SerializedName("photo_url") val photoUrl: String?,
)

data class Car(
    val id: Int?,
    val name: String?,
    val type: String?,
    val country: String?,
    val manufacturer: String?,
    val performance: Performance?,
    val features: List<String>,
    val imageUrl: String?,
) {
    data class Performance(
        val topSpeed: Int?,
        val cruiseSpeed: Int?,
        val range: Int?,
    )
}