package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import bttp.Bttp_IO;

public class BttpClient {
	private Socket socket;
	private Bttp_IO sio;
	private static final String END_SEQUENCE = "<end>";
	
	public BttpClient(String server_ip, int server_port) throws UnknownHostException, IOException {
		socket = new Socket(server_ip, server_port);
		sio = new Bttp_IO(socket);
	}
	
	public void bttp_dialog() throws IOException {
		Scanner sc = new Scanner(System.in);
		
		//System.out.println("Hello, I am the client.");
		String input, message;
		do {
			message = sio.read_line();
			
			System.out.println(message);
			
			input = sc.nextLine();
			if (input.equals(END_SEQUENCE) || message.contains(END_SEQUENCE))
				break;
			
			sio.direct_write_line(input);
			
		} while (!socket.isClosed() && !input.equals(END_SEQUENCE));
		
		socket.close();
		sc.close();
		//System.out.println("The client just closed");
	}
}
