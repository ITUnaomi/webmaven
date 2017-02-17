package projgit;

import connexion.Connexion;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Personne {

    private String IdPersonne;
    private String Nom;
    private String DateNaissance;
    private String Sexe;

    public void setIdPersonne(String id){
        IdPersonne=id;
    }
    public String getIdPersonne(){
        return IdPersonne;
    }

    public void setNom(String id){
        Nom=id;
    }
    public String getNom(){
        return Nom;
    }

    public void setDateNaissance(String id){
        DateNaissance=id;
    }
    public String getDateNaissance(){
        return DateNaissance;
    }

    public void setSexe(String id){
        Sexe=id;
    }
    public String getSexe(){
        return Sexe;
    }
    public Personne () {
        
    }
    public String toString(){
        return "(default,'"+Nom+"','"+DateNaissance+"','"+Sexe+"')";
    }

    public boolean insert() {
        try {
            Connexion c=new Connexion();
            String r=c.createInsertStatement("Personne",this);
            c.ConnectionDB();
            c.Insert(r);
            c.fermer();
            return true;
        } catch (Exception ex) {
           return false;
        }
        
    }
    public void sayHello(){
        System.out.println("Hello Mr Naina");
    }
     public void sayGoodBye(){
        System.out.println("Good Bye!");
    }
    
}