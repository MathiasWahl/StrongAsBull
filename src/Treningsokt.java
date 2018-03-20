import java.sql.*;
import java.sql.Date;
import java.util.*;


public class Treningsokt {

	private static int treningsoktIDCounter = 1;
    private int treningsoktID;
    private Date dato;
    private Time tidspunkt;
    private int varighet;
    private int form;
    private int prestasjon;
    private String notat;
    private int treningssenterID;

    
    public Treningsokt(Date dato, Time tidspunkt, int varighet, int form,
    		int prestasjon, String notat) {
    	this.treningsoktID = treningsoktIDCounter;
    	this.dato = dato;
    	this.tidspunkt = tidspunkt;
    	this.varighet = varighet;
    	this.form = form;
    	this.prestasjon = prestasjon;
    	this.notat = notat;
    	treningsoktIDCounter++;
    	
    }

}

