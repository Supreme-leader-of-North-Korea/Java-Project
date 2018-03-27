import java.util.Scanner;


public class Guest {
    private String name, address, country, gender, nationality, identity;
    private int credit, contact;
    
    public Guest(){
        
    }
    
    public void create() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        name = s.nextLine();
		System.out.println("Please enter your address: ");
		address = s.nextLine();
		System.out.println("Please enter your country: ");
		country = s.nextLine();
		System.out.println("Please enter your gender: ");
		gender = s.next();
		System.out.println("Please enter your nationality: ");
		nationality = s.nextLine();
		System.out.println("Please enter your identity[(D)riving License/(P)assport]: ");
		identity = s.nextLine();
		System.out.println("Please enter your credit card detail: ");
		credit = s.nextInt();
		System.out.println("Please enter your contact number: ");
		contact = s.nextInt();
    }
}