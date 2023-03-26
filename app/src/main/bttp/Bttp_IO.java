package bttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import bttp.BttpEncoder;

public class Bttp_IO{
	private Socket client_socket;
	private BufferedReader reader;
	private PrintWriter writer;
	
	public Bttp_IO(Socket socket) throws IOException {
		client_socket = socket;
		
		InputStream is = socket.getInputStream();
		reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
		
		OutputStream os = socket.getOutputStream();
		writer = new PrintWriter(os, true);
	}
	
	public String read_line() throws IOException {
		String line = reader.readLine();
		if (line == null)
			throw new IOException();
			
		return BttpEncoder.decode(line);
	}
	
	public String timedout_read_line() throws IOException {
		
		String message = read_line();
		
		return message;
	}
	
	public void direct_write_line(String s) {
		writer.println(BttpEncoder.encode(s));
	}
	
	public void buffered_write_line(String s) {
		writer.write(BttpEncoder.encode(s));
	}
	
}

class TimeOut{
	
}