package etu002087.framework;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.lang.String;
public class Mapping {
    String className;
    String Method;
   public Mapping(String c,String m){
        className=c;
        Method=m;
   }
   public String getclassName(){
        return className;
   }
   public String getMethod(){
        return Method;
   }

}  
