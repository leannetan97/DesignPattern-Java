/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dplab12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Tan Lay Yan WIF160058
 */
//abstract class FileSystemComponent {
//
//    private String name;
//    
//    FileSystemComponent(String name){
//        this.name = name;
//    }
//
//    //Chile management operation
//    public void addComponent(FileSystemComponent fsc) {
//        throw new UnsupportedOperationException();
//    }
//
////    public void removeComponent(){
////        throw new UnsupportedOperationException();
////    }
//    public FileSystemComponent getChild(int i) {
//        throw new UnsupportedOperationException();
//    }
//
//    // other related methods
//    public abstract int getComponentSize();
//
////    public void display() {
////        throw new UnsupportedOperationException();
////    }
//
//    public String getName() {
//        return name;
//    }
//}
//
//class FileComponent extends FileSystemComponent {
//    private int size;
//    
//    FileComponent(String name, int size){
//        super(name);
//        this.size = size;
//    }
//    
//    @Override
//    public int getComponentSize() {
//        return size;
//    }
//}
//
//class DirectoryComponent extends FileSystemComponent {
//    ArrayList<FileSystemComponent> directoryList = new ArrayList<>();
//    
//    DirectoryComponent(String name){
//        super(name);
//    }
//    
//    @Override
//    public int getComponentSize() {
//        int size  = 0;
//        Iterator<FileSystemComponent> fileComponent = directoryList.iterator();
//        
//        while(fileComponent.hasNext()){
//            size += fileComponent.next().getComponentSize();
//        }
//        return size;
//    }
//
//    @Override
//    public void addComponent(FileSystemComponent fsc) {
//        directoryList.add(fsc);
//    }
//    
//    @Override
//    public FileSystemComponent getChild(int i) {
//       return directoryList.get(i);
//    }
//    
//}
//
//public class CompositeDemo {
//
//    public static void main(String[] args) {
//        // TODO code application logic here
//        FileSystemComponent mainFolder = new DirectoryComponent("Year2019");
//        FileSystemComponent janSubFolder = new DirectoryComponent("Jan");
//        FileSystemComponent febSubFolder = new DirectoryComponent("Feb");
//        
//        FileSystemComponent janFile1 = new FileComponent("Jan1DataFile.txt",1000);
//        FileSystemComponent janFile2 = new FileComponent("Jan2DataFile.txt",2000);
//        FileSystemComponent febFile1 = new FileComponent("Feb1DataFile.txt",3000);
//        FileSystemComponent febFile2 = new FileComponent("Feb2DataFile.txt",4000);
//        
//        mainFolder.addComponent(janSubFolder);
//        mainFolder.addComponent(febSubFolder);
//        janSubFolder.addComponent(janFile1);
//        janSubFolder.addComponent(janFile2);
//        febSubFolder.addComponent(febFile1);
//        febSubFolder.addComponent(febFile2);
//        
//        System.out.println(mainFolder.getName() + " Folder Size =" + mainFolder.getComponentSize() + "kb");
//        System.out.println(janSubFolder.getName() + " Folder Size =" + janSubFolder.getComponentSize() + "kb");
//        System.out.println(febSubFolder.getName() + " Folder Size =" + febSubFolder.getComponentSize() + "kb");
//    }
//
//}


abstract class FileSystemComponent{
    private String name;
    
    FileSystemComponent(String name){
        this.name = name;
    }
    
    public void addComponent(FileSystemComponent component){
        throw new UnsupportedOperationException();
    }
    
    public FileSystemComponent getChild(int i){
        throw new UnsupportedOperationException();
    }
    
    public int getComponentSize(){
        throw new UnsupportedOperationException();
    }
    
    public String getName(){
        return name;
    }
}

class DirectoryComponent extends FileSystemComponent{
    LinkedList<FileSystemComponent> componentList;
    
            
    DirectoryComponent(String name){
        super(name);
        componentList = new LinkedList<>();
        
    }
    
    @Override
    public void addComponent(FileSystemComponent component){
        componentList.add(component);
    }
    
    @Override
    public FileSystemComponent getChild(int i){
        return componentList.get(i);
    }
    
    @Override
    public int  getComponentSize(){
        int size = 0;
        for (int i = 0; i < componentList.size(); i++) {
            size += getChild(i). getComponentSize();
        }
        return size;
    }
}

class FileComponent extends FileSystemComponent{
    private int size;
    
    FileComponent(String name, int size){
        super(name);
        this.size = size;
    }
    
    @Override
    public int getComponentSize(){
        return size;
    }
}

public class CompositeDemo{
    public static void main(String[] args) {
        FileSystemComponent mainFolder = new DirectoryComponent("Year2019");
        FileSystemComponent subFolder1 = new DirectoryComponent("Jan");
        FileSystemComponent subFolder2 = new DirectoryComponent("Feb");
        
        FileSystemComponent file1Jan = new FileComponent("Jan1DataFile.txt",1000);
        FileSystemComponent file2Jan = new FileComponent("Jan2DataFile.txt",2000);
        FileSystemComponent file1Feb = new FileComponent("Feb1DataFile.txt ",3000);
        FileSystemComponent file2Feb = new FileComponent("Feb2DataFile.txt ",4000);
        
        subFolder1.addComponent(file1Jan);
        subFolder1.addComponent(file2Jan);
        subFolder2.addComponent(file1Feb);
        subFolder2.addComponent(file2Feb);
        
        mainFolder.addComponent(subFolder1);
        mainFolder.addComponent(subFolder2);
        
        System.out.println(mainFolder.getName() + " Folder size= " + mainFolder.getComponentSize() + "kb");
        System.out.println(subFolder1.getName() + " Folder size= " + subFolder1.getComponentSize() + "kb");
        System.out.println(file1Jan.getName() + " Folder size= " + file1Jan.getComponentSize() + "kb");
    }
}