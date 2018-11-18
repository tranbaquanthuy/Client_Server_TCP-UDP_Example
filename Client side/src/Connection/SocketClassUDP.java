package Connection;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SocketClassUDP {
    private final String serverHost = "";
    private final int serverPort = 8089;
    BufferedReader reader;
    PrintWriter writer;
    public String CreateSocketandSendData(int a, int b, String c) throws IOException
    {
    	     DatagramSocket ClientSocket = new DatagramSocket(); 
    	     DataInputStream inFromUser = new DataInputStream(System.in); 
             byte outToServer1[]; 
             String s1 = String.valueOf(a); 
             String s2 = String.valueOf(b);  
             String total = s1 + "|.|" + s2 + "|.|" + c;
             outToServer1 = total.getBytes(); 
             int leng1 = outToServer1.length; 
             InetAddress address = InetAddress.getByName(serverHost); 
             DatagramPacket toServer1 = new DatagramPacket(outToServer1, leng1, address, serverPort); 
             ClientSocket.send(toServer1); 
             byte inFromServer[]; 
             inFromServer = new byte[256]; 
             leng1 = inFromServer.length; 
             DatagramPacket fromServer = new DatagramPacket(inFromServer, leng1); 
             ClientSocket.receive(fromServer); 
             String data; 
             data = (new String(fromServer.getData(),0,fromServer.getLength())).trim(); 
             System.out.println("Ket Qua :"+data); 
             ClientSocket.close();
             return data;    
    }
    public String CreateSocketandSendData5(int ED, int N, String S) throws IOException
    {
    	     DatagramSocket ClientSocket = new DatagramSocket(); 
    	     DataInputStream inFromUser = new DataInputStream(System.in); 
    	     byte outToServer1[]; byte outToServer2[]; byte outToServer3[]; 
             String s1 = "e-d|.|"+String.valueOf(ED); 
             String s2 = "N|.|" +String.valueOf(N);  
             String s3 = "S|.|" + S ;
             outToServer1 = s1.getBytes();
             outToServer2 = s2.getBytes();
             outToServer3 = s3.getBytes(); 
             int leng1 = outToServer1.length; 
             int leng2 = outToServer2.length; 
             int leng3 = outToServer3.length; 
             InetAddress address = InetAddress.getByName(serverHost); 
             DatagramPacket toServer1 = new DatagramPacket(outToServer1, leng1, address, serverPort);
             DatagramPacket toServer2 = new DatagramPacket(outToServer2, leng2, address, serverPort);
             DatagramPacket toServer3 = new DatagramPacket(outToServer3, leng3, address, serverPort);
             ClientSocket.send(toServer1); 
             ClientSocket.send(toServer2); 
             ClientSocket.send(toServer3); 
             byte inFromServer[]; 
             inFromServer = new byte[256]; 
             leng1 = inFromServer.length; 
             DatagramPacket fromServer = new DatagramPacket(inFromServer, leng1); 
             ClientSocket.receive(fromServer); 
             String data; 
             data = (new String(fromServer.getData(),0,fromServer.getLength())).trim(); 
             System.out.println("Ket Qua :"+data); 
             ClientSocket.close(); 
             return data    ;
    }
}
