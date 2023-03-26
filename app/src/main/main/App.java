package main;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import client.BttpClient;

public class App {
	//TODO make an enum

}

	public static void main(String[] args) {
		try {
			BttpClient c = new BttpClient("localhost", 3000);
			c.bttp_dialog();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();}
		catch (ConnectException e) {
			//System.out.println("Le serveur a refusé la connection.");
			//System.out.println("Shutting donw...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
	}

}
