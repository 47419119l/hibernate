import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

import java.io.Serializable;

/**
 * Created by 47419119l on 19/01/16.
 */
@Entity
public class Llibre implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    String titol;
    long nombre_exemplars;
    String editorial;
    long nombre_pagines;
    long any_edicio;

    public Llibre(){

    }
    public Llibre(String titol, int nombre_exemplars, String editorial, int nombre_pagines, int any_edicio){
        this.titol=titol;
        this.any_edicio= any_edicio;
        this.editorial=editorial;
        this.nombre_exemplars=nombre_exemplars;
        this.nombre_pagines=nombre_pagines;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitol(){
        return this.titol;
    }

    public  void setTitol(String titol){
        this.titol = titol;
    }

    public long getNombre_exemplars(){
        return this.nombre_exemplars;
    }

    public void setNombre_exemplars(long nombre_exemplars){
        this.nombre_exemplars=nombre_exemplars;
    }

    public String getEditorial(){
        return this.editorial;
    }

    public void setEditorial(String editorial){
        this.editorial=editorial;
    }

    public  long getNombre_pagines(){
        return this.nombre_pagines;
    }

    public void setNombre_pagines(long nombre_pagines){
        this.nombre_pagines = nombre_pagines;
    }

    public long getAny_edicio(){
        return this.any_edicio;
    }

    public void setAny_edicio(long any_edicio){
        this.any_edicio=any_edicio;
    }

}
