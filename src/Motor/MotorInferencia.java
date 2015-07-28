package Motor;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.jdom2.input.SAXBuilder;
import org.jdom2.Element;
import org.jdom2.Document;
import org.jdom2.JDOMException;

import java.util.*;
import java.io.File; 
import java.io.IOException;




public class MotorInferencia { 
    static public int ERROR = 0; 
    static public int MORE = 1; 
    static public int COMPLETE = 2; 
    static public int FAILED = 3; 

    private ArrayList regras = null; 
    private Hashtable perguntas = null; 
    private String var_objetivo = null; 
    private String var_objetivoDescricao = null; 
    private Hashtable base = new Hashtable(); 
    private String answeredregraatributo = null; 
    private String messagemPergunta = null; 
    private ArrayList totalRespostas = null; 
    private String RespostaEscolhida = null; 
    private int status; 
    private String logtext = null; 
    private String personagem = null;

    
    /* Lendo o XML
     * e guarda o hash da variavel objetivo, das regras e das questoes
     *  
     *  *  
     *  */
    public MotorInferencia(File file) throws IOException { 

    	/* parte do J2dom para ler o XML */
        SAXBuilder builder = new SAXBuilder(); 
        Element inputRootElement = null; 

        try { 
            Document inputDocument = builder.build(file); 
            inputRootElement = inputDocument.getRootElement(); 
        } catch (JDOMException e) { 
            e.printStackTrace(); 
        } 

        Iterator children = inputRootElement.getChildren().iterator(); 
        while (children.hasNext()) { 
            Element child = (Element) children.next(); 
            if (child.getName().equals("var_objetivo")) { 
                var_objetivo = child.getChild("atributo").getText(); 
                var_objetivoDescricao = child.getChild("text").getText(); 
            } else if (child.getName().equals("regras")) { 
                regras = readregras(child); 
            } else if (child.getName().equals("perguntas")) { 
                perguntas = readperguntas(child); 
            }
    
            
        } 

        Iterator regraSet = regras.iterator(); 
        while (regraSet.hasNext()) { 
            Regra regra = (Regra) regraSet.next(); 
            Enumeration keys = null; 
            Hashtable condicoes = regra.getCondicoes(); 
           
            keys = condicoes.keys();
           
            while (keys.hasMoreElements()) { 
                base.put(keys.nextElement(), ""); 
            } 
            Hashtable acoes = regra.getCondicoes(); 
            keys = acoes.keys(); 
            while (keys.hasMoreElements()) { 
                base.put(keys.nextElement(), ""); 
            } 
        } 
            Enumeration keys = base.keys(); 

           
        logging("Lendo a base de conhecimento do arquivo: " + file.getAbsolutePath()); 
        logging(""); 
        logging("Existem " + regras.size() + " regras"); 
        logging("Existem " + perguntas.size() + " perguntas"); 
        logging("Existem " + base.size() + " atributos"); 
        logging(""); 
        System.out.println(log()); 
    } 
    
    Hashtable readregrascora(Element element, String text) { 
        Hashtable data = new Hashtable(); 

        Iterator children = element.getChildren().iterator(); 
        while (children.hasNext()) { 
            Element child = (Element) children.next(); 
            String name = child.getChild("atributo").getText(); 
            String valor = child.getChild("valor").getText(); 
            data.put(name, valor); 
        } 

        return data; 
    } 

    ArrayList readregras(Element element) { 
        ArrayList regras = new ArrayList(); 
        Hashtable conds = null; 
        Hashtable acts = null; 
        String name = null; 
        Iterator children = element.getChildren().iterator(); 
        while (children.hasNext()) { 
            Element child = (Element) children.next(); 
            if (child.getName().equals("regra")) { 
                name = child.getChild("name").getText(); 
          
            
                
                Iterator condicoes = child.getChildren("condicoes").iterator(); 
                while (condicoes.hasNext()) { 
                	
                    conds = readregrascora((Element) condicoes.next(), "condicao"); 
               
                } 
                Iterator acoes = child.getChildren("acoes").iterator(); 
                while (acoes.hasNext()) { 
                    acts = readregrascora((Element) acoes.next(), "acao"); 
                 
                } 
            } 
            regras.add(new Regra(name, conds, acts)); 
        } 
        return regras; 
    } 

    Hashtable readperguntas(Element element) { 
        Hashtable perguntas = new Hashtable(); 
        Iterator quests = element.getChildren("pergunta").iterator(); 
        while (quests.hasNext()) { 
            Element pergunta = (Element) quests.next(); 
            String name = pergunta.getChild("atributo").getText(); 
          	System.out.println(name);
            String valor = pergunta.getChild("text").getText(); 
            ArrayList respostas = new ArrayList(); 
            Iterator resps = pergunta.getChildren("resposta").iterator(); 
            while (resps.hasNext()) { 
                Element resposta = (Element) resps.next(); 
                respostas.add(resposta.getText()); 
               
            } 
            perguntas.put(name, new Pergunta(valor, respostas)); 
        } 

        return perguntas; 
    } 
    
    
    
    
    

    
    
    
    public MotorInferencia() {
		// TODO Auto-generated constructor stub
	}








	public String log() { 
        String text = new String(logtext); 
        logtext = ""; 
        return text; 
    } 
	

    void logging(String message) { 
        logtext = logtext + message + "\n"; 
    } 

    public int run() { 
        boolean didsomething = false; 

        if (answeredregraatributo != null) { 
        
            makeknown(answeredregraatributo, RespostaEscolhida); 
            didsomething = true; 
        } 



        Iterator regraSet = regras.iterator(); 
        while (regraSet.hasNext()) { 
            Regra regra = (Regra) regraSet.next(); 
          // aqui eu examino a regra  
            if (regra.isAtivo()) { 
                logging("\nExaminando a regra " + regra.getName()); 
                boolean ok = true; 
                Hashtable condicoes = regra.getCondicoes(); 
                Enumeration keys; 
                keys = condicoes.keys(); 
           // aqui eu avalio o seguinte, se eu procurar um atributo, mas ele não existe na regra
                while (keys.hasMoreElements()) { 
                    String key = (String) keys.nextElement(); 
                    if ((base.get(key)).equals("")) { 
                        if (!perguntas.containsKey(key)) { 
                            logging("A Regra não pode ser resolvida no momento."); 
                            ok = false; 
                          
                            break; 
                        } 
                    } 
                } 
                
               
                if (ok) { 
                    keys = condicoes.keys(); 
                    while (keys.hasMoreElements()) { 
                        String key = (String) keys.nextElement(); 
                        if ((base.get(key)).equals("")) { 
                            if (perguntas.containsKey(key)) { 
                                Pergunta pergunta = (Pergunta) perguntas.get(key); 
                                logging("A Pergunta é " + pergunta.getText()); 
                                answeredregraatributo = key; 
                                messagemPergunta = pergunta.getText(); 
                                totalRespostas = (ArrayList) pergunta.getRespostas(); 
                                status = MORE; 
                                
                                return status; 
                            } 
                        } 
                    } 
                } 
                // Quando a regra for satisfeita, o OK = true. então a regra avalia é satisfeita e a variavel objetivo é definida e termina a máquina
                if (ok) { 
                    logging("A Regra " + regra.getName() + " foi satisfeita"); 
                    Hashtable acoes = regra.getAcoes(); 
                    Enumeration DefAcao = acoes.keys(); 
                    while (DefAcao.hasMoreElements()) { 
                        String acaoKey = (String) DefAcao.nextElement(); 
                        setPersonagem((String) acoes.get(acaoKey));
                        makeknown(acaoKey, (String) acoes.get(acaoKey)); 
                        
                    } 
                    regra.setInAtivo(); 
                    didsomething = true; 
                    if (var_objetivoset()) { 
                        status = COMPLETE; 
                      
                        return status; 
                    } 
                } 
            } 
        } 

        if (var_objetivoset()) { 
        
            status = COMPLETE; 
           
        } else if (didsomething == false) { 
            messagemPergunta = "Failed"; 
            status = FAILED; 
        } else { 
            // we did something but have nothing to ask the user, recurse! 
            status = run(); 
        } 

        return status; 
    } 

    private boolean var_objetivoset() { 
    
        if ((base.get(var_objetivo) != null)) { 
        	
            logging("\nA Váriavel-objetivo " + var_objetivo + " foi definida"); 
            logging(theansweris()); 

            messagemPergunta = theansweris(); 
            return true;  
            
        } else { 
        
  
        	return false; 

        } 
        
        
    } 


    public void makeknown(String atributo, String valor) { 
        logging("Definindo " + atributo + " igual " + valor); 
   
        base.put(atributo, valor); 

        Iterator regraSet = regras.iterator(); 
        while (regraSet.hasNext()) { 
            Regra regra = (Regra) regraSet.next(); 
            if (regra.isAtivo()) { 
                if (regra.getCondicoes().containsKey(atributo)) { 
                    if (!regra.getCondicoes().get(atributo).equals(valor)) { 
                        logging("A regra " + regra.getName() + " foi desativada, pois a condição não foi satisfeita\n O atributo:" + regra.getCondicoes().get(atributo) ); 
                        regra.setInAtivo(); 
                    } 
                } 
            } 
        } 
        
    } 
   
    


    String theansweris() { 
 
        StringTokenizer tokenizer = new StringTokenizer(var_objetivoDescricao); 
       
        String resposta = ""; 
        while (tokenizer.hasMoreTokens()) { 
            String token = tokenizer.nextToken(); 
      
            if (token.equals(var_objetivo)) { 
                resposta = resposta + base.get(token); 
            } else { 
                resposta = resposta + token; 
            } 
            resposta = resposta + " "; 
        } 
        resposta = resposta + getPersonagem();
    
        return resposta; 
    } 

    
    
    /* Gets e Sets*/
    public String getMessagemPergunta() { 
        return messagemPergunta; 
    } 

    public ArrayList getTotalRespostas() { 
        return totalRespostas; 
    } 

    public void setRespostaEscolhida(String RespostaEscolhida) { 
        this.RespostaEscolhida = RespostaEscolhida; 
    } 

	public String getPersonagem() {
		return personagem;
	}




	public void setPersonagem(String personagem) {
		this.personagem = personagem;
	}

}