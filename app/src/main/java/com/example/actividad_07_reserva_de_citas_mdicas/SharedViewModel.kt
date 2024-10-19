package com.example.actividad_07_reserva_de_citas_mdicas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime

/*
La clase "SharedViewModel" es un ViewModel que se utiliza para compartir datos entre diferentes fragmentos de la aplicación.

- Utiliza "MutableLiveData" y "LiveData" para almacenar y visualizar datos:

  - "_selectedDoctor" es un "MutableLiveData" que guarda el doctor seleccionado.
  - "_appointmentDate" es un "MutableLiveData" que almacena la fecha de la cita seleccionada.
  - "_appointmentTime" es un "MutableLiveData" que guarda la hora de la cita.

- Los métodos "setSelectedDoctor(doctor: Doctor)" y "setAppointmentDateTime(date: LocalDate, time: LocalTime)"
- permiten actualizar el doctor seleccionado, así como la fecha y hora de la cita, respectivamente.

Este ViewModel permite compartir estos datos entre los fragmentos (vistas) de la aplicación de forma reactiva y centralizada.
*/

class SharedViewModel : ViewModel()
{
    private val _selectedDoctor = MutableLiveData<Doctor>()
    val selectedDoctor: LiveData<Doctor> = _selectedDoctor

    private val _appointmentDate = MutableLiveData<LocalDate>()
    val appointmentDate: LiveData<LocalDate> = _appointmentDate

    private val _appointmentTime = MutableLiveData<LocalTime>()
    val appointmentTime: LiveData<LocalTime> = _appointmentTime

    fun setSelectedDoctor(doctor: Doctor) {
        _selectedDoctor.value = doctor
    }

    fun setAppointmentDateTime(date: LocalDate, time: LocalTime) {
        _appointmentDate.value = date
        _appointmentTime.value = time
    }
}
