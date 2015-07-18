package Motor;
import java.io.File;
import java.net.URL;



public class Main {
 //http://www.theserverside.com/discussions/thread.tss?thread_id=21562
	URL testFile = (this.getClass().getResource("Test.xml"));
	InferenceEngine engine = new InferenceEngine(new File(testFile.getPath()));
	
	engine.run();
	engine.setChosenAnswer("yes");
	engine.run();
	engine.setChosenAnswer("no");
	engine.run();
	engine.setChosenAnswer("blue");
	engine.run(); 


}
