package etu002087.framework;
import java.util.HashMap;

public class Controleur{
    HashMap<String,Object> post;
    HashMap<String,Object> get;
    public void set_get(HashMap<String,Object> g){
        get=g;
    }
    public void set_post(HashMap<String,Object> p){
        post=p;
    }
    public HashMap<String,Object> post(){
       return post;
    }
    public HashMap<String,Object> get(){
        return get;
    }
    public Controleur(HashMap<String,Object> g){
        set_get(g);
    }
    public Controleur(){}
    
} 
