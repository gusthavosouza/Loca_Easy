/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.FilmeDaoImp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.Filmes;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import util.FilmesDataModel;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@SessionScoped
public class FilmeMB {

    private DefaultStreamedContent graphicText;

    /**
     * Creates a new instance of FilmeMB
     */
    public FilmeMB() {
        modelList = getListaFilme();
        filmeDataModel = new FilmesDataModel(modelList);

    }

    private Filmes filme = new Filmes();
    private FilmesDataModel filmeDataModel;
    private List listaFilme;
    private List modelList;
    private UploadImageMB image;
    private String pastaDoUpload;
    private String nomeArquivo;
    private String imagemUrl;
    private List imagensList;
    private StreamedContent gra;

    public StreamedContent getGra() {
        return gra;
    }

    public List getImagensList() {
        return imagensList;
    }

    public void setImagensList(List imagensList) {
        this.imagensList = imagensList;
    }

    private NavegarBean navegarBean = new NavegarBean();

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public FilmesDataModel getFilmeDataModel() {
        return filmeDataModel;
    }

    public void setFilmeDataModel(FilmesDataModel filmeDataModel) {
        this.filmeDataModel = filmeDataModel;
    }

    public List getListaFilme() {
        FilmeDaoImp dao = new FilmeDaoImp();
        listaFilme = dao.allFilmes();
        return listaFilme;
    }

    /**
     * BufferedImage img = ImageIO.read(new
     * ByteArrayInputStream(filme.getImagemurl())); File file = new
     * File("nome"); try{ boolean write = ImageIO.write(img, "png", file);
     * FileInputStream fi = new FileInputStream(file); StreamedContent imagem =
     * new DefaultStreamedContent(fi); } catch (Exception e ){
     * e.printStackTrace(); }
     */
    public void setListaFilme(List listaFilme) {
        this.listaFilme = listaFilme;
    }

    public List getModelList() {
        return modelList;
    }

    public void setModelList(List modelList) {
        this.modelList = modelList;
    }

    public Filmes getFilme() {
        return filme;
    }

    public void setFilme(Filmes filme) {
        this.filme = filme;
    }

    public void reList() {

        this.modelList = getListaFilme();
        this.filmeDataModel = new FilmesDataModel(this.modelList);

    }

    public void clear() {
        filme = new Filmes();
    }

    public String salvar() {
        FilmeDaoImp check = new FilmeDaoImp();
        boolean checkFilme = check.checkFilmes(filme);
        if (checkFilme == true) {
            filme.setImagemurl(getImagemUrl());
            FilmeDaoImp filmDao = new FilmeDaoImp();
            filmDao.save(filme);
            menssagemControl("Salvo com sucesso!");
            reList();
            clear();
            return navegarBean.returnLocaEasy();
        } else {
            menssagemControl("o Titulo ja foi cadastrado");
        }

        return "";
    }

    public void menssagemControl(String menssagem) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(menssagem));
    }

    public String uploadImage(FileUploadEvent uploadEvent) {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        try {
            File targetFolder = new File("C:\\Users\\Gusthavo\\Documents\\NetBeansProjects\\Loca\\web\\seguro\\imagens\\");
            InputStream inputStream = uploadEvent.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    uploadEvent.getFile().getFileName()));

            pastaDoUpload = String.valueOf(targetFolder);
            nomeArquivo = String.valueOf(uploadEvent.getFile().getFileName());
            imagemUrl = "imagens" + "\\" + nomeArquivo;
            System.out.println(imagemUrl);
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
        return imagemUrl;
    }

    public void selectFilme(SelectEvent event) {

        FacesMessage msg = new FacesMessage("Noticia Selecionada", this.filme.getTitulo());

        FacesContext.getCurrentInstance().addMessage(null, msg);

        this.filme = ((Filmes) event.getObject());

    }

    public void unselectFilme(UnselectEvent event) {
        this.filme.setTitulo("");

    }

    public void alugar() {
        filme.setDisponibilidade("Indisponivel");
        FilmeDaoImp fil = new FilmeDaoImp();
        fil.alugar(filme);
        reList();

    }
    
    public void devolverFilme(){
        filme.setDisponibilidade("Disponivel");
        FilmeDaoImp fil = new FilmeDaoImp();
        fil.devolver(filme);
        reList();
    }
    
public String enabledLink(String texto){
    if(texto.equals("Indisponivel")){
        return "false"; 
    } else{
        return "true";
    }
    
}
    public String disabledLink(String texto) {

        if (texto.equals("Indisponivel")) {

            return "true";
        } else {
            return "false";
        }
    }

}

/**
 * FilmeDaoImp fil = new FilmeDaoImp(); imagensList = fil.showAllImage();
 * for(int x = 0; x < imagensList.size(); x++){ String url =
 * String.valueOf(imagensList.get(x));
 */
