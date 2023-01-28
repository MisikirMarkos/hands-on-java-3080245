package bank;

import java.security.cert.CertPath;
import java.util.Scanner;

import javax.security.auth.login.LoginException;
import javax.xml.catalog.CatalogException;

import bank.Exceptions.AmountExceptions;

public class Menu {

  private Scanner scanner;
  
  public static void main (String[] args){
    System.out.println("Welcome to the Bank");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);
    
    Customer customer = menu.autheticateUser();
    if(customer != null){
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }

  private Customer autheticateUser(){
    System.out.println("username");
    String username = scanner.next();

    System.out.println("password");
    String password = scanner.next();

    Customer customer = null;
    try{
      customer = Authenticator.login(username, password);
    } catch(LoginException e){
      System.out.println("There was error: " + e.getMessage());
    }
    
    return customer;
  }
  
  private void showMenu(Customer customer, Account account) {

    int selection = 0;

    while(selection != 4 && customer.isAuthenticated()){
      System.out.println("===============================");
      System.out.println("Select one of the following: ");
      System.out.println("1: Deposit");
      System.out.println("2: Withdraw");
      System.out.println("3: Check Balance");
      System.out.println("4: Exit");
      System.out.println("================================");

      selection = scanner.nextInt();
      double amount = 0;

      switch(selection){
        case 1:
        System.out.println("How much do you want to deposit?");
        amount = scanner.nextDouble();
        try {
          account.deposit(amount);
        }catch (AmountExceptions  e){
          System.out.println(e.getMessage());
          System.out.println("Try again");
        }
        break;

        case 2:
        System.out.println("How much do you want to withdraw?");
        amount = scanner.nextDouble();
        try {
          account.withdraw(amount);
        }catch(AmountExceptions e){
          e.getMessage()
        }
        break;

        case 3:
        System.out.println("current balance: " + account.getBalance());
        break;

        case 4:
        Authenticator.logout(customer);
        System.out.println("Thanks for using the bank");
        break;

        default:
        System.out.println("Invalid option");
        break;
      }

    }

  }
}
