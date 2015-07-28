
package Motor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// import para carregar o xml
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;


public class InterG3 extends JFrame implements ActionListener 
{
    private JLabel lbnro1, lbnro2;
    private JTextField txnro1, txnro2, txres;
    private JComboBox cbOp;
    private JButton btcalc, btsair;
    private Image image;
    
    private JPanel pbotoes, pdados;
    
    private Container tela;
    MotorInferencia m = new MotorInferencia();
    Class cls = m.getClass();
    
    URL testFile = (cls.getResource("Test.xml"));
	//System.out.println("Value = " + testFile);  
    MotorInferencia motorInferencia = new MotorInferencia(new File(testFile.getPath()));
    
    InterG3() throws IOException
    {
        // configurações de conteúdo e layout ////////
        tela = this.getContentPane();
        pbotoes = new JPanel();
        pdados = new JPanel();
        
        tela.setLayout(new BorderLayout());
        pdados.setLayout(new GridLayout(2,1));
        pbotoes.setLayout(new GridLayout(1,2));
                
        // configurações dos componentes ////////////
        
        lbnro1 = new JLabel("Número 1:");
       // lbnro2 = new JLabel("Número 2:");
      //  txnro1 = new JTextField(10);
     //   txnro2 = new JTextField(10);
     //   txres = new JTextField(10);
        String ops[] = {};
        cbOp = new JComboBox(ops);
        btcalc = new JButton("Responder");
        btsair = new JButton("Sair");
        
        btcalc.addActionListener(this);
        btsair.addActionListener(this);
        
        // adição de componentes no frame e nos paineis ////
        
        tela.add(pdados,BorderLayout.NORTH);
        tela.add(pbotoes,BorderLayout.SOUTH);
        
        pdados.add(lbnro1); 
        /*pdados.add(txnro1);
        pdados.add(lbnro2); 
        pdados.add(txnro2);;*/
        pdados.add(cbOp); 
        //pdados.add(txres)
        
        pbotoes.add(btcalc); pbotoes.add(btsair);
        
        pdados.setBackground(Color.CYAN);
        
        
        // configurações da janela/frame /////////////
        this.setTitle("LOL");
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        Iniciar();
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
       if (ae.getSource() == btsair)
           System.exit(0);
       
       if (ae.getSource() == btcalc)
       {
    	  // System.out.println(cbOp.getSelectedItem());  
    	   String resposta = (String) cbOp.getSelectedItem();
    	   motorInferencia.setRespostaEscolhida(resposta);
    	  try {
			Iniciar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
         
             
       }
    }
    
    public void Iniciar() throws IOException{
    	
	
	
    if (motorInferencia.run() != 2) {
	System.out.println(motorInferencia.log());
	lbnro1.setText(motorInferencia.getMessagemPergunta());
	cbOp.removeAllItems();
	for (int i = 0; i < motorInferencia.getTotalRespostas().size(); i++){
        cbOp.addItem(motorInferencia.getTotalRespostas().get(i));
	 }
    }else
    {
    	System.out.println(motorInferencia.log());
    	lbnro1.setText(motorInferencia.getMessagemPergunta());
    	btcalc.setVisible(false);
    	cbOp.setVisible(false);
    	this.setSize(800,450);
    	   image = ImageIO.read(new File("/home/rafael/Documents/workspace/Inference_engine_1/src/Motor/lol_3a.jpg"));
		      JLabel picLabel = new JLabel(new ImageIcon(image));
		      
		      tela.add(picLabel);
		      tela.repaint();
    
    	
    }
	
    }


    
    
}
