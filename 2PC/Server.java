/*
//	Two Phase Commit protocol: Server Application
//	Auther: Devender A
//	Date : 21-09-2012
//	Hyderabad
*/
import java.io.*;
import java.net.*;
class Clients
{
	static int n;
	static String[] status= new String[2];
	Clients(int num){
		n=num;
		for (int j=0;j<n ;j++ )
		{
			status[j] = new String("NotPrepared");
		}
	}
}
class Coordinator implements Runnable
{
		public static int i=-1;
		int flag=1;
		Socket s;Thread t;
		MulticastSocket ms =null;
		InetAddress group ;
		Coordinator(Socket c){
			try
			{
				ms = new MulticastSocket(8899);	
				group= InetAddress.getByName("228.5.6.7");
				ms.joinGroup(group);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			s=c;
			t = new Thread(this);
			t.start();
			i++;
		}
		public void run(){
			int index = i;
			String clientSattus;
			try
			{
				DataInputStream input=new DataInputStream(s.getInputStream());
				DataOutputStream output=new DataOutputStream(s.getOutputStream());
				while (true)
				{
					clientSattus = input.readUTF();
					System.out.println("Client "+index+" "+clientSattus);
					Clients.status[index] = new String (clientSattus); 
					for (int k=0;k<Clients.n; k++)
					{	
						System.out.println(Clients.status[k]);
						if (Clients.status[k].equalsIgnoreCase("prepared"))
							continue;
						else
							flag=0;
					}
					if (flag==1){
						byte[] msg = new String("commit").getBytes();
						DatagramPacket msgpack = new DatagramPacket(msg, msg.length, group, 8899);
						ms.send(msgpack);
						System.out.println("BroadCasted msg "+new String(msg));
					}
					flag=1;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
}
class Server 
{
	public static ServerSocket ss; 
	Server(){
	}
	public static void main(String args[]) throws Exception
	{
		ss = new ServerSocket(8088);
		int num;
		num = Integer.parseInt(args[0]);
		new Clients(num);
		while (true)
		{	
			System.out.println("Server waiting: ");
			Socket s = ss.accept();
			new Coordinator(s);
		}
	}
}