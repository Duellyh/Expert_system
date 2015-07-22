package Motor;
import java.io.File;
import java.io.IOException;
import java.net.URL;



public class Maquina {
	 

	
	
	public static void main(String[] args) throws IOException {
		
		  Maquina m = new Maquina();
		    Class cls = m.getClass();
		    
	URL testFile = (cls.getResource("Test.xml"));
	System.out.println("Value = " + testFile);  
	
	
	InferenceEngine engine = new InferenceEngine(new File(testFile.getPath()));
	//new InterG3();
	engine.run();
	System.out.println(engine.log());
	System.out.println("1aaaaaaa");
	
	engine.setChosenAnswer("tanker");
	System.out.println("Difiniu resposta da primeira pergunta");
	
	engine.run();
	System.out.println(engine.log());
	System.out.println("3aaaaaaa");
	//System.out.println(engine.log());
	//System.out.println("aaaaaaa");
	
	engine.setChosenAnswer("top");
	engine.run();
	System.out.println(engine.log()); 
	//engine.setChosenAnswer("blue");
	//engine.run();
	//System.out.println(engine.log());
	}
	


}
