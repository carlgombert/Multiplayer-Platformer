package core.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.SwingUtilities;

import core.Game;
import util.MathUtil;

public class Client implements Runnable{

	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private boolean done;
	private String hostIP;
	private int id;
	
	
	public Client() {
		try(final DatagramSocket socket = new DatagramSocket()){
			  socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			  hostIP = socket.getLocalAddress().getHostAddress();
		} catch (UnknownHostException | SocketException e) {
			//ignore
		}
	}
	
	
	@Override
	public void run() {
		try {
			client = new Socket(hostIP, 9999);
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			out.println("/init " + Game.playerHandler.getPlayer().getId() + " " + Game.playerHandler.getPlayer().getSkinName() + " " + Game.playerHandler.getPlayer().getWorldX() + " " + Game.playerHandler.getPlayer().getWorldY());
			
			String inMessage;
			while((inMessage = in.readLine()) != null) {
				boolean isNewPlayer = ServerInput.decodeMessage(inMessage);
				if(isNewPlayer) {
					out.println("/init " + Game.playerHandler.getPlayer().getId() + " " + Game.playerHandler.getPlayer().getSkinName() + " " + Game.playerHandler.getPlayer().getWorldX() + " " + Game.playerHandler.getPlayer().getWorldY());
				}
			}
		} catch (IOException e) {
			shutdown();
		}
		
	}
	
	public void shutdown() {
		done = true;
		try {
			in.close();
			out.close();
			if(!client.isClosed()) {
				client.close();
			}
		} catch (IOException e) {
			// ignore
		}
		
	}
	
	public void sendMessage(String message) {
		if(!done && !client.isClosed()) {
			out.println(message);
		}
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}
}
