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
    Date date_naissance;
    Float test_Float;

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
    @Set_value_jspannotation(nom_atribue="test_Float")
    public void setdate_naissance(Date d){
        date_naissance=d;
    } 
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
    @Urlannotation(index = "insert")
    public ModelView insert(){
        ModelView model = new ModelView("/Formulaire_Employer.jsp");
        return model;
    }


    @Urlannotation(index = "save")
    public ModelView save(){
        ModelView model = new ModelView("/afficher.jsp");
        model.addItem("test_Float", gettest_Float());
        return model ;
       
    }
    
}  
