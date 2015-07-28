package Motor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterG2 extends JFrame implements ActionListener{

	  private JLabel lbnro1;
	    private JButton btcalc, btsair, btadd;
	    
	    private JPanel pbotoes, pdados;
	    private Image image;
	    private Container tela;
	   
	  
	    
	    InterG2() throws IOException
	    {
	        // configurações de conteúdo e layout ////////
	        tela = this.getContentPane();
	        pbotoes = new JPanel();
	        pdados = new JPanel();
	       
	        tela.setLayout(new BorderLayout());
	        pdados.setLayout(new GridLayout(2,1));
	        pbotoes.setLayout(new GridLayout(1,2));
	                
	        // configurações dos componentes ////////////
	        
	        lbnro1 = new JLabel("Neste sistema especialista iremos mostrar as funcionalidades do jogo LOL:");

	        btcalc = new JButton("Iniciar");
	        btsair = new JButton("Sair");
	        btadd = new JButton("Add Regra");
	        
	        
	        image = ImageIO.read(new File("/home/rafael/Documents/workspace/Inference_engine_1/src/Motor/LolHD-logo.png"));
		      JLabel picLabel = new JLabel(new ImageIcon(image));
		      
		      tela.add(picLabel);
		      tela.repaint();
	        btcalc.addActionListener(this);
	        btsair.addActionListener(this);
	        btadd.addActionListener(this);
	        
	        // adição de componentes no frame e nos paineis ////
	        
	        tela.add(pdados,BorderLayout.NORTH);
	        tela.add(pbotoes,BorderLayout.SOUTH);
	        
	        pdados.add(lbnro1); 

	        
	        pbotoes.add(btcalc); pbotoes.add(btsair);
	        pbotoes.add(btadd);
	        
	        pdados.setBackground(Color.CYAN);
	        
	        
	        // configurações da janela/frame /////////////
	        this.setTitle("LOL");
	        this.setSize(500,300);
	        this.setLocationRelativeTo(null);
	        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        this.setVisible(true);
	    }

	    @Override
	    public void actionPerformed(ActionEvent ae) 
	    {
	    	if (ae.getSource() == btadd)
	    	{
	    		new AddRegra();
	    		
	    	}
		          
	       if (ae.getSource() == btsair)
	           System.exit(0);
	       
	       if (ae.getSource() == btcalc)
	       {
	    	   try {
				new InterG3();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	   this.setVisible(false);
	          
	           
	       }
	    }

	}


