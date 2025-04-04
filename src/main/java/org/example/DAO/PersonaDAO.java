package org.example.DAO;

import org.example.model.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonaDAO {


    public List<Persona> getPersonas(){
        List<Persona> personas = new ArrayList<>();
        String sql="SELECT * from personas";

        try {
            Connection conn = Conexion.obtenerConexion();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("id_persona");
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");

                personas.add(new Persona(id,cedula,nombre,apellido,telefono,correo) );
            }
        }catch (SQLException e){
            System.out.println("error: " + e.getMessage());
        }
       
        return personas;


    }

    public void insertPersona(Persona persona) {
        String sql = "INSERT INTO personas (cedula,nombre,apellido,telefono,correo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, persona.getCedula());
            pstmt.setString(2, persona.getNombre());
            pstmt.setString(3, persona.getApellido());
            pstmt.setString(4, persona.getTelefono());
            pstmt.setString(5, persona.getCorreo());


            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        persona.setId(rs.getInt(1));
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void actualizarPersona(int id, String nombre, String apellido) {
        String sql = "UPDATE personas SET nombre = ?, apellido = ? WHERE id_persona = ?";

        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setInt(3, id);

            int filasAfectadas = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public boolean eliminarPersona(int id) {
        String sql = "DELETE FROM personas WHERE id_persona = ?";

        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }






}
