package etu002087.framework.model;

import etu002087.framework.Urlannotation;
import etu002087.framework.ModelView;

import javax.management.modelmbean.ModelMBean;
import etu002087.framework.Controleur;
import etu002087.framework.Set_value_jspannotation;
import etu002087.framework.FileUpload;
import etu002087.framework.Scopeannotation;
import etu002087.framework.Authannotation;
import etu002087.framework.Sessionannotation;
import java.util.HashMap;



@Scopeannotation(indication="singleton")
public class Login { //Identification du membre et ajout du profile dans un session
    String profile;
    HashMap<String,Object> session;
    public void setSession(HashMap<String,Object> s){
        session=s;
    }
    public HashMap<String,Object> getSession(){
        return session;
    }
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
            model.addsession("isconnected", true);
        }else{
            model.addsession("isconnected", false);
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

    @Urlannotation(index = "sansprofile",nomparametre = {})
    @Authannotation(profil = "")
    public ModelView deleteUsertest(){
        ModelView model = new ModelView();
        model.setnompage("DeleteUser.jsp");
        return model;
    }

    @Urlannotation(index = "sessionmodif",nomparametre = {})
    @Sessionannotation()
    @Authannotation(profil = "")
    public ModelView Usesession(){
        getSession().put("profil","User");
        ModelView model = new ModelView();
        model.setnompage("Accuille.jsp");
        return model;
    }
    
}