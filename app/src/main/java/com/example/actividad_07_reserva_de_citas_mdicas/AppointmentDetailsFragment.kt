package com.example.actividad_07_reserva_de_citas_mdicas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

/*
Esta clase "AppointmentDetailsFragment" es un Fragmento de una aplicación de reserva de citas médicas.
Su principal función es permitir al usuario seleccionar una fecha y hora para una cita con un médico.

- Inicializa los elementos de la interfaz de usuario como un "DatePicker", un "TimePicker", y "TextViews" para mostrar el nombre
- y la especialidad del doctor seleccionado.
- Usa "ViewModelProvider" para obtener una instancia del "SharedViewModel" que se utiliza para compartir datos entre fragmentos.
- Observa el `LiveData` del médico seleccionado para actualizar los detalles del doctor en la interfaz cuando estos cambien.
- Al pulsar el botón "Siguiente", obtiene la fecha y hora seleccionadas, las guarda en el ViewModel, y navega al siguiente
- fragmento de confirmación de la cita.
*/

class AppointmentDetailsFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var doctorNameTextView: TextView
    private lateinit var doctorSpecialtyTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_appointment_details, container, false)

        datePicker = view.findViewById(R.id.datePicker)
        timePicker = view.findViewById(R.id.timePicker)
        doctorNameTextView = view.findViewById(R.id.doctorNameTextView)
        doctorSpecialtyTextView = view.findViewById(R.id.doctorSpecialtyTextView)

        Locale.setDefault(Locale("es", "MX"))

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.selectedDoctor.observe(viewLifecycleOwner) { doctor ->
            doctorNameTextView.text = doctor.Nombre
            doctorSpecialtyTextView.text = doctor.Especialidad
        }

        view.findViewById<Button>(R.id.buttonNext).setOnClickListener {
            val selectedDate = LocalDate.of(
                datePicker.year, datePicker.month + 1, datePicker.dayOfMonth
            )
            val selectedTime = LocalTime.of(timePicker.hour, timePicker.minute)

            viewModel.setAppointmentDateTime(selectedDate, selectedTime)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, ConfirmationFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
