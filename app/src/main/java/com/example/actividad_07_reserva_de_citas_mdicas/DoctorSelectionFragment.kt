package com.example.actividad_07_reserva_de_citas_mdicas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/*
La clase "DoctorSelectionFragment" es un Fragmento que muestra una lista de doctores para que el usuario seleccione uno para su cita médica.

- Define una lista de doctores (doctorList) con diferentes nombres, especialidades y disponibilidades.
- Utiliza un "RecyclerView" para mostrar la lista de doctores en un formato de lista vertical, utilizando un "LinearLayoutManager".
- Asigna un adaptador (DoctorAdapter) para vincular los datos de los doctores con las vistas de cada ítem en el "RecyclerView".

  - Cuando un doctor es seleccionado (al hacer clic en un ítem), el doctor se guarda en el "SharedViewModel" a través de "setSelectedDoctor()".
  - Después de seleccionar un doctor, el fragmento actual es reemplazado por "AppointmentDetailsFragment",
  - y se añade la transacción a la pila de retroceso (BackStack).

- En el método "onViewCreated", se inicializa el "SharedViewModel", que permite compartir los datos del doctor seleccionado con otros
- fragmentos de la aplicación.
*/

class DoctorSelectionFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var doctorList: List<Doctor>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_doctor_selection, container, false)

        doctorList = listOf(
            Doctor("Dr. Juan Pérez", "Cardiología", "Disponible"),
            Doctor("Dra. Ana Gómez", "Pediatría", "No Disponible"),
            Doctor("Dr. Luis Martínez", "Dermatología", "Disponible"),
            Doctor("Dr. Roberto Sánchez", "Neurología", "Disponible"),
            Doctor("Dra. María Rodríguez", "Ginecología", "No Disponible"),
            Doctor("Dr. Carlos Hernández", "Oncología", "Disponible"),
            Doctor("Dra. Laura Fernández", "Oftalmología", "Disponible"),
            Doctor("Dr. Pedro Morales", "Psiquiatría", "No Disponible"),
            Doctor("Dra. Sofía López", "Traumatología", "Disponible"),
            Doctor("Dr. Andrés García", "Urología", "No Disponible"),
            Doctor("Dra. Mónica Ruiz", "Endocrinología", "Disponible"),
            Doctor("Dr. Javier Torres", "Reumatología", "Disponible"),
            Doctor("Dra. Verónica Castro", "Neumología", "No Disponible")

        )

        recyclerView = view.findViewById(R.id.recyclerViewDoctors)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = DoctorAdapter(doctorList) { doctor ->
            viewModel.setSelectedDoctor(doctor)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, AppointmentDetailsFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
}
