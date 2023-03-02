import java.util.Scanner;

public class Main {
	  public static void main(String[] args) {
	    Settings settings = new Settings();
	    Scanner scanner = new Scanner(System.in);

	    System.out.print("Enter First Name: ");
	    settings.setFirstName(scanner.nextLine());
	    System.out.print("Enter Email: ");
	    settings.setEmail(scanner.nextLine());
	    System.out.print("Enter Information: ");
	    settings.setInformation(scanner.nextLine());

	    settings.exportSettings();
	    settings.importSettings();
	    settings.exportInformation();
	    settings.importInformation();
	  }
}
