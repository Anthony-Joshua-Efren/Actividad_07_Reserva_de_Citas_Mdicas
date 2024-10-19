package com.example.actividad_07_reserva_de_citas_mdicas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/*
La clase "ConfirmationFragment" es un Fragmento que muestra una pantalla de confirmación para una cita médica previamente seleccionada.

- Utiliza un "SharedViewModel" para acceder a los datos compartidos entre fragmentos, como el nombre del doctor, su especialidad,
- la fecha y la hora de la cita.
- Recupera y muestra en la interfaz de usuario la información de la cita:

  - El nombre y la especialidad del doctor seleccionados.
  - La fecha y la hora elegidas para la cita.

- Incluye un botón "Confirmar", que cuando se pulsa, muestra un mensaje emergente (Toast) que confirma que la cita ha sido registrada con éxito.
*/


class ConfirmationFragment : Fragment()
{
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_confirmation, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val doctorName = viewModel.selectedDoctor.value?.Nombre
        val doctorSpecialty = viewModel.selectedDoctor.value?.Especialidad
        val appointmentDate = viewModel.appointmentDate.value
        val appointmentTime = viewModel.appointmentTime.value

        view.findViewById<TextView>(R.id.textViewDoctorName).text = doctorName
        view.findViewById<TextView>(R.id.textViewDoctorSpecialty).text = doctorSpecialty
        view.findViewById<TextView>(R.id.textViewDate).text = appointmentDate.toString()
        view.findViewById<TextView>(R.id.textViewTime).text = appointmentTime.toString()

        view.findViewById<Button>(R.id.buttonConfirm).setOnClickListener {
            Toast.makeText(requireContext(), "Cita confirmada", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}