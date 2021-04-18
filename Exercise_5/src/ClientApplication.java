
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * This program demonstrate the implementation of DatagramPacket and DatagramSocket
 * for UDP.  
 * 
 * This program represents a client-side application that sends  a text data from the client.
 * 
 * @author Cecilia
 *
 */
public class ClientApplication {

	public static void main(String[] args) {
		
		// The server port to which the client socket is going to connect
		final int SERVERPORT = 50001;

		int bufferSize = 1024;

		try {

			// Instantiate client socket
			DatagramSocket clientSocket = new DatagramSocket();

			// Get the IP address of the server
			InetAddress serverAddress = InetAddress.getByName("localhost");

			// Create buffer to send data
			byte sendingDataBuffer[] = new byte[bufferSize];

			// Convert data to bytes and store data in the buffer
			String sentence = "testing";
			sendingDataBuffer = sentence.getBytes();

			// Creating a UDP packet 
			DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer,
					sendingDataBuffer.length, serverAddress, SERVERPORT);

			// Sending UDP packet to the server
			System.out.println("Sending UDP packet with - "+sentence);
			clientSocket.send(sendingPacket);
			
			// Create buffer to receive data
			byte receivingDataBuffer [] = new byte[bufferSize];
			
			// Receive data packet from server
		    DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer,
		    		receivingDataBuffer.length);
		    clientSocket.receive(receivingPacket);
		    
		    // Unpack packet
		    String message = new String(receivingPacket.getData()).trim();
		    message = message.trim();
		    int messageLength = Integer.valueOf(message);
		    System.out.println("Response received from server: "+messageLength);

			// Closing the socket connection with the server
			clientSocket.close();
			
		} catch (Exception ex) {

			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}

	}

}
