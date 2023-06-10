package etu002087.framework.model;

import etu002087.framework.Urlannotation;
import etu002087.framework.ModelView;

import javax.management.modelmbean.ModelMBean;
import etu002087.framework.Controleur;
import etu002087.framework.Set_value_jspannotation;
import etu002087.framework.FileUpload;
import etu002087.framework.Scopeannotation;
import etu002087.framework.Authannotation;


@Scopeannotation(indication="singleton")
public class Login { //Identification du membre et ajout du profile dans un session
    String profile;
    @Set_value_jspannotation(nom_atribue="profile")
    public void setprofile(String p){
        profile=p;
    }
    public String getprofile(){
        return profile;
    }
    @Urlannotation(index = "login",nomparametre={})
    public etu002087.framework.ModelView login(){
        ModelView model = new ModelView();
        model.setnompage("Login.jsp");
        return model;
    }
    @Urlannotation(index = "authentification",nomparametre={"profile","motpasse"})
    public etu002087.framework.ModelView  authentification(String profile,String motpasse){
        ModelView model = new ModelView();
        model.setnompage("Accuille.jsp");
        if(motpasse.compareTo("Mertina")==0){
            model.addsession("profil", profile);
        }
        return model;
    }
    @Urlannotation(index = "delete",nomparametre = {})
    @Authannotation(profil = "Admin")
    public ModelView deleteUser(){
        ModelView model = new ModelView();
        model.setnompage("DeleteUser.jsp");
        return model;
    }
    
}