package javaee_1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class MultiServer extends Thread{

	/**
	 * @param args
	 */
	static ServerSocket serverSocket=null;
	Socket clientSocket=null;
	static int count=1;
	private int clientID=1;
	
	private MultiServer(Socket clientSoc){
		clientSocket=clientSoc;
		clientID=count++;
		start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader in;
		PrintWriter out;
		String inputline;
		try {
			out=new PrintWriter(clientSocket.getOutputStream(),true);
			in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			while((inputline=in.readLine())!=null){
				System.out.println("recive message from client "+clientID+": "+inputline);
				
				char[] arr =inputline.toCharArray(); //逆序输出字符数组
				char []arr1 = new char[arr.length];
				for(int i=arr.length-1;i>=0;i--)
				{ 
					arr1[arr.length-1-i]=arr[i];
				}
				String str=new String(arr1);
				
				out.println(str);//返回发送倒叙信息
				if (inputline.equals("bye")){
					break;
				}
			}
			
			System.out.println("终止客户端"+clientID);
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("服务器开启");
		try {
			serverSocket=new ServerSocket(3333);
			while(true){
				new MultiServer(serverSocket.accept());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
