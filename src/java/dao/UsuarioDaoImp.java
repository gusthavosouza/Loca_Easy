/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.List;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import util.HibernateUtil;
import model.Usuarios;
import web.LoginMB;

/**
 *
 * @author Gustavo
 */
public class UsuarioDaoImp implements UsuarioDao {
    @Override
    public void salvar(Usuarios user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
           session.beginTransaction();
           session.save(user);
           session.getTransaction().commit();
           session.close();
           
        } catch(Exception e ){
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(Usuarios user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean checkUsuario(Usuarios usuario) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Usuarios u where u.email = :pEmail and u.senha = :pSenha");
        q.setParameter("pEmail", usuario.getEmail());
        q.setParameter("pSenha", usuario.getSenha());
        boolean okUser = !q.list().isEmpty();
        session.close();
        return okUser;

    }

   

    @Override
    public boolean checkEmail(Usuarios usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       org.hibernate.Transaction tx = session.getTransaction();
       Query q = session.createQuery("from Usuarios u where u.email = :pEmail");
       q.setParameter("pEmail", usuario.getEmail());
       boolean okEmail = q.list().isEmpty();
       session.close();
       return okEmail;
        
        
    }

}
