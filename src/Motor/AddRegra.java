package Motor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddRegra extends JFrame implements ActionListener{

	  private JLabel lbnro1,lbnomeregra, lbcondicao, lbatributoregra, lbvalorregra, lbatributoregra2, lbvalorregra2, lbacao, lbatributoacao, lbvaloracao, lbnada, lbnada2;
	    private JButton btcalc, btsair, btadd;
	    private JTextField txtNomeRegra, txtAtributoRegra, txtvalorRegra,txtAtributoRegra2, txtvalorRegra2, txtAtributoAcao, txtValorAcao;
	    
	    private JPanel pbotoes, pdados;
	    private String nome_regra, atributo_regra, valor_regra, atributo_regra2, valor_regra2, atributo_acao, valor_acao;
	    private Container tela;
	    private static final String xmlFilePath = "/home/rafael/Documents/workspace/Inference_engine_1/src/Motor/Test.xml";
	     JTextField[] txtFieldA;
	    
	    AddRegra()
	    {
	        // configurações de conteúdo e layout ////////
	        tela = this.getContentPane();
	        pbotoes = new JPanel();
	        pdados = new JPanel();
	      
	       				
	        
	        tela.setLayout(new BorderLayout());
	        pdados.setLayout(new GridLayout(9,9));
	        pbotoes.setLayout(new GridLayout(1,2));
	                
	        // configurações dos componentes ////////////
	        
	       // lbnro1 = new JLabel("Neste sistema especialista iremos mostrar as funcionalidades \ndo jogo LOL bla bla:");
	        lbnomeregra = new JLabel("Nome Regra");
	        lbcondicao = new JLabel("Condição"); 
	        lbatributoregra = new JLabel("Atributo 1"); 
	        lbvalorregra = new JLabel("Valor 1"); 
	        
	        lbatributoregra2 = new JLabel("Atributo 2"); 
	        lbvalorregra2 = new JLabel("Valor 2"); 
	        
	        lbacao = new JLabel("Ação"); 
	        lbatributoacao = new JLabel("Atributo"); 
	        lbvaloracao = new JLabel("Valor");
	        lbnada = new JLabel(" ");
	        lbnada2 = new JLabel(" ");
	        
	        txtNomeRegra = new JTextField(10);
	        txtAtributoRegra = new JTextField(15);
	        
	         txtvalorRegra  = new JTextField(15);
	         txtAtributoRegra2 = new JTextField(15);
		        
	         txtvalorRegra2  = new JTextField(15);
	         
	          txtAtributoAcao = new JTextField(15);
	           txtValorAcao = new JTextField(15);
	           
	           
	           txtNomeRegra.setName("Nome da Regra");
	           txtAtributoRegra.setName("Atributo 1");
		        
		         txtvalorRegra.setName("Valor 1");
		         txtAtributoRegra2.setName("Atributo 2");
			        
		         txtvalorRegra2.setName("Valor 2");
		         
		          txtAtributoAcao.setName("Atributo Acao");
		           txtValorAcao.setName("Valor Acao");
		           
		           JTextField[] txtFieldA = new JTextField[5] ;
			        txtFieldA[0] = txtNomeRegra;
			        txtFieldA[1] = txtAtributoRegra;	
			        txtFieldA[2] = txtvalorRegra;
			       	//txtFieldA[5] = txtAtributoRegra2;
			       	//txtFieldA[6] = txtvalorRegra2;
			       	txtFieldA[3] = txtAtributoAcao;
			       	txtFieldA[4] = txtValorAcao;
	        
	        btcalc = new JButton("Add Regra");
	        btsair = new JButton("Sair");
	       
	        
	        btcalc.addActionListener(this);
	        btsair.addActionListener(this);
	       
	        
	        // adição de componentes no frame e nos paineis ////
	        
	        tela.add(pdados,BorderLayout.NORTH);
	        tela.add(pbotoes,BorderLayout.SOUTH);
	        
	      
	        pdados.add(lbnomeregra);  pdados.add(txtNomeRegra);
	        
	        pdados.add(lbcondicao);  pdados.add(lbnada);
	        pdados.add(lbatributoregra); pdados.add(txtAtributoRegra); 
	        pdados.add(lbvalorregra); pdados.add(txtvalorRegra); 
	        pdados.add(lbatributoregra2); pdados.add(txtAtributoRegra2); 
	        pdados.add(lbvalorregra2); pdados.add(txtvalorRegra2); 
	        
	        pdados.add(lbacao);  pdados.add(lbnada2);
	        pdados.add(lbatributoacao); pdados.add(txtAtributoAcao); 
	        pdados.add(lbvaloracao);  pdados.add(txtValorAcao);

	        
	        pbotoes.add(btcalc); pbotoes.add(btsair);
	    
	        
	        pdados.setBackground(Color.CYAN);
	        
	        
	        // configurações da janela/frame /////////////
	        this.setTitle("LOL");
	        this.setSize(500,500);
	        this.setLocationRelativeTo(null);
	        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        this.setVisible(true);
	    }

	    @Override
	    public void actionPerformed(ActionEvent ae) 
	    {
	       if (ae.getSource() == btsair)
	    	   this.setVisible(false);
	       
	       if (ae.getSource() == btcalc)
	       {
	    	   
	    	   
	    		   
	    		   nome_regra = txtNomeRegra.getText();
		    	   atributo_regra = txtAtributoRegra.getText();
		    	   valor_regra = txtvalorRegra.getText();
		    	   atributo_regra2 = txtAtributoRegra2.getText();
		    	   valor_regra2 = txtvalorRegra2.getText();
		    	   
		    	   atributo_acao = txtAtributoAcao.getText();
		    	   valor_acao = txtValorAcao.getText();
		    	   
		    	   CriarRegra();
	    		   
	    		   
	    	  
	    		
	    	   
	    	   
	    	  
	          
	           
	       }
	    }
	    
	    public void CriarRegra()
	    {
	    	SAXBuilder saxBuilder = new SAXBuilder();
	    	    File xmlFile = new File(xmlFilePath);
	    	    try {    
	    	    Document document = (Document) saxBuilder.build(xmlFile);
	    	     
	      Element rootElement = document.getRootElement();
	    	    	 
	     Element employee = rootElement.getChild("regras");	   
	     // adicionando no regra
	     Element regra = new Element("regra");
	     employee.addContent(regra);
	     
	     // nome da regra dentro de regra
	     Element El_regranome = new Element("name").setText(nome_regra);  
	  
	     regra.addContent(El_regranome);  
	     
	     //condicoes dentro de regra
	     Element El_condicoes = new Element("condicoes");
	     
	     regra.addContent(El_condicoes);  
	     
	     //condicao dentro de condicoes
	     Element El_condicao = new Element("condicao");
	     
	     El_condicoes.addContent(El_condicao);  
	     	
		     //atributo dentro de condicao
		     Element El_condicao_atri = new Element("atributo").setText(atributo_regra);
		     
		     El_condicao.addContent(El_condicao_atri);  
		     
		     //valor dentro de condicao
		     Element El_condicao_valor = new Element("valor").setText(valor_regra);
		     
		     El_condicao.addContent(El_condicao_valor);  
		     
		     if (!(txtAtributoRegra2.getText().equals(" ")) && !(txtvalorRegra2.getText().equals(" ")))
	    	   {
		    	 //condicao dentro de condicoes
			     El_condicao = new Element("condicao");
			     
			     El_condicoes.addContent(El_condicao); 
		    	 //atributo dentro de condicao
			    El_condicao_atri = new Element("atributo").setText(atributo_regra2);
			     
			     El_condicao.addContent(El_condicao_atri);  
			     
			     //valor dentro de condicao
			     El_condicao_valor = new Element("valor").setText(valor_regra2);
			     
			     El_condicao.addContent(El_condicao_valor);  
		    	 
		    	 
	    	   
	    	   }
	     
	     
	      
	          
		     //acoes dentro de regra
		     Element El_acoes = new Element("acoes");
		     
		     regra.addContent(El_acoes);  
		     
			     //condicao dentro de condicoes
			     Element El_acao = new Element("acao");
			     
			     El_acoes.addContent(El_acao);  
			     
				     //atributo dentro de acao
				     Element El_acao_atri = new Element("atributo").setText(atributo_acao);
				     
				     El_acao.addContent(El_acao_atri);  
				     
				     //valor dentro de acao
				     Element El_acao_valor = new Element("valor").setText(valor_acao);
				     
				     El_acao.addContent(El_acao_valor); 
			     
		          
	      
	                XMLOutputter xmlOutput = new XMLOutputter();
	     
	               //update the file with nice XML formating 
	                 xmlOutput.setFormat(Format.getPrettyFormat());
	     	 
	    	            
					xmlOutput.output(document, new FileWriter(xmlFilePath));
				
	      
	     	          
	      
	     	            System.out.println("XML File successfully updated!");
	    		} catch (IOException e) {
					// TODO Auto-generated catch block
	    			 System.out.println(e.getMessage());
				} catch (JDOMException jdomex) {
					            System.out.println(jdomex.getMessage());
			        }
	    }

	}


