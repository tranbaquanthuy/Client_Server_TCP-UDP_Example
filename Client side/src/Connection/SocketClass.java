package Connection;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import javax.xml.bind.DatatypeConverter;
public class SocketClass {
    private static Socket socket = null;
    private final String serverHost = "";
    private final int serverPort = 8088;
    BufferedReader reader;
    PrintWriter writer;
    public void CreateSocket() throws UnknownHostException, IOException {
	    try {
	    	SocketAddress sockaddr = new InetSocketAddress(serverHost, serverPort);
	    	socket = new Socket();
	    	socket.connect(sockaddr, 5000);
	    	reader  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	writer = new PrintWriter( socket.getOutputStream(), true);	
	    } catch (UnknownHostException e) {
	        System.err.println("Don't know about host " + serverHost);
	        return;
	    } catch (IOException e) {
	        System.err.println("Couldn't get I/O for the connection to " + serverHost);
	        return;
	    }
    }
    public void CreateSocket(String Host,int port) throws UnknownHostException, IOException {
    	String addr = "";
    	try {
	    	 addr = InetAddress.getByName(Host).getHostAddress();
	    	SocketAddress sockaddr = new InetSocketAddress(addr, port);
	    	socket = new Socket();
	    //	socket = new Socket(InetAddress.getByName(Host).getHostAddress(), 80);
	    	socket.connect(sockaddr, 1000);
	   
	   	
	    	reader  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	writer = new PrintWriter( socket.getOutputStream(), true);	
	    } catch (UnknownHostException e) {
	        System.err.println("Don't know about host " + addr);
	        return;
	    } catch (IOException e) {
	        System.err.println("Couldn't get I/O for the connection to " + addr);
	        return;
	    }
    }
    public void CloseConnection() {
    	try {
    		reader.close();
    		writer.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public String RecievedPacket2() {
		 try { 
			String line;
			String n  =  "";
		    while ((line = reader.readLine()) != null ) {
		    	n = n + line;
		    	n = n + "\n";
		    	System.out.println(line);
		    	if(line.endsWith("</html>"))
		    	{
		    		break;
		    	}
		    }    
		    return n;
		} catch (IOException e) {
			return "error connection IO exception occurs";
		}
		// return "error connection of another exception";
	}
    public String RecievedPacket() {
		 try { 
			String line;
			String n  =  "";
		    while ((line = reader.readLine()) != null) {
		    	System.out.println(line);
               n = n +line;
		    }
		    
		    return n;
		} catch (IOException e) {
			return "error connection IO exception occurs";
		}
	}

    public String encryptPacket(String packet)
    {
    	String encryptedpacket = DatatypeConverter.printBase64Binary(packet.getBytes(Charset.forName("UTF-8"))); 
		return encryptedpacket;
    }
   
    public void SendGetXML(String url) throws UnknownHostException, IOException {
	    CreateSocket();   
	    String packet =  "GET /example.xml HTTP/1.1";
	    writer.println(packet);
    }
    public void SendUSCLN(String n,String m) throws UnknownHostException, IOException {
	    CreateSocket();   
	    String packet =  "USCLN|.|14|.|6";
	    writer.println(encryptPacket(packet));
    }
    public void SendGetHTTP(String url) throws UnknownHostException, IOException {
	    CreateSocket();   
	    String packet =  "GET /2.html HTTP/1.1";
	    writer.println(packet);
    }
    public void SendGetHTTP(String url,String Host,int Port) throws UnknownHostException, IOException {
	    CreateSocket(Host,Port);   
	    String packet =  "GET / HTTP/1.1\r\n"+  "Host: "+ Host + "\r\n\r\n";
	    writer.print(packet);
	    writer.flush();
    }
	public void SendLogin(String username,String password) throws UnknownHostException, IOException {
		    CreateSocket();   
		    String type = "Login";
		    String packet = username + "|.|" + password+"|.|" + type;
		    writer.println(encryptPacket(packet));
	}
	public void SendRaw(String m) {
		    String packet = m; 
		    writer.println(packet);	    
	}
	public void SendCaculate(String n,String m,String g) throws UnknownHostException, IOException {
		    CreateSocket();
		    String type = "Caculate";
		    String packet =  n +  "|.|"+ m + "|.|" + g + "|.|" + type;
		    writer.println(encryptPacket(packet));	   
    }
	public void SendDisconnect()  {	   
		    String type = "Disconnect";
		    String packet = "1|.|1|.|" + type;
		    writer.println(encryptPacket(packet));	    
	}
	public void SendChat(String m,String username)  {	   
	    String packet = m  + "|.|" + username + "|.|Chat" ;
	    writer.println(encryptPacket(packet));	    
}

	public void SearchLoaiSach()  {
		    String type = "SearchLoaiSach";
		    String packet = "1" + "|.|" + "1"+"|.|" + type;
		    writer.println(encryptPacket(packet));
	}
	public void CreateAccount(String username,String password,String name,String id, String chucVu)  {
		    String type = "CreateAccount";
		    String packet = username + "|.|" + password+ "|.|" + name  +"|.|" + id + "|.|" + chucVu + "|.|"  + type; 
		    writer.println(encryptPacket(packet));
	}
	public void CreateLoaiSach(String tenLoaiSach,String maLoaiSach)  {
		    String type = "CreateLoaiSach";
		    String packet = tenLoaiSach + "|.|" + maLoaiSach+ "|.|"  + type;
		    writer.println(encryptPacket(packet));
	}
	public void CreateSach(String maloaiSach,String maTacGia,String maSach,String tenSach , String giatien) {
		    String type = "CreateSach";
		    String packet = maloaiSach + "|.|" + maTacGia+ "|.|" + maSach  +"|.|" + tenSach + "|.|"  + giatien + "|.|"  + type;
		    writer.println(encryptPacket(packet));
	}
	public void UpdateAccount(String username,String password,String name,String id, String chucVu) {
		    String type = "UpdateAccount";
		    String packet = username + "|.|" + password+ "|.|" + name  +"|.|" + id + "|.|" + chucVu + "|.|"  + type; 
		    writer.println(encryptPacket(packet));
	}
	public void UpdateLoaiSach(String tenloaisach,String maloaisach) {
		    String type = "UpdateLoaiSach";
		    String packet = tenloaisach + "|.|" + maloaisach  + "|.|"  + type;
		    writer.println(encryptPacket(packet));
	}
	public void SearchAccount() {
		    String type = "SearchAccount";
		    String packet = "1" + "|.|" + "1" +"|.|" + type;
		    writer.println(encryptPacket(packet));
	}
	public void DeleteAccount(String username) {
		    String type = "DeleteAccount";
		    String packet = "1" + "|.|" + username +"|.|" + type;
		    writer.println(encryptPacket(packet));
	}
	public void DeleteLoaiSach(String maloaisach){
		    String type = "DeleteLoaiSach";
		    String packet = "1" + "|.|" + maloaisach +"|.|" + type; 
		    writer.println(encryptPacket(packet));
	}
	public void SearchBook(String datasearch,String attribute)  {
		    String type = "Search";
		    String packet = datasearch + "|.|" + attribute+"|.|" + type; 
		    writer.println(encryptPacket(packet));
	}
	public void SearchBookAdvanced(String tentacgia,String loaisach)  {
		    String type = "SearchAdvanced";
		    String packet = tentacgia + "|.|" + loaisach + "|.|" + type;
		    writer.println(encryptPacket(packet));
	}
	public String[] HandlePacket(String n)
	{
		if(n.equals("no data"))
		{
			System.out.println("not data found");  
			return null;
		}
		else if  (n.equals("error connection") )
		{
			String[] list = new String[] {n};
			return list;
		}
		else 
		{ 
			 System.out.println(n);
		    String[] list = n.split("XTX");  //Login packet structure	
		   
			for(int i=0;i<list.length;i++)
			{
			   StringBuilder sb = new StringBuilder();
		       sb.append(list[i].substring(2,list[i].length()-1));
		       sb.append(System.lineSeparator());
		       list[i] = new String(DatatypeConverter.parseBase64Binary(sb.toString()));  
			   System.out.println( list[i] );	
			} 
			return list;
		}
	}

}
