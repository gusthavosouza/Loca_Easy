/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import javax.faces.model.ListDataModel;
import model.Filmes;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Gusthavo
 */
public class FilmesDataModel extends ListDataModel implements SelectableDataModel {

    public FilmesDataModel() {
    }

    public FilmesDataModel(List list) {
        super(list);
    }

    public Object getRowKey(Filmes film) {

        return film.getTitulo();

    }

    @Override
    public Filmes getRowData(String film) {
        List list = (List) getWrappedData();
        return null;
    }

    @Override
    public Object getRowKey(Object t) {

        return t;
    }
}
