package com.example.actividad_07_reserva_de_citas_mdicas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*
La clase "DoctorAdapter" es un adaptador personalizado para gestionar una lista de doctores en un "RecyclerView".

- Toma una lista de objetos "Doctor" (doctorList) y una función (onDoctorSelected) que se ejecuta cuando un doctor es seleccionado por el usuario.
- Dentro de la clase "DoctorViewHolder", se definen tres "TextViews" para mostrar el nombre, la especialidad y la disponibilidad del doctor.
- El método "onCreateViewHolder" llena el diseño de un ítem de doctor (item_doctor.xml) y crea un "DoctorViewHolder".
- El método "onBindViewHolder" vincula los datos de un doctor específico a los "TextViews" en cada posición de la lista.
- Al hacer clic en un ítem, se llama a la función "onDoctorSelected", pasando el doctor seleccionado.
- El método "getItemCount" devuelve el tamaño de la lista de doctores.
*/

class DoctorAdapter(
    private val doctorList: List<Doctor>,
    private val onDoctorSelected: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    class DoctorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.textViewDoctorName)
        val specialtyTextView: TextView = view.findViewById(R.id.textViewDoctorSpecialty)
        val availabilityTextView: TextView = view.findViewById(R.id.textViewDoctorAvailability)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctorList[position]
        holder.nameTextView.text = doctor.Nombre
        holder.specialtyTextView.text = doctor.Especialidad
        holder.availabilityTextView.text = doctor.Disponibilidad

        holder.itemView.setOnClickListener {
            onDoctorSelected(doctor)
        }
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }
}
