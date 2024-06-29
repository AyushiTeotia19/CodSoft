import java.util.*;
public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount UserAccount = new BankAccount(1000.0); 

        while (true) {
            System.out.println("Welcome to the ATM!");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdrawl");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1/2/3/4): ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Your balance is: ₹" + UserAccount.currentBalance());
                    break;
                case 2:
                    System.out.print("Enter the deposit amount: ₹");
                    double depositAmount = scanner.nextDouble();
                    UserAccount.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter the withdrawal amount: ₹");
                    double withdrawalAmount = scanner.nextDouble();
                    boolean success = UserAccount.withdrawl(withdrawalAmount);
                    if (success) {
                        System.out.println("Remaining balance: ₹" + UserAccount.currentBalance());
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM!");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}

        class BankAccount {
            private double balance;

            public BankAccount(double initialBalance) {
                balance = initialBalance;
            }

            public double currentBalance() {
                return balance;
            }

            public void deposit(double amount) {
                if (amount > 0) {
                    balance = balance + amount;
                    System.out.println("₹" + amount + "is Deposited Successfully.");
                } else {
                    System.out.println("Invalid Deposit Amount.");
                }
            }

            public boolean withdrawl(double amount) {
                if (amount > 0 && amount <= balance) {
                    balance = balance - amount;
                    System.out.println("₹" + amount + " is Credited Successfully.");
                    return true;
                } else {
                    System.out.println("Insufficient Amount or Invalid Withdrawal Amount.");
                    return false;
                }
            }
        }


