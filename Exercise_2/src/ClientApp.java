import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientApp {

	public static void main(String[] args) {
		
		ItemProduct itemProduct = new ItemProduct();
		itemProduct.setName("McChicken Nugget 2 pcs");
		itemProduct.setPrice((float) 12.00);
		
		try {
			
			// Connect to server and send object to server
			int portNo = 4228;
			InetAddress serverAddress = InetAddress.getLocalHost();
			Socket socket = new Socket(serverAddress,portNo);
			ObjectOutputStream objectOutputStream =  new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(itemProduct);
			
			// read response from server
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			
			// if validation is successful
			if(objectInputStream.readBoolean()) {
				
				itemProduct = (ItemProduct) objectInputStream.readObject();
				
				// display item information on console
				System.out.println("Item Product ID: "+itemProduct.getItemProduct());
				System.out.println("Name of Product: "+itemProduct.getName());
				System.out.println("Price of Product: "+itemProduct.getPrice());
				
			}else {
				System.out.println("Validation failed for Item Product name: "+itemProduct.getName());
			}
			
			
			
			objectOutputStream.close();
			objectInputStream.close();
			socket.close();
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
