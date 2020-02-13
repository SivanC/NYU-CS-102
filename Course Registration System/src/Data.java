import java.lang.Math;

public class Data {
	public static void main(String[] args) {
		System.out.println(Data.generateName());
	}
	public static String generateName() {
		String[] first = {"Ben", "Jorge", "Maya", "Catalina", "Richard", "Barnaby", "Winston", "Gretchen", "Helga", 
				"Lola",  "Bill", "DeMarcus", "Roy", "Lonzo", "Anya", "Ralph"};
		String[] last = {"Khaled", "Nguyen", "Patel", "Cooperman", "Shaw", "Lee", "Yamashita", "Applebaum", "Goldberg", "Williams", "Baker-Smith",
				"Wisenbacher", "Griffin", "Pesci", "Coltrane", "Ellison", "Jefferson", "Wang", "Kim", "Weissman", "Kong"};
		String name = first[(int) (Math.random() * first.length)] + " " + last[(int) (Math.random() * last.length)];
		return name;
	}
}
