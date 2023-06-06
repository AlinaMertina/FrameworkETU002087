package etu002087.framework.model;

import etu002087.framework.Urlannotation;
import etu002087.framework.ModelView;
import etu002087.framework.Controleur;
import etu002087.framework.Set_value_jspannotation;
import etu002087.framework.FileUpload;
import etu002087.framework.Scopeannotation;

import java.util.Vector;
import java.io.IOException;
import java.util.Date;
@Scopeannotation(indication="singleton")
public class Singleton_test {
    String  nom;
    // Date date_naissance;
    Integer  age;
    Double salaire;
    Date date_naissance;
    Float test_Float;
    FileUpload file;
    String[] liste;
    Integer nombre_appel=0;
    public Integer get_nbr_appel(){
        return nombre_appel;
    }
    public void increment(){
        nombre_appel=nombre_appel+1;
    }
    @Set_value_jspannotation(nom_atribue="nomemp")
    public void set_liste(String[] l){
        liste=l;
    }
    // @Set_value_jspannotation(nom_atribue="file")
    // public void set_file(FileUpload f){
    //     file=f;
    // }
    public FileUpload get_file(){
        return file;
    }

    @Set_value_jspannotation(nom_atribue="nom")
    public void setnom(String s){
        nom=s;
    }   
    @Set_value_jspannotation(nom_atribue="age")
    public void setage(Integer a){
        age=a;
    }  
    @Set_value_jspannotation(nom_atribue="salaire")
    public void setsalaire(Double d){
        salaire=d;
    } 
    // @Set_value_jspannotation(nom_atribue="test_Float")
    // public void setdate_naissance(Date d){
    //     date_naissance=d;
    // } 
    @Set_value_jspannotation(nom_atribue="test_Float")
    public void settest_Float(Float d){
        test_Float=d;
    } 

    public Double getsalaire(){
        return salaire;
    }
    public Integer getage(){
        return age;
    }
    public String getnom(){
        return nom;
    }
    public Date getdate_naissance(){
        return date_naissance;
    }
    public Float gettest_Float(){
        return test_Float;
    }
    // @Set_value_jspannotation(nom_atribue="date_naissance")
    // public void setdate_naissance(Date d){
    //     date_naissance=d;
    // }     

    public Singleton_test(){
        increment();
    }

    @Urlannotation(index = "findAll",nomparametre={})
    public ModelView findAll(){
        ModelView model = new ModelView("/index.jsp");
        Vector<Object[]> tabeemp = new Vector<Object[]>();
        tabeemp.add(new Object[]{"Mertina","20"});
        tabeemp.add(new Object[]{"Dylan","20"});
        tabeemp.add(new Object[]{"Alain","20"});
        tabeemp.add(new Object[]{"Tsiki","20"});
        tabeemp.add(new Object[]{"Aro","20"});
        model.addItem("nomemp", tabeemp);
        return model;
    }
    @Urlannotation(index = "insert",nomparametre={})
    public ModelView insert(){
        ModelView model = new ModelView("/Formulaire_Employer.jsp");
        return model;
    }


    @Urlannotation(index = "save",nomparametre={})
    public ModelView save(){
        ModelView model = new ModelView("/afficher.jsp");
        model.addItem("test_Float", gettest_Float());
        return model ;
       
    }
    @Urlannotation(index = "fonction",nomparametre={"nom_adulte","age_adulte"})
    public ModelView fonction_parametre(String nom_adulte,String age){
        ModelView model = new ModelView("/afficher.jsp");
        model.addItem("nom_adulte", nom_adulte);
        model.addItem("age_adulte", age);
        try {
            get_file().save_file("/home/mertina/Bureau/L2/Web_Dynamique/RepertoireDetravailler/UtilisationframeWork/test_framework/read.sc");   
        } catch (IOException e) {
            System.out.println(e);
        }
        return model ;
    }
    @Urlannotation(index = "tabeemp_singleton",nomparametre={"nomemp"})
    public ModelView test_table_form(String[] nomemp){
        increment();
        ModelView model = new ModelView("/liste_emp.jsp");
        model.addItem("nomemp", nomemp);
        model.addItem("nbr", get_nbr_appel());
        return model;
    }


}
