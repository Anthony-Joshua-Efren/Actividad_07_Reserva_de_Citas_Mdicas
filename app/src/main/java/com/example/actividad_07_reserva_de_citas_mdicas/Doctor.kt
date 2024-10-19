package com.example.actividad_07_reserva_de_citas_mdicas

/*
La clase "Doctor" es un "data class" que representa a un doctor en el sistema de reservas de citas médicas.

- Define tres propiedades:

  - Nombre: el nombre del doctor.
  - Especialidad: la especialidad médica del doctor.
  - Disponibilidad: una cadena que representa los horarios o días en los que el doctor está disponible para citas.

*/

data class Doctor(
    val Nombre: String,
    val Especialidad: String,
    val Disponibilidad: String
)