import java.util.Scanner;
class BankAccount 
{
    private String accountNumber;
    private double balance;
    private User owner;

    public BankAccount(String accountNumber, User owner) 
    //This is a constructor that runs when a new BankAccount is created.
    {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0.0; // default balance
    }


    // Getters and Setters
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

    public void setBalance(double balance) 
    {
        this.balance = balance;
    }
}

// User class for user details
class User 
{
    private String username;
    private String password;
    private String email;
    private BankAccount bankAccount; // Link to one bank account

    //creating a new user with all fields
    public User(String username, String password, String email, BankAccount bankAccount) 
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bankAccount = bankAccount;
    }

    //access or update the user's information
    // Getter and Setter Methods
    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }

    public String getEmail() 
    {
        return email;
    }

    public BankAccount getBankAccount() 
    {
        return bankAccount;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }
}

class week_5 
{
    static final int MAX_USERS = 100;
    static User[] users = new User[MAX_USERS]; // Array of User objects
    static int userCount = 0;
    static int loggedInUserIndex = -1; // Keeps track of the logged-in user index

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        boolean bank = true;

        while (bank) {
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

    // Registration method to register a new user
    static void registerUser(Scanner sc) 
    {
        if (userCount >= MAX_USERS) 
        {
            System.out.println("User limit reached.");
            return;
        }

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        // Check if the username already exists
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

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        // Generate a simple account number
        //e.g., ACC1000, ACC1001, etc.
        String accountNumber = "ACC" + (1000 + userCount);

        // Create temporary user with null bank account to pass to BankAccount constructor
        User tempUser = new User(username, password, email, null);

        // Create BankAccount and assign to user
        BankAccount bankAccount = new BankAccount(accountNumber, tempUser);

        // Now update the user to include the bank account
        User newUser = new User(username, password, email, bankAccount);

        users[userCount] = newUser;
        userCount++;

        System.out.println("Registration successful.");
        System.out.println("Bank account created.\n Account Number: " + accountNumber);
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
                System.out.println("Your Account Number: " + users[i].getBankAccount().getAccountNumber());
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
}
