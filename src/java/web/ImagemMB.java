/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import com.sun.corba.se.impl.ior.ObjectReferenceFactoryImpl;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Gusthavo
 */
@ManagedBean
@SessionScoped
public class ImagemMB {

     private StreamedContent gra;  

    public StreamedContent getGra() {
        return gra;
    }
    /**
     * Creates a new instance of ImagemMB
     */
    public ImagemMB() {
    

        try{
         BufferedImage bufferedImg = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);  
            Graphics2D g2 = bufferedImg.createGraphics();  
            g2.drawString("This is a text", 0, 10);  
            ByteArrayOutputStream os = new ByteArrayOutputStream();  
            ImageIO.write(bufferedImg, "png", os);  
            gra = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");
          
        }catch (Exception e ) {
            e.printStackTrace();
        }
}

    
    public void showImage() {

        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\Gusthavo\\Documents\\NetBeansProjects\\Loca\\web\\WEB-INF\\files\\Semtitulo.jpg"));

            int length = in.available();
            byte[] bytes = new byte[length];
            in.read(bytes);
            in.close();
ServletResponse response = null;
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse req = (HttpServletResponse) response;

            req.setContentType("C:\\Users\\Gusthavo\\Documents\\NetBeansProjects\\Loca\\web\\WEB-INF\\files\\Semtitulo.jpg");
            req.setContentLength(length);
            req.getOutputStream().write(bytes);
            context.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Falha, I/O error");
        }

    }
    
}
