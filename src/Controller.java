import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import jdk.internal.org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by 47419119l on 19/01/16.
 */
public class Controller {
    Socis sociActu;
    Llibre llibreActu;
    PrestecDao prestec = new PrestecDao();
    SocioDao socioDao = new SocioDao();
    LlibreDao llibre = new LlibreDao();
    @FXML
    TextField nomsoci, cognomsoci, direcciosoci, telefonsoci,
                edatsoci,titolllibre,editoriallibre,ediciollibre,
                exemplarsllibre,paginesllibre,idSoci,idLlibre,
                buscarSociPrestec,buscarPrestecLlibre,
                buscarTitols,buscarNom,buscarCognom, buscarAutor;
    @FXML
    ListView llistat,llistatLlibres,llistatPrestec;
    @FXML
    AnchorPane pane;
    List<Socis>socios;List<Llibre>llibres;List<Prestec>prestecs;
    @FXML
    DatePicker dataIniciPrestec,dataFinalPrestec;
    @FXML
    CheckBox checkFiData;

    /**
     * Metode que s'executa al iniciar la aplicació
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public void initialize() throws IOException, SAXException, ParserConfigurationException {
        pane.getStyleClass().add("pane");
        iniciarSocis();
    }

    /*
    ------------------------------------METODES PER GESTIONAR ELS SOCIS--------------------------------------------
     */

    /**
     * Metode per possar tots els socis que es troban a la bbdd al ListView
     */
    public void iniciarSocis()  {
        ObservableList<String> items = FXCollections.observableArrayList();
        socios = socioDao.obtenListaSoci();
        for(int i = 0; i< socios.size();i++){
            items.add(socios.get(i).getId()+" - "+socios.get(i).getNom()+" "+socios.get(i).getCognom());
        }
        llistat.setItems(items);
    }
    /**
     * Metode per mostrar la informació del soci sel·leccionat
     * @param event
     */
    public void SociSelect(Event event) {
        llistat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sociActu = socios.get(llistat.getSelectionModel().getSelectedIndex());
                nomsoci.setText(sociActu.getNom());
                cognomsoci.setText(sociActu.getCognom());
                edatsoci.setText(String.valueOf(sociActu.getEdat()));
                direcciosoci.setText(sociActu.getDireccio());
                telefonsoci.setText(sociActu.getTelefon());
            }
        });

    }

    /**
     * Metode per donar d'alta un soci desde la interfaç gràfica
     * @param actionEvent
     */
    public void donarAltadaSoci(ActionEvent actionEvent) {
        Socis s = new Socis(nomsoci.getText(), cognomsoci.getText(),Long.parseLong(edatsoci.getText()), direcciosoci.getText(), telefonsoci.getText());
        nomsoci.setText("");cognomsoci.setText("");edatsoci.setText("");direcciosoci.setText("");telefonsoci.setText("");
        socioDao.guardaSoci(s);
        iniciarSocis();

    }

    /**
     * Metode per donar d'alta un llibre desde la interfaç gràfica
     * @param actionEvent
     */
    public void donarAltadaLlibre(ActionEvent actionEvent) {
        Llibre l = new Llibre(titolllibre.getText(),Integer.parseInt(exemplarsllibre.getText()),editoriallibre.getText(),Integer.parseInt(paginesllibre.getText()),Integer.parseInt(ediciollibre.getText()));
        titolllibre.setText("");exemplarsllibre.setText("0");editoriallibre.setText("");paginesllibre.setText("0");ediciollibre.setText("2000");
        llibre.guardaLlibre(l);
        iniciarLlibres();
    }

    /**
     * Metode per eliminar Soci des de la interfaç gràfica
     * @param actionEvent
     */
    public void eliminarSoci(ActionEvent actionEvent) {
        socioDao.eliminaSoci(socios.get(llistat.getSelectionModel().getSelectedIndex()));
        nomsoci.setText("");cognomsoci.setText("");edatsoci.setText("");direcciosoci.setText("");telefonsoci.setText("");
        iniciarSocis();
    }

    /**
     * Metode per extreure el soci sel·lecionat del nostre Listview
     * @param event
     */
    public void escollirSoci(Event event) {

        int index =llistat.getSelectionModel().getSelectedIndex();
        sociActu =socios.get(index);
        cognomsoci.setText(socios.get(index).getCognom());
        nomsoci.setText(socios.get(index).getNom());
        telefonsoci.setText(socios.get(index).getTelefon());
        direcciosoci.setText(socios.get(index).getDireccio());
        long edat = socios.get(index).getEdat();
        edatsoci.setText(String.valueOf(edat));

    }

    /**
     * Metode per modificar el Soci sel·lecionat
     * @param actionEvent
     */
    public void modificarSoci(ActionEvent actionEvent) {
        sociActu.setNom(nomsoci.getText());
        sociActu.setCognom(cognomsoci.getText());
        sociActu.setDireccio(direcciosoci.getText());
        sociActu.setEdat(Integer.parseInt(edatsoci.getText()));
        sociActu.setTelefon(telefonsoci.getText());
        nomsoci.setText("");cognomsoci.setText("");edatsoci.setText("");direcciosoci.setText("");telefonsoci.setText("");
        titolllibre.setText("");exemplarsllibre.setText("0");editoriallibre.setText("");paginesllibre.setText("0");ediciollibre.setText("2000");
        socioDao.actualizaSoci(sociActu);
        iniciarSocis();
    }


    /**
     * Mostra tots els socis de la BBDD.
     * @param actionEvent
     */
    public void mostrarTotsSocis(ActionEvent actionEvent) {
        iniciarSocis();
    }

    /**
     * Busca un soci per el seu nom i cognom depenent dels camps que estiguin omplerts.
     * @param actionEvent
     */
    public void buscarSoci(ActionEvent actionEvent) {
        socios.clear();
        if (!buscarCognom.getText().equals("") && !buscarNom.getText().equals("")){
            socios = socioDao.consultaSociPerNomCognom(buscarNom.getText(), buscarCognom.getText());
        }
        if (buscarNom.getText().equals("")){
            socios = socioDao.consultaSociPerCognom(buscarCognom.getText());
        }
        if (buscarCognom.getText().equals("")){
            socios = socioDao.consultaSociPerNom(buscarNom.getText());
        }
            buscarNom.setText("");
            buscarCognom.setText("");
            ObservableList<String> items = FXCollections.observableArrayList();
            for (int i = 0; i < socios.size(); i++) {
                items.add(socios.get(i).getId() + " - " + socios.get(i).getNom() + " " + socios.get(i).getCognom());
            }
            llistat.setItems(items);

    }
/*
--------------------------------METODES PER GESTIONAR ELS LLIBRES-------------------------------------------
 */
    /**
     * Metode per mostrar tots es Llibres que es troben a la bbdd al ListView
     */
    public void iniciarLlibres(){
        ObservableList<String> items = FXCollections.observableArrayList();
        llibres =llibre.obtenListaLlibre();
        for(int i = 0; i< llibres.size();i++){
            items.add (llibres.get(i).getId()+" - "+llibres.get(i).getTitol());
        }
        llistatLlibres.setItems(items);
    }
    /**
     * Metode per modificar el LLibre sel·lecionat
     * @param actionEvent
     */
    public void modificarLlibre(ActionEvent actionEvent) {

        llibreActu.setTitol(titolllibre.getText());
        llibreActu.setEditorial(editoriallibre.getText());
        llibreActu.setAny_edicio(Integer.parseInt(ediciollibre.getText()));
        llibreActu.setNombre_pagines(Integer.parseInt(paginesllibre.getText()));
        llibreActu.setNombre_exemplars(Integer.parseInt(exemplarsllibre.getText()));
        llibre.actualizaLlibre(llibreActu);
    }

    /***
     * Metode per Borrar el llibre sel·lecionat
     * @param actionEvent
     */
    public void borrarLlibre(ActionEvent actionEvent) {
        llibre.eliminaLlibre(llibres.get(llistatLlibres.getSelectionModel().getSelectedIndex()));
        titolllibre.setText("");exemplarsllibre.setText("0");editoriallibre.setText("");paginesllibre.setText("0");ediciollibre.setText("2000");
        iniciarLlibres();
    }

    /**
     * Metode que s'executa quan accedim al pane Llibre
     * @param event
     */
    public void llibrePane(Event event) {
        iniciarLlibres();
    }

    /**
     * Metode per mostrar informació del llibre sel·lecionat.
     * @param event
     */
    public void llibreSelect(Event event) {
        llistatLlibres.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                llibreActu = llibres.get(llistatLlibres.getSelectionModel().getSelectedIndex());
                titolllibre.setText(llibreActu.getTitol());
                editoriallibre.setText(llibreActu.getEditorial());
                ediciollibre.setText(String.valueOf(llibreActu.getAny_edicio()));
                paginesllibre.setText(String.valueOf(llibreActu.getNombre_pagines()));
                exemplarsllibre.setText(String.valueOf(llibreActu.getNombre_exemplars()));
            }
        });
    }

    public void buscarPerTitolAutor(ActionEvent actionEvent) {
        llibres.clear();
        if(!buscarAutor.getText().equals("")&& !buscarTitols.getText().equals("")) {
            llibres = llibre.obtenListaLlibrePerEditorialTitol(buscarAutor.getText(), buscarTitols.getText());
        }
        if(buscarAutor.getText().equals("")){
            llibres = llibre.obtenListaLlibrePerTitol(buscarTitols.getText());
        }
        if(buscarTitols.getText().equals("")){
            llibres = llibre.obtenListaLlibrePerEditorial(buscarAutor.getText());
        }
        buscarAutor.setText("");buscarTitols.setText("");
        ObservableList<String> items = FXCollections.observableArrayList();
        for(int i = 0; i< llibres.size();i++){
            items.add (llibres.get(i).getId()+" - "+llibres.get(i).getTitol());
        }
        llistatLlibres.setItems(items);

    }

    public void mostrarTotsLlibres(ActionEvent actionEvent) {
        iniciarLlibres();
    }
   /*
   ----------------------------------------METDES PER GESTIONAR ELS PRESTECS----------------------------------------
    */
    /**
     * Metode per mostrar tots els Prestec que es troben a la bbdd al listView
     */
    public void iniciarPrestec(){
        ObservableList<String> items = FXCollections.observableArrayList();
        prestec.mostrarLlibresPrestats(items);
        llistatPrestec.setItems(items);
    }

    /**
     * Metode que s'executa quan anem al panel Prestec
     * @param event
     */
    public void PrestecSelected(Event event) {
     iniciarPrestec();

    }


    /**
     * Metode per donar d'alta un prestec des de la interficie gràfica
     * @param actionEvent
     */

    public void altaPrestec(ActionEvent actionEvent) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fechainici = null;
        Date fechafinal = null;
        try {
            fechainici = formatoDelTexto.parse(dataIniciPrestec.getValue().toString());
            fechafinal = formatoDelTexto.parse(dataFinalPrestec.getValue().toString());

        } catch (ParseException ex) {

            ex.printStackTrace();

        }

        Prestec p = new Prestec(Integer.parseInt(idSoci.getText()), Integer.parseInt(idLlibre.getText()), fechainici,fechafinal);
        idLlibre.setText("");
        idSoci.setText("");
        prestec.guardaLlibre(p);
        iniciarPrestec();
    }

    /**
     * Metode per borrar un prestec
     * @param actionEvent
     */
    public void BorrarPrestec(ActionEvent actionEvent) {
    }

    /**
     * Metode per mostrar la informació del prestec sel·lecionat.
     * @param event
     */
    public void llistatPrestec(Event event) {
        llistatPrestec.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {


            }
        });
    }

    public void checkDates(Date dataFinal)
    {
        Date dataInici = new Date ();
        prestecs = prestec.obtenListaPrestec();
        for(int i =0; i < prestecs.size();i++){

            if (dataInici.after( prestecs.get(i).getData_final()))
            {
                ObservableList<String> items = FXCollections.observableArrayList();


            }
        }

    }

    public void dataFi(ActionEvent actionEvent) {
        ObservableList<String> items = FXCollections.observableArrayList();
        Date dataInici = new Date ();
        prestecs = prestec.obtenListaPrestec();
        for(int i =0; i < prestecs.size();i++){

            if (dataInici.after( prestecs.get(i).getData_final()))
            {
                prestec.mostrarSoci((int) prestecs.get(i).getIdSoci());
                items.add( prestec.mostrarSoci((int) prestecs.get(i).getIdSoci()));
            }
        }
        llistatPrestec.setItems(items);
    }

    public void buscarPrestec(ActionEvent actionEvent) {
        ObservableList<String> items = FXCollections.observableArrayList();
        prestec.mostrarSocisPrestats(items,Integer.parseInt(buscarSociPrestec.getText()));
        llistatPrestec.setItems(items);
    }
}

