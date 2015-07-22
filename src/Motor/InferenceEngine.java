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
import java.net.URL;

 class Rule { 
    private String name = null; 
    private Hashtable conditions = null; 
    private Hashtable actions = null; 
    private boolean active = true; 

    Rule(String name, Hashtable conditions, Hashtable actions) { 
        this.name = name; 
        this.conditions = conditions; 
        this.actions = actions; 
    } 

    void setInactive() { 
        active = false; 
    } 

    public String getName() { 
        return name; 
    } 

    public Hashtable getConditions() { 
        return conditions; 
    } 

    public Hashtable getActions() { 
        return actions; 
    } 

    public boolean isActive() { 
        return active; 
    } 
} 

class Question { 
    private String text = null; 
    private Object responses = null; 

    Question(String text, Object responses) { 
        this.text = text; 
        this.responses = responses; 
    } 

    public String getText() { 
        return text; 
    } 

    public void setText(String text) { 
        this.text = text; 
    } 

    public Object getResponses() { 
        return responses; 
    } 

    public void setResponses(Object responses) { 
        this.responses = responses; 
    } 
} 

public class InferenceEngine { 
    static public int ERROR = 0; 
    static public int MORE = 1; 
    static public int COMPLETE = 2; 
    static public int FAILED = 3; 

    private ArrayList rules = null; 
    private Hashtable questions = null; 
    private String goalVariable = null; 
    private String goalDescription = null; 
    private Hashtable knowledge = new Hashtable(); 
    private String answeredRuleAttribute = null; 
    private String messageForQuerent = null; 
    private ArrayList possibleResponses = null; 
    private String chosenAnswer = null; 
    private int status; 
    private String logtext = null; 
    private String personagem = null;

    public InferenceEngine(File file) throws IOException { 

        SAXBuilder builder = new SAXBuilder(); 
        Element inputRootElement = null; 
        //Lendo o documento
        try { 
            Document inputDocument = builder.build(file); 
            inputRootElement = inputDocument.getRootElement(); 
        } catch (JDOMException e) { 
            e.printStackTrace(); 
        } 
       // aqui ele acha a variavel objetivo
       // acha o attributo e o texto 
        Iterator children = inputRootElement.getChildren().iterator(); 
        while (children.hasNext()) { 
            Element child = (Element) children.next(); 
            if (child.getName().equals("goal")) { 
                goalVariable = child.getChild("attribute").getText(); 
                goalDescription = child.getChild("text").getText(); 
            } else if (child.getName().equals("rules")) { 
                rules = readrules(child); 
            } else if (child.getName().equals("questions")) { 
                questions = readquestions(child); 
            }
            System.out.println("Goal_Attribute: " + goalVariable);
            System.out.println("Goal_text: " + goalDescription);
            System.out.println("Rules: " + rules);
            System.out.println("Questions: " + questions);
            
        } 

        Iterator ruleSet = rules.iterator(); 
        while (ruleSet.hasNext()) { 
            Rule rule = (Rule) ruleSet.next(); 
            Enumeration keys = null; 
            Hashtable conditions = rule.getConditions(); 
           
            keys = conditions.keys();
           
            while (keys.hasMoreElements()) { 
                knowledge.put(keys.nextElement(), ""); 
            } 
            Hashtable actions = rule.getConditions(); 
            keys = actions.keys(); 
            while (keys.hasMoreElements()) { 
                knowledge.put(keys.nextElement(), ""); 
            } 
        } 
            Enumeration keys = knowledge.keys(); 

            while (keys.hasMoreElements()) { 
                System.out.println((String)keys.nextElement()); 
               

            } 
        logging("Reading knowledge base from " + file.getAbsolutePath()); 
        logging(""); 
        logging("There are " + rules.size() + " rules"); 
        logging("There are " + questions.size() + " questions"); 
        logging("There are " + knowledge.size() + " attributes"); 
        logging(""); 
        System.out.println(log()); 
    } 

    
    
    
    public String getPersonagem() {
		return personagem;
	}




	public void setPersonagem(String personagem) {
		this.personagem = personagem;
	}




	public String log() { 
        String text = new String(logtext); 
        logtext = ""; 
        return text; 
    } 

    public int run() { 
        boolean didsomething = false; 
    	//System.out.println("answered: "+ answeredRuleAttribute);  
    	//System.out.println(chosenAnswer);  
        if (answeredRuleAttribute != null) { 
        
            makeknown(answeredRuleAttribute, chosenAnswer); 
            didsomething = true; 
        } 

      /*  if (goalset()) { 
            status = COMPLETE; 
            System.out.println(log()); 
            return status; 
        } */

        Iterator ruleSet = rules.iterator(); 
        while (ruleSet.hasNext()) { 
            Rule rule = (Rule) ruleSet.next(); 
            
            if (rule.isActive()) { 
                logging("\nExamining rule " + rule.getName()); 
                boolean ok = true; 
                Hashtable conditions = rule.getConditions(); 
                Enumeration keys; 
                keys = conditions.keys(); 
                while (keys.hasMoreElements()) { 
                    String key = (String) keys.nextElement(); 
                    if ((knowledge.get(key)).equals("")) { 
                        if (!questions.containsKey(key)) { 
                            logging("Rule cannot be resolved at present"); 
                            ok = false; 
                           // System.out.println(log()); 
                            break; 
                        } 
                    } 
                } 
                if (ok) { 
                    keys = conditions.keys(); 
                    while (keys.hasMoreElements()) { 
                        String key = (String) keys.nextElement(); 
                        if ((knowledge.get(key)).equals("")) { 
                            if (questions.containsKey(key)) { 
                                Question question = (Question) questions.get(key); 
                                logging("Question is " + question.getText()); 
                                answeredRuleAttribute = key; 
                                messageForQuerent = question.getText(); 
                                possibleResponses = (ArrayList) question.getResponses(); 
                                status = MORE; 
                                
                                return status; 
                            } 
                        } 
                    } 
                } 
                if (ok) { 
                    logging("Rule " + rule.getName() + " passed"); 
                    Hashtable actions = rule.getActions(); 
                    Enumeration actionSet = actions.keys(); 
                    while (actionSet.hasMoreElements()) { 
                        String actionKey = (String) actionSet.nextElement(); 
                        setPersonagem((String) actions.get(actionKey));
                        makeknown(actionKey, (String) actions.get(actionKey)); 
                        
                    } 
                    rule.setInactive(); 
                    didsomething = true; 
                    if (goalset()) { 
                        status = COMPLETE; 
                        
                        return status; 
                    } 
                } 
            } 
        } 

        if (goalset()) { 
        
            status = COMPLETE; 
           
        } else if (didsomething == false) { 
            messageForQuerent = "Failed"; 
            status = FAILED; 
        } else { 
            // we did something but have nothing to ask the user, recurse! 
            status = run(); 
        } 

        return status; 
    } 

    private boolean goalset() { 
    	// System.out.println("testandoo_1: " + knowledge.get(goalVariable));
        if ((knowledge.get(goalVariable) != null)) { 
        	// System.out.println("The goal " + goalVariable + " has been set");
            logging("\nThe goal " + goalVariable + " has been set"); 
            logging(theansweris()); 

            messageForQuerent = theansweris(); 
            
            return true; 
        } else { 
            return false; 
        } 
    } 


    public void makeknown(String attribute, String value) { 
        logging("Setting " + attribute + " to " + value); 
       // System.out.println("Setting " + attribute + " to " + value); 
        knowledge.put(attribute, value); 

        Iterator ruleSet = rules.iterator(); 
        while (ruleSet.hasNext()) { 
            Rule rule = (Rule) ruleSet.next(); 
            if (rule.isActive()) { 
                if (rule.getConditions().containsKey(attribute)) { 
                    if (!rule.getConditions().get(attribute).equals(value)) { 
                        logging("Rule " + rule.getName() + " is now inactive"); 
                        rule.setInactive(); 
                    } 
                } 
            } 
        } 
        
    } 

    public String getMessageForQuerent() { 
        return messageForQuerent; 
    } 

    public ArrayList getPossibleResponses() { 
        return possibleResponses; 
    } 

    public void setChosenAnswer(String chosenAnswer) { 
        this.chosenAnswer = chosenAnswer; 
    } 

    void logging(String message) { 
        logtext = logtext + message + "\n"; 
    } 

    String theansweris() { 
    	// aqui ele constroe o texto para goal e define a variavel goal
        StringTokenizer tokenizer = new StringTokenizer(goalDescription); 
       
        String response = ""; 
        while (tokenizer.hasMoreTokens()) { 
            String token = tokenizer.nextToken(); 
      
            if (token.equals(goalVariable)) { 
                response = response + knowledge.get(token); 
            } else { 
                response = response + token; 
            } 
            response = response + " "; 
        } 
        response = response + getPersonagem();
    
        return response; 
    } 

    Hashtable readrulescora(Element element, String text) { 
        Hashtable data = new Hashtable(); 

        Iterator children = element.getChildren().iterator(); 
        while (children.hasNext()) { 
            Element child = (Element) children.next(); 
            String name = child.getChild("attribute").getText(); 
            String value = child.getChild("value").getText(); 
            data.put(name, value); 
        } 

        return data; 
    } 

    ArrayList readrules(Element element) { 
        ArrayList rules = new ArrayList(); 
        Hashtable conds = null; 
        Hashtable acts = null; 
        String name = null; 
        Iterator children = element.getChildren().iterator(); 
        while (children.hasNext()) { 
            Element child = (Element) children.next(); 
            if (child.getName().equals("rule")) { 
                name = child.getChild("name").getText(); 
                
               // System.out.println("Rule_name: " + name);
                
                Iterator conditions = child.getChildren("conditions").iterator(); 
                while (conditions.hasNext()) { 
                	
                    conds = readrulescora((Element) conditions.next(), "condition"); 
                  //  System.out.println("rule_conds: " + conds);
                } 
                Iterator actions = child.getChildren("actions").iterator(); 
                while (actions.hasNext()) { 
                    acts = readrulescora((Element) actions.next(), "action"); 
                  //  System.out.println("rule_acts: " + acts);
                } 
            } 
            rules.add(new Rule(name, conds, acts)); 
        } 
        return rules; 
    } 

    Hashtable readquestions(Element element) { 
        Hashtable questions = new Hashtable(); 
        Iterator quests = element.getChildren("question").iterator(); 
        while (quests.hasNext()) { 
            Element question = (Element) quests.next(); 
            String name = question.getChild("attribute").getText(); 
            String value = question.getChild("text").getText(); 
            ArrayList responses = new ArrayList(); 
            Iterator resps = question.getChildren("response").iterator(); 
            while (resps.hasNext()) { 
                Element response = (Element) resps.next(); 
                responses.add(response.getText()); 
                System.out.println("questions_responses: " + responses);
            } 
            questions.put(name, new Question(value, responses)); 
        } 
        return questions; 
    } 

}