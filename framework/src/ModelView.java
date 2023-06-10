package etu002087.framework;
import java.util.HashMap;

public class ModelView{
   String nompage ;
   HashMap<String,Object> Attribu=new HashMap<String,Object>();
   HashMap<String,Object> session=new HashMap<String,Object>();
   public void setnompage(String nom){
    nompage=nom;
   }
   public String getnompage(){
    return nompage;
   }
   public void addsession(String m,Object o){
         session.put(m, o);
   }
   public HashMap<String,Object> getsession(){
      return session;
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