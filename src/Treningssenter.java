import java.sql.*;
import java.util.*;

public class Treningssenter {
    
    private static int treningssenterIDCounter = 1;
    private int treningssenterID; 
    private String navn;
    private int rangering;
    private int storrelse;
    private String sted;


    public Treningssenter(String navn, int rangering, int storrelse, String sted){
        this.treningssenterID = treningssenterIDCounter;
        this.navn = navn;
        this.rangering = rangering;
        this.storrelse = storrelse;
        this.sted = sted;
        treningssenterIDCounter++;
        
        // Create row in database using some method
    }

    public int getTreningssenterID() {
        return this.treningssenterID;
    }
}