package com.example.ejercicolistview

data class Persona(private var Nombre: String, private var Apellidos: String, private var Sexo: String, private var Ciclo: String) {
    var nombre: String
        get() = Nombre
        set(value) {
            require(value.isNotBlank()) {"El nombre no puede estar vacío"}
            Nombre = value
        }
    var apellidos: String
        get() = Apellidos
        set(value) {
            require(value.isNotBlank()) {"El nombre no puede estar vacío"}
            Apellidos = value
        }
    var sexo: String
        get() = Sexo
        set(value) {
            require(value.isNotBlank()) {"El nombre no puede estar vacío"}
            require(value == "Hombre" || value == "Mujer") {"Solo puede ser Hombre o Mujer"}
            Sexo = value
        }
    var ciclo: String
        get() = Ciclo
        set(value) {
            require(value.isNotBlank()) {"El nombre no puede estar vacío"}
            require(value == "ASIR" || value == "DAW" || value == "DAM") {"Solo puede ser ASIR, DAW o DAM"}
            Ciclo = value
        }
    init {
        require(Nombre.isNotBlank()) {"no puede estar vacío"}
        require(Apellidos.isNotBlank()) {"no puede estar vacío"}
        require(Sexo.isNotBlank()) {"puede estar vacío"}
        require(Ciclo.isNotBlank()) {"no puede estar vacío"}
        require(Sexo == "Hombre" || Sexo == "Mujer") {"Solo puede ser Hombre o Mujer"}
        require(Ciclo == "ASIR" || Ciclo == "DAW" || Ciclo == "DAM")
    }
    fun safeCopy(Nombre: String, Apellidos: String, Sexo: String, Ciclo: String): Persona {
        return Persona(Nombre, Apellidos, Sexo, Ciclo)
    }
}
