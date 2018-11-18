package Boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Connection.SocketClass;
import Connection.SocketClassUDP;
import Entity.SendMailEntity;

public class FrmGetXML extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static SocketClass sockobj = new SocketClass();
	JTextArea textArea;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JTextField textField_2;
	private Task t;
	private JTextField textField_3;
	public FrmGetXML() {
		getContentPane().setBackground(new Color(224, 255, 255));
		init();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 800);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void init() {
		getContentPane().setLayout(null);
	
		
		JButton button = new JButton("Get XML");
		button.addActionListener(this);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setForeground(new Color(154, 205, 50));
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBackground(new Color(240, 255, 240));
		button.setBounds(180, 604, 106, 76);
		getContentPane().add(button);
		
		JButton button3 = new JButton("Get HTML");
		button3.addActionListener(this);
		button3.setHorizontalAlignment(SwingConstants.LEFT);
		button3.setForeground(new Color(154, 205, 50));
		button3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button3.setBackground(new Color(240, 255, 240));
		button3.setBounds(28, 604, 137, 76);
		getContentPane().add(button3);
		
		JButton button2 = new JButton("Exit");
		button2.addActionListener(this);
		button2.setHorizontalAlignment(SwingConstants.LEFT);
		button2.setForeground(new Color(154, 205, 50));
		button2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button2.setBackground(new Color(240, 255, 240));
		button2.setBounds(845, 588, 76, 76);
		getContentPane().add(button2);
		
		JButton button4 = new JButton("Caculate TCP");
		button4.addActionListener(this);
		button4.setHorizontalAlignment(SwingConstants.LEFT);
		button4.setForeground(new Color(154, 205, 50));
		button4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button4.setBackground(new Color(240, 255, 240));
		button4.setBounds(306, 548, 146, 43);
		getContentPane().add(button4);
		
		textArea = new JTextArea();
		textArea.setText(" ");
		textArea.setEditable(false);
		textArea.setBounds(83, 16, 706, 414);
		getContentPane().add(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		textField = new JTextField();
		textField.setBounds(306, 434, 146, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(306, 476, 146, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(180, 495, 92, 26);
		getContentPane().add(passwordField);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(306, 512, 146, 26);
		getContentPane().add(textField_2);
		
		JButton button_1 = new JButton("Stop");
		button_1.addActionListener(this);
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setForeground(new Color(154, 205, 50));
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBackground(new Color(240, 255, 240));
		button_1.setBounds(180, 537, 106, 51);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Caculate UDP");
		button_2.addActionListener(this);
		button_2.setHorizontalAlignment(SwingConstants.LEFT);
		button_2.setForeground(new Color(154, 205, 50));
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_2.setBackground(new Color(240, 255, 240));
		button_2.setBounds(306, 621, 146, 43);
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton("Tim USCLN");
		button_3.addActionListener(this);
		button_3.setHorizontalAlignment(SwingConstants.LEFT);
		button_3.setForeground(new Color(154, 205, 50));
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_3.setBackground(new Color(240, 255, 240));
		button_3.setBounds(466, 613, 157, 51);
		getContentPane().add(button_3);
		
		JButton button_4 = new JButton("Encrypt/Decrypt");
		button_4.addActionListener(this);
		button_4.setHorizontalAlignment(SwingConstants.LEFT);
		button_4.setForeground(new Color(154, 205, 50));
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_4.setBackground(new Color(240, 255, 240));
		button_4.setBounds(638, 613, 178, 51);
		getContentPane().add(button_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(29, 476, 146, 26);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton button_5 = new JButton("Get HTML 2");
		button_5.addActionListener(this);
		button_5.setHorizontalAlignment(SwingConstants.LEFT);
		button_5.setForeground(new Color(154, 205, 50));
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_5.setBackground(new Color(240, 255, 240));
		button_5.setBounds(28, 512, 137, 76);
		getContentPane().add(button_5);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Get XML") {
		   t = new Task();
		  t.start();
		   
			  }

		else if(e.getActionCommand() == "Stop") {
		   t.stop();
		   t.interrupt();
		   
		 }
		else if(e.getActionCommand() == "Encrypt/Decrypt") {
			  
			 SocketClassUDP n = new SocketClassUDP();
			 try {
			String m  =	n.CreateSocketandSendData5(0,1,"helloworld");
			textArea.setText(m);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 }
		else if(e.getActionCommand() == "Tim USCLN") {
			try {
				sockobj.SendUSCLN("1", "1");
				String packet = sockobj.RecievedPacket2().trim();
				textArea.setText(packet.substring(2,packet.length()-1));
                sockobj.CloseConnection();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			   
			 }
		else if(e.getActionCommand() == "Get HTML") {
		    try {
		    	SocketClass sockobj2 = new SocketClass();
				sockobj2.SendGetHTTP("/",textField_3.getText().trim(),80);
				String packet = sockobj2.RecievedPacket2();
				textArea.setText(packet);
                sockobj2.CloseConnection();
		    	
		    	//textArea.setText(t2);9
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		 }
		else if(e.getActionCommand() == "Get HTML 2") {
		    try {
		    	SocketClass sockobj = new SocketClass();
				sockobj.SendGetHTTP("/");
				String packet = sockobj.RecievedPacket2();
				textArea.setText(packet);
                sockobj.CloseConnection();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		 }
		
		else if(e.getActionCommand() == "Caculate TCP") {
		    try {
		    	SocketClass sockobj = new SocketClass();
				sockobj.SendCaculate(textField.getText(),textField_1.getText(),textField_2.getText());
				String packet = sockobj.RecievedPacket();
				String[] n = sockobj.HandlePacket(packet);
				//textArea.setText(packet.toString());
				textArea.setText(n[0]);
                sockobj.CloseConnection();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		    catch(Exception e1)
		    {
		    	e1.printStackTrace();
		    }
		 }
		else if(e.getActionCommand() == "Caculate UDP") {
			 SocketClassUDP n = new SocketClassUDP();
			 try {
			String m  =	n.CreateSocketandSendData(Integer.parseInt(textField.getText()),Integer.parseInt(textField_1.getText()),textField_2.getText());
			textArea.setText(m);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		 }
		else if(e.getActionCommand() == "Exit") {
			 System.exit(0);
		 }
		
	}

	private class Task extends Thread {    
	      public Task(){
	    	  
	      }
	      public void run(){ 	
while(true) {
		    	   try {
					sockobj.SendGetXML("/example.xml");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					String packet = sockobj.RecievedPacket2();
					String n = packet;
				    n = n.replace("HTTP/1.1 200 OK\n", "");
				    n = n.replace("Server: Simple-Python-Server\n", "");
				    n = n.replace("Connection: close\n", "");
				    String result = n.substring(n.indexOf("<SJC>\n") + 6 , n.indexOf("</SJC>")).trim();
				    System.out.println(Long.parseLong(result));
				    
				    if(Long.parseLong(result) < 1000  || Long.parseLong(result) > 1500)
				    {
				    	textArea.setText("SJC price is lower or higher than a value , sending mail !");
				    	SendMailEntity.sendFromGMail(SendMailEntity.from,passwordField.getText(), SendMailEntity.to, SendMailEntity.subject, SendMailEntity.body);
				        
				    }
				    else {
				    	packet = packet + "SJC is still alright !";
				    	textArea.setText(packet);
				    }
				    
	                sockobj.CloseConnection();
		            try {
		               System.out.println("Sleeping in 10 seconds");
		               Thread.sleep(10000);
		            } catch (InterruptedException e) {
		            	
		            } 		
		 	  
}
	      }
	   }   
	public static void main(String [] args) {
		new FrmGetXML();
	}
}
