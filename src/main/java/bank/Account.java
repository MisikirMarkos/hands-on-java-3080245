package bank;

import bank.Exceptions.AmountExceptions;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance){
    setId(id);
    setType(type);
    setBalance(balance);
  }


  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws AmountExceptions{
    if(amount < 1){
      throw new AmountExceptions("The minimum deposit is 1.00");
    }

    else{
      double newBalance =  balance + amount;
      setBalance(newBalance); 
      DataSource.updateAccountBalance(id, newBalance);
    }

  }

  public void withdraw(double amount) throws AmountExceptions {

    if (amount < 0) {
      throw new AmountExceptions("Withdraw must be more than 0");
    }
    else if(amount > getBalance()){
      throw new AmountExceptions("Not suffient fund");
    }
    else{
      double newBalance = balance - amount;
      setBalance(newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    }
  }

}
