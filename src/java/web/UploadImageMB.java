/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.Filmes;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@RequestScoped
public class UploadImageMB implements Serializable {

    public UploadImageMB() {
    }

    private String pastaDoUpload;
    private String nomeArquivo;
    private String imagemUrl;

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
    public String getPastaDoUpload() {
        return pastaDoUpload;
    }

    public void setPastaDoUpload(String pastaDoUpload) {
        this.pastaDoUpload = pastaDoUpload;
    }
    private static final int BUFFER_SIZE = 6124;
    

   
    public void uploadImage(FileUploadEvent uploadEvent) {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        
            try {
            File targetFolder = new File("C:\\Users\\Gusthavo\\Documents\\NetBeansProjects\\Loca\\web\\WEB-INF\\files\\");
            InputStream inputStream = uploadEvent.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    uploadEvent.getFile().getFileName()));
            
            pastaDoUpload = String.valueOf(targetFolder);
            nomeArquivo = String.valueOf(uploadEvent.getFile().getFileName());
            imagemUrl = pastaDoUpload +"\\"+ nomeArquivo;
            
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
              FacesMessage msg
                    = new FacesMessage("Descrição do arquivo", "Nome Do arquivo: " + uploadEvent.getFile().getFileName()
                            + "Tamanho do arquivo: " + uploadEvent.getFile().getSize() / 1024 + " Kb content type: "
                            + uploadEvent.getFile().getContentType() + "O upload foi feito com sucesso.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
           
    }
            /**
             File file = new File(context.getRealPath("C:\\Users\\Gusthavo\\Documents\\NetBeansProjects\\Loca\\web\\WEB-INF\\files\\" + uploadEvent.getFile().getFileName()));
       
            FileOutputStream fileOut = new FileOutputStream(file);
            byte[] buff = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = uploadEvent.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buff);
                if (bulk < 0) {

                    break;
                }
                fileOut.write(buff, 0, bulk);
                fileOut.flush();
            }
            fileOut.close();

            inputStream.close();

            FacesMessage msg
                    = new FacesMessage("File Description", "file name: " + uploadEvent.getFile().getFileName()
                            + "file size: " + uploadEvent.getFile().getSize() / 1024 + " Kb content type: "
                            + uploadEvent.getFile().getContentType() + "The file was uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            e.printStackTrace();

            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "The files were not uploaded!", "");

            FacesContext.getCurrentInstance().addMessage(null, error);
        }
*/
    
}
