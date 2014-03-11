/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import dao.FilmeDao;
import dao.FilmeDaoImp;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Gusthavo
 */
@ManagedBean (name = "imageMB")
@SessionScoped
public class ImagemMB {

    private String file ;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }


    
    /**
     * Creates a new instance of ImagemMB
     */
    public ImagemMB() {
   
}

    
    public void showImage() {

        FilmeDaoImp dao = new FilmeDaoImp();
        
        String file = "C:\\Users\\Gusthavo\\Documents\\NetBeansProjects\\Loca\\web\\WEB-INF\\files\\Semtitulo.jpg";
        
        
        }
}
        
        
        
        
        
      /**  try {
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

    }*/
    

