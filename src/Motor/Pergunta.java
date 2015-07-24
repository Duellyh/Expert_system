package Motor;

public class Pergunta {

	/**
	 * Esta definido a Hashtable das perguntas
	 */
	  private String text = null; 
	    private Object respostas = null; 

	    Pergunta(String text, Object respostas) { 
	        this.text = text; 
	        this.respostas = respostas; 
	    } 

	    public String getText() { 
	        return text; 
	    } 

	    public void setText(String text) { 
	        this.text = text; 
	    } 

	    public Object getRespostas() { 
	        return respostas; 
	    } 

	    public void setRespostas(Object respostas) { 
	        this.respostas = respostas; 
	    } 
	

}
