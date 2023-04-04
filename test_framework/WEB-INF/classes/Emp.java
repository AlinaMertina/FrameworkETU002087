package etu002087.framework.model;

import etu002087.framework.Urlannotation;
import etu002087.framework.ModelView;

import java.util.Vector;
public class Emp {

    public Emp(){}

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
    
}  
