/*
//	Two Phase Commit protocol: Client Application
//	Auther: Devender A
//	Date : 21-09-2012
//	Hyderabad
*/

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

class Client extends JFrame implements ActionListener{
	JButton b1,b2,b4,b5;
	JPanel p1,p2;
	JTextField t1;
	JLabel l1;
	ServerSocket ss;
	Socket s;
	DataOutputStream output;
	DataInputStream input;
	Connection con;
	Statement stmt;
	String serverMessage="Prepared";
	static int port;

	Client(){
		b1=new JButton("Prepared");
		b2=new JButton("NotPrepared");

		b4=new JButton("Execute");
		b5=new JButton("Exit");
		t1=new JTextField("",35);
		l1=new JLabel("SQL");
		p1=new JPanel();
		p2=new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(l1);
		p1.add(t1);
		p2.add(b1);
		p2.add(b2);
		p2.add(b4);
		p2.add(b5);
		add(p1);
		add(p2,"South");
		setSize(600,300);
		setTitle("DNS Client");
		setVisible(true);
		b1.addActionListener(this);
		b2.addActionListener(this);

		b4.addActionListener(this);
		b5.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		MulticastSocket ms =null;
		InetAddress group ;
		try
		{
			s = new Socket("localhost",8088);
			System.out.println("Client Connected");
			output=new DataOutputStream(s.getOutputStream());
			input=new DataInputStream(s.getInputStream());
			con = DBConnector.getDBConnection();
			stmt = con.createStatement();
			con.setAutoCommit(false);
			ms = new MulticastSocket(8899);	
			group= InetAddress.getByName("228.5.6.7");
			ms.joinGroup(group);
			byte[] buffer = new byte[1024];
			while (true)
			{
 				DatagramPacket serMsg= new DatagramPacket(buffer, buffer.length);
				ms.receive(serMsg);
				String commitMsg = new String (serMsg.getData()).trim();
				if (commitMsg.equals("commit"))
				{
					System.out.println("Received "+commitMsg);
					con.commit();
					System.out.println("Transactions Committed");
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent ae){
		try
		{
			String str=ae.getActionCommand(); 

			if(str.equals("Execute")){
				String query = t1.getText();
				System.out.println(stmt.executeUpdate(query));
				t1.setText("Query Executed(NotPrepared)");
				output.writeUTF("NotPrepared");
			}

			if(str.equals("Prepared")){
				output.writeUTF("Prepared");
			}

			if(str.equals("NotPrepared")){
			output.writeUTF("NotPrepared");
			}

			if(str.equals("Exit")){
				System.exit(0);
				stmt.close();
				con.close();
			}
		}
		catch(Exception e){
			JLabel errorFields = new JLabel("<HTML><FONT COLOR = BLUE>"+e.getMessage()+"</FONT></HTML>");	
			JOptionPane.showMessageDialog(null,errorFields); 
			e.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Client c=new Client();
	}
}