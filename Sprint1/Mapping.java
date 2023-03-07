package etu002087.framework;

public class Mapping{
    String className;
    String Method;
    public void setclassName(String m){
        className=m;
    }
    public void setMethos(String m){
        Method=m;
    }
    public String getclassName(){
        return className;
    }
    public String getMethod(){
        return Method;
    }
    public Mapping(String n,String m){
        setclassName(n);
        setMethos(m);
    };
}