 package etu002087.framework;
 import java.util.Vector;
 import java.io.File;

 public class Getclass{
    public Getclass(){

    }
    public void getclass(String nompackage,Vector<String>vector,String extension){
        File dossiermere=new File(nompackage);
        File[] liste = dossiermere.listFiles();
        if(liste!=null){
            for(int i=0;i<liste.length;i++){
                if(liste[i].isDirectory()==true){
                    getclass(liste[i].getPath(),vector,extension);
                }
                else if(liste[i].getName().contains(extension)==true){
                    vector.add(liste[i].getPath());
                }
        }
        }
       
    }

 }
