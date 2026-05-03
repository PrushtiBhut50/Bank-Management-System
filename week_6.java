import java.util.Scanner;

class Person 
{
    protected String name;
    protected String email;

    public Person(String name, String email) 
    {
        this.name = name;
        this.email = email;
    }
}

abstract class Account 
{
    protected String accountNumber;
    protected double balance;
    protected User owner;

    public Account(String accountNumber, User owner) 
    {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0.0;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public double getBalance() 
    {
        return balance;
    }

    public User getOwner() 
    {
        return owner;
    }

    public void deposit(double amount) 
    {
        if (amount > 0) 
        {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } 
        else 
        {
            System.out.println("Invalid deposit amount.");
        }
    }

    public abstract void withdraw(double amount);
}

// --- Saving Account ---
class SavingAccount extends Account 
{
    private static final double MIN_BALANCE = 500.0;

    public SavingAccount(String accountNumber, User owner) 
    {
        super(accountNumber, owner);
    }

    @Override
    public void withdraw(double amount) 
    {
        if (balance - amount >= MIN_BALANCE) 
        {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } 
        else 
        {
            System.out.println("Withdrawal failed. Minimum balance of ₹" + MIN_BALANCE + " must be maintained.");
        }
    }
}

// --- Current Account ---
class CurrentAccount extends Account 
{
    private static final double OVERDRAFT_LIMIT = -1000.0;

    public CurrentAccount(String accountNumber, User owner) 
    {
        super(accountNumber, owner);
    }

    @Override
    public void withdraw(double amount) 
    {
        if (balance - amount >= OVERDRAFT_LIMIT) 
        {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } 
        else 
        {
            System.out.println("Withdrawal failed. Overdraft limit of ₹" + OVERDRAFT_LIMIT + " exceeded.");
        }
    }
}

// --- User Class ---
class User extends Person 
{
    private String username;
    private String password;
    private Account account;

    public User(String name, String email, String username, String password, Account account) 
    {
        super(name, email);
        this.username = username;
        this.password = password;
        this.account = account;
    }

    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }

    public Account getAccount() 
    {
        return account;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }
}

// --- Main Class ---
public class week_6
{
    static final int MAX_USERS = 100;
    static User[] users = new User[MAX_USERS];
    static int userCount = 0;
    static int loggedInUserIndex = -1;

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        boolean bank = true;

        while (bank) 
        {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) 
            {
                case 1:
                    registerUser(sc);
                    break;
                case 2:
                    loginUser(sc);
                    break;
                case 3:
                    logoutUser();
                    break;
                case 4:
                    bank = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void registerUser(Scanner sc) 
    {
        if (userCount >= MAX_USERS) 
        {
            System.out.println("User limit reached.");
            return;
        }

        System.out.print("Enter full name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        for (int i = 0; i < userCount; i++) 
        {
            if (users[i].getUsername().equals(username)) 
            {
                System.out.println("Username already taken. Please try again.");
                return;
            }
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.println("Select Account Type:");
        System.out.println("1. Saving Account");
        System.out.println("2. Current Account");
        System.out.print("Enter choice: ");
        int accType = sc.nextInt();
        sc.nextLine(); // newline

        String accountNumber = "ACC" + (1000 + userCount);
        Account account;

        User tempUser = new User(name, email, username, password, null);

        if (accType == 1) 
        {
            account = new SavingAccount(accountNumber, tempUser);
        } 
        else if (accType == 2) 
        {
            account = new CurrentAccount(accountNumber, tempUser);
        } 
        else 
        {
            System.out.println("Invalid account type. Registration failed.");
            return;
        }

        User newUser = new User(name, email, username, password, account);
        users[userCount] = newUser;
        userCount++;

        System.out.println("Registration successful.");
        System.out.println("Bank account created. Account Number: " + accountNumber);
    }

    static void loginUser(Scanner sc) 
    {
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        for (int i = 0; i < userCount; i++) 
        {
            if (users[i].getUsername().equals(username) && users[i].getPassword().equals(password)) 
            {
                loggedInUserIndex = i;
                System.out.println("Login successful. Welcome, " + username + "!");
                showAccountMenu(sc, users[i]);
                return;
            }
        }

        System.out.println("Login failed. Invalid credentials.");
    }

    static void logoutUser() 
    {
        if (loggedInUserIndex == -1) 
        {
            System.out.println("You are not logged in.");
        } 
        else 
        {
            System.out.println("User " + users[loggedInUserIndex].getUsername() + " logged out.");
            loggedInUserIndex = -1;
        }
    }

    static void showAccountMenu(Scanner sc, User user) 
    {
        boolean session = true;
        Account account = user.getAccount();

        while (session) 
        {
            System.out.println("\n--- Account Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Logout to Main Menu");
            System.out.print("Enter choice: ");
            int option = sc.nextInt();

            switch (option) 
            {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double dep = sc.nextDouble();
                    account.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double wd = sc.nextDouble();
                    account.withdraw(wd);
                    break;
                case 3:
                    System.out.println("Account Number: " + account.getAccountNumber());
                    System.out.println("Current Balance: ₹" + account.getBalance());
                    break;
                case 4:
                    session = false;
                    logoutUser();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
