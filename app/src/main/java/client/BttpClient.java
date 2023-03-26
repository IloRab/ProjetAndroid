package client;

import java.io.IOException;
import java.net.Socket;

import bttp.Bttp_IO;

public class BttpClient implements Runnable{
	private Socket socket;
	private Bttp_IO sio;

	private IHM ihm;
	private static final String END_SEQUENCE = "<end>";

	private String server_ip;
	private int server_port;
	
	public BttpClient(String server_ip, int server_port, IHM ihm) {
		this.server_ip = server_ip;
		this.server_port = server_port;
		this.ihm = ihm;
	}

	@Override
	public void run() {

		try {
			socket = new Socket(server_ip, server_port);
			sio = new Bttp_IO(socket);

			String input, message;
			do {
				message = sio.read_line();

				ihm.display_msg(message);

				input = ihm.query_user();
				if (input.equals(END_SEQUENCE) || message.contains(END_SEQUENCE))
					break;

				sio.direct_write_line(input);

			} while (!socket.isClosed() && !input.equals(END_SEQUENCE));

			socket.close();
		} catch (IOException e){
			throw new RuntimeException("Connection impossible");
		}
	}
}
