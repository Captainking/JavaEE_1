package javaee_1;

import java.io.*;
import java.net.*;


public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Socket cSocket=null;
		PrintWriter out=null;
		BufferedReader in=null;
		String userInput;
		try {
			cSocket=new Socket("127.0.0.1",3333);
			out=new PrintWriter(cSocket.getOutputStream(),true);
			in=new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
			while((userInput=stdIn.readLine())!=null){
				System.out.println("客户端发送消息:"+userInput);
				out.println(userInput);
				System.out.println("客户端收到消息:"+in.readLine());
				if(userInput.equals("bye")){
					break;
				}
			}
			System.out.println("客户端程序退出");
			out.close();
			in.close();
			cSocket.close();
			stdIn.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch blockhhhh
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
	}

}