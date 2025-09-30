import java.net.*;
import java.io.*;

public class EchoServer {

	public static void main(String[] args) throws IOException {

		// Check the arguments
		if (args.length != 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.err.println("port should be a number between 1025 and 65535\n");
			System.exit(1);
		}


		int portNumber = Integer.parseInt(args[0]);
		byte[] buffer = new byte[256];
		DatagramPacket DpReceive = null;
		DatagramSocket  serverSocket = null;

		try {
			//Creates a DatagramSocket and binds to the specified port on the local machine
			serverSocket = new DatagramSocket (portNumber);
			
		} catch (SocketException e) {
			System.out.println("Socket could not be opened, or the socket could not bind to the specified port " + portNumber );
			System.out.println(e.getMessage());
		}

		while(true){
			System.out.println("Waiting ....");  
			//clear the buffer
			buffer = new byte[256];

			//Constructs a DatagramPacket for receiving the data of length buffer.length in the byte array buffer 
			DpReceive = new DatagramPacket(buffer, buffer.length);
			
			//The receive method blocks until a message arrives and it stores the message
			//inside the byte array of the DatagramPacket passed to it.
			serverSocket.receive(DpReceive);
			
		    // convert the byte array into a string message  
			String received = new String(DpReceive.getData()).trim();
			//or
			//String received = new String(buffer, 0, DpReceive.getLength());

			if (received.equalsIgnoreCase("exit")) {
				System.out.println("message recieved: " + received);
				System.out.println("exiting");
				serverSocket.close();
				break;
			}
			
			System.out.println("server received " +received.length()+" bytes"+ "\n"+ received);
			System.out.println("---------------------------------" );
		}



	}
}