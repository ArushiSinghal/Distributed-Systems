import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Client 
{
	final static int ServerPort = 1234;
	public static void main(String args[]) throws UnknownHostException, IOException 
	{
		Scanner scn = new Scanner(System.in);
		// getting localhost ip
		InetAddress ip = InetAddress.getByName("localhost");
		
		// establish the connection
		Socket s = new Socket(ip, ServerPort);
		
		//obtaining input and out streams
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		String client_id = dis.readUTF();
		
		System.out.println("Enter your username,default will be your client id: ");
		String username = scn.nextLine();
		if(username.length() <= 0)
			username = "client" + client_id;
		else
			username = username +" client" + client_id;

		dos.writeUTF(username);

		string chatrooms = dis.readUTF();
		System.out.println("Available chatrooms:");
		System.out.println(chatrooms);
		System.out.println("Please choose either from the list or select new chatroom: ");
		
		String chatroom_name = scn.nextLine();
		
		while(chatroom_name.length() <= 0){
			System.out.println("Please enter valid-input name of chatroom: ");
			chatroom_name = scn.nextLine();
		}
		dos.writeUTF(chatroom_name);

		if(obj instanceof Integer){
			System.out.println((Integer)obj);
		}else if(obj instanceof String){
			System.out.println((String)obj);
		}
		
		// sendMessage thread
		Thread sendMessage = new Thread(new Runnable()
		{
			@Override
			public void run() {
				while (true) {
					// read the message to deliver.
					String msg = scn.nextLine();
					try {
						// write on the output stream//
						dos.writeUTF(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		// readMessage thread
		Thread readMessage = new Thread(new Runnable()
		{
			@Override
			public void run() {
				while (true) {
					try {
						// read the message sent to this client
						String msg = dis.readUTF();
						System.out.println(username + ": " +msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		sendMessage.start();
		readdMessage.start();
	}
}
