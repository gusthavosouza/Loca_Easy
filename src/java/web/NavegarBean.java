/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@SessionScoped

public class NavegarBean implements Serializable {

    /**
     * Creates a new instance of NavegarBean
     */
    public NavegarBean() {
    }

    public String returnLogin() {
        return "/login.xhtml?faces-redirect=true";
    }
public String toLogin() {
        return "login.xhtml";
    }
    public String returnLocaEasy() {
        System.out.println("Passo por aki");
        return "/seguro/locaeasy.xhtml?faces-redirect=true";

    }
public String toLocaEasy(){
    return "seguro/locaeasy.xhtml";
    
}
    public String returnCadastroFilme() {
        return "/cadastrofilme.xhtml?faces-redirect=true";
        
    }
}
