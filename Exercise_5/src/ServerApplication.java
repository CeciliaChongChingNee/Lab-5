
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * How to run this application?
 * 1. Open Terminal
 * 2. Change directory to workspace-dad/udplab/bin
 * 3. Type java exe1.upd.receiver.ServerApplication
 * 
 * @author Cecilia
 *
 */
public class ServerApplication {

	public static void main(String[] args) {
		
		
		// Server UDP socket runs at this port
		final int serverPort=50001;
		
		try {
			
			// Instantiate a new DatagramSocket to receive responses from the client
		    DatagramSocket serverSocket = new DatagramSocket(50001);
		    
		    // Create buffers to hold receiving data.
		    byte receivingDataBuffer[] = new byte[1024];
		    
		    // Instantiate a UDP packet to store the client data using the buffer for receiving data
		    DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
		    System.out.println("Waiting for a client to connect...\n");
		    
		    // Receive data from the client and store in inputPacket
		    serverSocket.receive(inputPacket);
		    
		    // Printing out the client sent data
		    String receivedData = new String(inputPacket.getData()).trim();
		    System.out.println("Sent from the client: " + receivedData);
		    System.out.println();
		    
		    // Process data - count length and send as int
		    String messageLength = Integer.toString(receivedData.length());
		    
		    // Creating corresponding buffer to send data
		    byte sendingDataBuffer[] = messageLength.getBytes();
		    
		    // Get client's address
		    InetAddress senderAddress = inputPacket.getAddress();
		    int senderPort = inputPacket.getPort();
		    
		    // Create new UDP packet with data to send to the client
		    DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, 
		    		sendingDataBuffer.length, senderAddress,senderPort);
		    
		    // Send the created packet to client
			System.out.println("Sending UDP packet with - "+messageLength);
		    serverSocket.send(outputPacket);
		    
		    // Close the socket connection
		    serverSocket.close();
		      
		} catch (Exception ex) {
			
			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}

	}

}
