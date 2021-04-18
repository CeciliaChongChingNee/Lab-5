import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

public class ServerApp {

	public static void main(String[] args) {
		int counter = 1;
		try {
			
			int portNo = 4228;
			ServerSocket serverSocket = new ServerSocket(portNo);
			
			System.out.println("Waiting for request\n");
			
			while(true) {
				
				Socket clientSocket = serverSocket.accept();
				
				System.out.println("Connection from client successful\n");
				
				ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
				ItemProduct itemProduct = (ItemProduct) objectInputStream.readObject();
				
				System.out.println("Object received:");
				System.out.println("Item Product ID: "+itemProduct.getItemProduct());
				System.out.println("Name of Product: "+itemProduct.getName());
				System.out.println("Price of Product: "+itemProduct.getPrice());
				System.out.println();
				
				// Check for alphanumeric and spaces only name
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
				
				// Send validation result
				if(p.matcher(itemProduct.getName()).find()) {
					System.out.println("Sending validation result: "+false);
					System.out.println();
					objectOutputStream.writeBoolean(false);
				}else {
					System.out.println("Sending validation result: "+true);
					objectOutputStream.writeBoolean(true);
					itemProduct.setItemProduct(counter++);
					System.out.println("Sending object: ");
					System.out.println("Item Product ID: "+itemProduct.getItemProduct());
					System.out.println("Name of Product: "+itemProduct.getName());
					System.out.println("Price of Product: "+itemProduct.getPrice());
					System.out.println();
					objectOutputStream.writeObject(itemProduct);					
				}
				
				objectOutputStream.close();
				objectInputStream.close();
				
			}
			
			
		}catch(ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		

	}

}
