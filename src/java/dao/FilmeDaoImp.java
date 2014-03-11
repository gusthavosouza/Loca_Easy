/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import model.Filmes;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Gustavo
 */
public class FilmeDaoImp implements FilmeDao {

    @Override
    public void save(Filmes film) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(film);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Filmes film) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Filmes film) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List allFilmes() {
        List listaFilmes = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("select tn from Filmes tn");
            listaFilmes = (List) q.list();

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaFilmes;

    }

    @Override
    public boolean checkFilmes(Filmes film) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Filmes f where f.titulo = :pTitulo");
        q.setParameter("pTitulo", film.getTitulo());
        boolean okFilme = q.list().isEmpty();
        session.close();
        return okFilme;

    }

    public List showAllImage() {
        List imagensList;
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createSQLQuery("select imagemurl from filmes");
        imagensList = (List) q.list();
        session.close();
        return imagensList;
    }

    @Override
    public boolean checkAlugar(Filmes film) {
      /*  Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.merge(film);
        session.getTransaction().commit();
        session.close();*/

         Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Filmes f where f.disponibilidade = :pDisponibilidade");
        q.setParameter("pDisponibilidade", film.getDisponibilidade());
        boolean okFilme = q.list().isEmpty();
        session.close();
        return okFilme;
    }

    @Override
    public void alugar(Filmes film) {
   Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.merge(film);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void devolver(Filmes film) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.merge(film);
        session.getTransaction().commit();
        session.close();
    }

}
