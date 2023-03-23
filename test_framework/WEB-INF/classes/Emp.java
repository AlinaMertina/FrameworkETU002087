package etu002087.framework.model;

import etu002087.framework.Urlannotation;
public class Emp {

    public Emp(){}

    @Urlannotation(index = "findAll")
    public String findAll(){
        return "/index.jsp";
    }
    
}  
