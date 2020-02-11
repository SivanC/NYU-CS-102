import java.io.*;

public class IOTestOut {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please enter a length:\n");
		int l = Integer.parseInt(in.readLine());
		System.out.println("Please enter a width:\n");
		int w = Integer.parseInt(in.readLine());
		
		Rectangle rect = new Rectangle(l, w);
		
		System.out.println(rect.calculateArea());

	}

}