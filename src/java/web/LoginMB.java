/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.UsuarioDaoImp;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Usuarios;

/**
 *
 * @author Gustavo
 */
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {

    private UsuarioDaoImp user;
    private Usuarios usuario = new Usuarios();
    private String username;
    private boolean logado;

    @ManagedProperty(value = "#{navegarBean}")
    private NavegarBean navegarBean = new NavegarBean();

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public NavegarBean getNavegarBean() {
        return navegarBean;
    }

    public void setNavegarBean(NavegarBean navegarBean) {
        this.navegarBean = navegarBean;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String password;

    public LoginMB() {
    }

    public UsuarioDaoImp getUser() {
        return user;
    }

    public void setUser(UsuarioDaoImp user) {
        this.user = user;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    /**
     * Creates a new instance of ManagedBean
     *
     * @return
     */
    public String login() {

        UsuarioDaoImp userSave = new UsuarioDaoImp();
        boolean check = userSave.checkUsuario(usuario);
        if (check == true) {
            logado = true;
            return navegarBean.returnLocaEasy();
        } else {
            menssagemControl("Login ou senha invalidos");
            //FacesContext context = FacesContext.getCurrentInstance();
            //context.addMessage(null, new FacesMessage("login ou senha invalidas"));
        }
        return navegarBean.toLogin();
    }

    public String logout() {

        logado = false;

        // Set logout message
        menssagemControl("Logout feito com sucesso!");
        return navegarBean.returnLogin();

    }

    public void preCadastro() {
        usuario = new Usuarios();

    }

    public void menssagemControl(String menssagem) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(menssagem));
    }

    public String cadastrar() {
        UsuarioDaoImp userCheck = new UsuarioDaoImp();

        boolean checkEmail = userCheck.checkEmail(usuario);

        if (checkEmail == true) {
            String senha = usuario.getSenha();
            String confSenha = usuario.getConfSenha();
            
            if (!senha.equals(confSenha)) {
                menssagemControl("Senhas não conferem");
            } else {
                UsuarioDaoImp userSave = new UsuarioDaoImp();
                userSave.salvar(usuario);
                //cesMessage msg = new FacesMessage("Cadastrado com sucesso");
                //FacesContext.getCurrentInstance().addMessage(null, msg);
                menssagemControl("Cadastrado com sucesso");
               
                return navegarBean.returnLogin();

            }
        } else {
            menssagemControl("Email já cadastrado");
            //FacesMessage msg = new FacesMessage("Email ja esta em uso");
            //msg.setSeverity(FacesMessage.SEVERITY_INFO);
            //FacesContext.getCurrentInstance().addMessage(null, msg);

        }

        return "";
    }

}
