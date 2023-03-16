package iterrations;
import java.util.Scanner;
/***
 * 
 * @author Abdirizak Ali
 *
 */
public class Main {
	  public static void main(String[] args) {
	    Setting settings = new Setting();
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
