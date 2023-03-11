package etu002087.framework;

import etu002087.framework.*;
@Classannotation(classgroup="sckot")
public class Emp {
    String nom;
    String prenom;
    public Emp(String n ,String p){
        nom=n;
        prenom=p;
    }
    @Methodannotation(name="findAll")
    public String findAll(){
        return nom+" "+prenom;
    }

    public void otherMethod(){
        nom="null";
    }
}
