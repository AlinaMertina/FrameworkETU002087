package etu002087.framework;
import java.util.HashMap;

public class ModelView{
   String nompage ;
   HashMap<String,Object> Attribu=new HashMap<String,Object>();;
   public void setnompage(String nom){
    nompage=nom;
   }
   public String getnompage(){
    return nompage;
   }
   public void addItem(String n,Object valuer){
            Attribu.put(n,valuer);
   }
   public HashMap<String,Object> getItem(){
    return Attribu;
   }
   public ModelView(String urljsp){
      setnompage(urljsp);
   }
   public ModelView(){}
}