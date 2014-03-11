package web;



import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImagemServlet extends HttpServlet {

     public void doGet(HttpServletRequest request, HttpServletResponse response) 
          throws ServletException, IOException
     {
          try {
   
               String file = request.getParameter("file");
               BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\Gusthavo\\Documents\\NetBeansProjects\\Loca\\web\\files\\" + file));

  
               byte[] bytes = new byte[in.available()];
               in.read(bytes);
               in.close();

            
               response.getOutputStream().write(bytes);
          } catch (IOException e) {
               e.printStackTrace();
          }
     }
}