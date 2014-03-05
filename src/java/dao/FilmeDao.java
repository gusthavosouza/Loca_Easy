/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;


import java.util.List;
import model.Filmes;

/**
 *
 * @author Gustavo
 */
public interface FilmeDao {
    abstract void save(Filmes film);
    abstract void delete(Filmes film);
    abstract  void update(Filmes film);
    abstract List allFilmes();
    abstract  boolean checkFilmes(Filmes film);
    abstract  boolean checkAlugar(Filmes film);
    abstract void alugar(Filmes film);
    abstract  void devolver(Filmes film);
}
