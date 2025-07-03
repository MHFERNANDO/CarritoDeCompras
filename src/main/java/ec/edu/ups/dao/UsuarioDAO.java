package ec.edu.ups.dao;

import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.modelo.Rol;

import java.util.List;

public interface UsuarioDAO {
    Usuario autenticar(String username, String contrasenia);

    void crear(Usuario usuario);

    List<Usuario> buscarPorUsername(String username);

    Usuario buscarPorUserEspecifico(String username);

    void eliminar(String username);

    public void actualizar(String antiguoUsername, Usuario usuarioNuevo);


    List<Usuario> listarTodos();

    List<Usuario> listarPorRol(Rol rol);

}
