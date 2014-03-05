/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Usuarios;

/**
 *
 * @author Gustavo
 */
public interface UsuarioDao {

    abstract void salvar(Usuarios user);

    abstract void excluir(Usuarios user);

    abstract boolean checkUsuario(Usuarios user);

    abstract boolean checkEmail(Usuarios user);
}
