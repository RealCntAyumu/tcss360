package iterrations;
import java.util.Scanner;

/***
 * 
 * @author Abdirizak Ali
 *
 */
public class Setting {
  private String firstName;
  private String email;
  private String information;

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setInformation(String information) {
    this.information = information;
  }

  public String getInformation() {
    return information;
  }

  public void exportSettings() {
    System.out.println("Exporting Settings...");
    System.out.println("First Name: " + firstName);
    System.out.println("Email: " + email);
  }

  public void importSettings() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Importing Settings...");
    System.out.print("Enter First Name: ");
    firstName = scanner.nextLine();
    System.out.print("Enter Email: ");
    email = scanner.nextLine();
  }

  public void exportInformation() {
    System.out.println("Exporting Information...");
    System.out.println(information);
  }

  public void importInformation() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Importing Information...");
    System.out.print("Enter Information: ");
    information = scanner.nextLine();
  }
}
