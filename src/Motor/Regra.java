package Motor;

import java.util.Hashtable;

public class Regra {

	/**
	 * Esta definido a Hashtable das regras
	 */
	 private String name = null; 
	    private Hashtable condicoes = null; 
	    private Hashtable acoes = null; 
	    private boolean ativo = true; 

	    Regra(String name, Hashtable condicoes, Hashtable acoes) { 
	        this.name = name; 
	        this.condicoes = condicoes; 
	        this.acoes = acoes; 
	    } 

	    void setInAtivo() { 
	        ativo = false; 
	    } 

	    public String getName() { 
	        return name; 
	    } 

	    public Hashtable getCondicoes() { 
	        return condicoes; 
	    } 

	    public Hashtable getAcoes() { 
	        return acoes; 
	    } 

	    public boolean isAtivo() { 
	        return ativo; 
	    } 
	
	

	}


