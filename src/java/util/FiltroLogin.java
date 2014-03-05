/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.LoginMB;

/**
 *
 * @author Gustavo
 */

@WebFilter ("/seguro/*")
public class FiltroLogin implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         HttpServletRequest req = (HttpServletRequest) request;
         LoginMB loginMB = (LoginMB) req.getSession().getAttribute("loginMB");

        if (loginMB != null && loginMB.isLogado()) {
            // User is logged in, so just continue request.
            chain.doFilter(request, response);
        } else { 
            // User is not logged in, so redirect to index.
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(req.getContextPath() + "/login.xhtml");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
        
    }
    @Override
    public void destroy() {
    }
        
        
        
        
        
        
        /** LoginMB login = (LoginMB) ((HttpServletRequest) request).getSession().getAttribute("login");
       
      
        if (login == null || !login.isLogado()) {
            String context = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(context + "/login.xhtml");
            System.out.println("Entro aki e ficou");
        } 

        else { chain.doFilter(request, response);
         System.out.println("sai de");
        }
        */
    }

    

      /**String context = ((HttpServletRequest) request).getContextPath() + "/login.xhtml";
            boolean logged = login != null && login.isLogado();
            boolean loginRequest = ((HttpServletRequest)request).getRequestURI().equals(context);
            if(logged || loginRequest){
                chain.doFilter(request, response);
            }

        else { 
                ((HttpServletResponse)response).sendRedirect(context);
        }
    }
*/