import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
	//Vector to store active clients
	static Vector<ClientHandler> ar = new Vector<>();
	//Vector to store the chatroom of the selected client.
	//static Vector<string> chatroom = new Vector<>();
	//static Vector<string> user = new Vector<>();
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

			dos.writeUTF(Integer.toString(i));
			string chatrooms_list = "";
			for(int index=0; index < chatroom.size(); index++){
				string var = chatroom.get(index);
				chatrooms_list += "[" + var + "], ";
			}
			string username = dis.readUTF();
			dos.writeUTF(chatrooms_list);
			string chatroom_name = dis.readUTF();

			// Create a new handler object for handling this request.
			ClientHandler mtch = new ClientHandler(s,"client " + i, dis, dos, username, chatroom_name);
			
			Thread t = new Thread(mtch);
			
			System.out.println("Adding this client to active client list");
			ar.add(mtch);
			
			t.start();
			i++;
		}
	}
}

// ClientHandler class
class ClientHandler implements Runnable 
{
    Scanner scn = new Scanner(System.in);
    private String name;
    private String username;
    private String chatroom_name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    boolean isloggedin;
     
    // constructor
    public ClientHandler(Socket s, String name, DataInputStream dis, DataOutputStream dos, String username, String chatroom_name) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
	this.username = username;
	this.chatroom_name = chatroom_name;
        this.isloggedin=true;
    }
 
    @Override
    public void run() {
 
        String received;
        while (true) 
        {
            try
            {
                // receive the string
                received = dis.readUTF();
                 
                System.out.println(received);
                 
                if(received.equals("logout")){
                    this.isloggedin=false;
                    this.s.close();
                    break;
                }
                 
                // break the string into message and recipient part
 
                // search for the recipient in the connected devices list.
                // ar is the vector storing client of active users
                for (ClientHandler mc : Server.ar) 
                {
                    // if the recipient is found, write on its
                    // output stream
                    if (mc.name.equals(recipient) && mc.isloggedin==true && mc.chatroom_name.equals(this.chatroom_name))
                    {
                        mc.dos.writeUTF(this.username+" : "+received);
                        break;
                    }
                }
            } catch (IOException e) {
                 
                e.printStackTrace();
            }
             
        }
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();
             
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
