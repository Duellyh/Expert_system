package Motor;
import java.io.File;
import java.io.IOException;
import java.net.URL;



public class Maquina {
	 
 //http://www.theserverside.com/discussions/thread.tss?thread_id=21562
	
	
	public static void main(String[] args) throws IOException {
		  Maquina m = new Maquina();
		    Class cls = m.getClass();
		    
	URL testFile = (cls.getResource("Test.xml"));
	System.out.println("Value = " + testFile);  
	
	
	InferenceEngine engine = new InferenceEngine(new File(testFile.getPath()));
	
	engine.run();
	engine.setChosenAnswer("yes");
	engine.run();
	engine.setChosenAnswer("no");
	engine.run();
	engine.setChosenAnswer("blue");
	engine.run(); 
	 }


}
