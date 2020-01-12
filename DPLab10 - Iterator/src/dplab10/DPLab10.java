/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dplab10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Tan Lay Yan WIF160058
 */
class Candidate {

    private final String name;
    private final String currentWorkingLocation;
    private final String certificateType;

    public Candidate(String name, String certificateType, String currentWorkingLocation) {
        this.name = name;
        this.currentWorkingLocation = currentWorkingLocation;
        this.certificateType = certificateType;
    }

    public String getCertificateType() {
        return certificateType;
    }
    
    public void display(){
        System.out.println(name + ", "+ certificateType  + ", " + currentWorkingLocation);
    }
}

class AllCandidates {

    ArrayList<Candidate> data;

    public AllCandidates() throws IOException {
        data = new ArrayList<>();
        readFile();
    }
    
    public void readFile() throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader("Candidates.txt"));
        String str;
        while((str = br.readLine())!= null){
            String[] details = str.split(",");
            addCandidate(details[0],details[1],details[2]);
        }
    }

    public void addCandidate(String name,String certificateType,String currentWorkingLocation) {
        data.add(new Candidate(name, certificateType, currentWorkingLocation));
    }

    public Iterator<Candidate> getAllCandidatesIterator() {
        return data.iterator();
    }

    public CertifiedCandidatesIterator getCertifiedCandidatesIterator(String type) throws IOException {
        return new CertifiedCandidatesIterator(data, type);
    }
}

class CertifiedCandidatesIterator implements Iterator {

    private ArrayList<Candidate> data;
    private ArrayList<Candidate> filteredList;
    private int position = 0;

    public CertifiedCandidatesIterator(ArrayList<Candidate> data, String type) throws IOException {
        this.data = data;
        getFilteredList(type);
    }

    @Override
    public boolean hasNext() {
        if (position >= filteredList.size() || filteredList.get(position) == null) {
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        if(filteredList.size()>0){
            Candidate c = filteredList.get(position);
            position += 1;
            return c;
        } else {
            return new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        // do nothing
    }
    
    public void getFilteredList(String type) throws IOException {
        filteredList = new ArrayList<>();
        Iterator<Candidate> fullCandidateListIterator = new AllCandidates().getAllCandidatesIterator();
        while (fullCandidateListIterator.hasNext()) {
            Candidate ppl = fullCandidateListIterator.next();
            if ((ppl.getCertificateType().toLowerCase()).equals(type.toLowerCase())) {
                filteredList.add(ppl);
            }
        }
    }
}


 class SearchManger{
     AllCandidates allC;
    
    public SearchManger(AllCandidates allC){
        this.allC = allC;
    }
    
    public void displayAllCandidates(){
        Iterator allCIterator = allC.getAllCandidatesIterator();
        while(allCIterator.hasNext()){
            Candidate c = (Candidate)allCIterator.next();
            c.display();
        }
    }
    
    public void displayAllCertifiedCandidates(String type) throws IOException{
        CertifiedCandidatesIterator allCCertifiedIterator = allC.getCertifiedCandidatesIterator(type);
        while(allCCertifiedIterator.hasNext()){
            Candidate c = (Candidate)allCCertifiedIterator.next();
            c.display();
        }
    }
    
}

public class DPLab10{
    public static void main(String[] args) throws IOException {
        AllCandidates allC = new AllCandidates();
        SearchManger manager = new SearchManger(allC);
        System.out.println("Printing file content:");
        manager.displayAllCandidates();
        
        Scanner in = new Scanner(System.in); // int scanner
        Scanner in2 = new Scanner(System.in); //String scanner
        
        while(true){
            System.out.print("\nEnter 1 to display all candidates, 2 to display certified candidates, 0 to exit :");
            int option;
            switch(option = in.nextInt()){
                case 1:
                    manager.displayAllCandidates();
                    break;
                case 2:
                    System.out.print("Enter certification type: ");
                    String type = in2.nextLine();
                    manager.displayAllCertifiedCandidates(type);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid options. Enter again");
                    break;
            }
            
            if(option == 0) break;
        }
        
    }
}
