package org.example.controller;

import org.example.DAO.PersonaDAO;
import org.example.model.Persona;

public class ControllerPersona {

    public PersonaDAO perDao = new PersonaDAO();


    public void getPersonas() {

        System.out.println(perDao.getPersonas());
    }

    public void insertPersona(Persona persona) {
        perDao.insertPersona(persona);
    }

    public void actualizarPersona(int id, String nombre, String apellido) {
        perDao.actualizarPersona(id, nombre, apellido);
    }

    public void eliminarPersona(int id) {
        perDao.eliminarPersona(id);
    }
}
