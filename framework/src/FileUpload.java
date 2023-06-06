package etu002087.framework;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUpload  {
    String name_file;
    InputStream fileContent;
    long fileSize;
    String contentType ;
    File upload;
    public void save_file(String directorie)throws IOException {
        File outputFile = new File(directorie);
        InputStream inputStream=get_fileContent();
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
    public void set_contentType(String g){
        contentType=g;
    }
    public String get_contentType(){
        return contentType;
    }
    public void set_fileSize(long g){
        fileSize=g;
    }
    public long get_fileSize(){
        return fileSize;
    }
    public void set_fileContent(InputStream f){
        fileContent=f;
    }
    public InputStream get_fileContent(){
        return fileContent;
    }
    public void set_name_file(String n){
        name_file=n;
    }
    public String get_name_file(){
        return name_file;
    }
    public FileUpload(String namefile,String contenttype,long size,InputStream in){
        set_name_file(namefile);
        set_fileSize(size);
        set_contentType(contenttype);
        set_fileContent(in);
    }
    public FileUpload(){}
}
