package etu002087.framework.model;

import etu002087.framework.Urlannotation;
import etu002087.framework.ModelView;
import etu002087.framework.Controleur;
import etu002087.framework.Set_value_jspannotation;
import java.util.Vector;
import java.util.Date;
public class Emp extends Controleur{
    String  nom;
    // Date date_naissance;
    Integer  age;
    Double salaire;

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
    public Double getsalaire(){
        return salaire;
    }
    public Integer getage(){
        return age;
    }
    public String getnom(){
        return nom;
    }
    // @Set_value_jspannotation(nom_atribue="date_naissance")
    // public void setdate_naissance(Date d){
    //     date_naissance=d;
    // }     

    public Emp(){
        super();
    }

    @Urlannotation(index = "findAll")
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

    @Urlannotation(index = "save")
    public void save(){
        System.out.println(getnom());
    }
    
}  
