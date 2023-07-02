package etu002087.framework;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelView{
   String nompage ;
   HashMap<String,Object> Attribu=new HashMap<String,Object>();
   HashMap<String,Object> session=new HashMap<String,Object>();
   List<String> nomSessionDelete= new ArrayList<String>() ;
   Boolean invalidateSession=false;
   public void setInvalidateSession(Boolean f){
      invalidateSession=f;
   }
   public Boolean getsetInvalidateSession(){
      return invalidateSession;
   }
   public void  setDeleteSession(String i){
      nomSessionDelete.add(i);
   }
   public List<String> getDeleteSession(){
      return nomSessionDelete;
   }
   Boolean isjson=false;
   public void setIsjson(Boolean t){
      isjson=t;
   }
   public Boolean getIsjson(){
      return isjson;
   }
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