import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
	//Vector to store active clients
	static Vector<ClientHandler> ar = new Vector<>();
	//Vector to store the chatroom of the selected client.
	static Vector<string> chatroom = new Vector<>();
	// counter for clients
	static int i = 0;
	
	public static void main(String[] args) throws IOException
	{
		//port considered is 1234
		ServerSocket ss = new ServerSocket(1234);
		Socket s;

		while (true)
		{
			s = ss.accept;
			System.out.println("New client request received : " + s);
			
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());

			System.out.println("Creating a new handler for this client...");
			
			// Create a new handler object for handling this request.
			ClientHandler mtch = new ClientHandler(s,"client " + i, dis, dos);
			
			// Create a new Thread with this object.
			Thread t = new Thread(mtch);
			
			System.out.println("Adding this client to active client list");
			
			//add this client to active clients list
			ar.add(mtch);
			
			//start the thread.
			t.start();
			
			// increment i for new client.
			// i is used for naming only, and can be replaced
			// by any naming scheme
			i++;
		}
	}
}
	private ServerSocket socket;
	private ConnectionListener connectionListener;
	// temp
	private List<Client> clientList = new ArrayList<Client>();
	// temp end
	
	public Server(int port) {
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		connectionListener = new ConnectionListener(this);
	}
