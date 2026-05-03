import java.util.Scanner;
class User 
{
    String username;
    String password;
    String email;
    double balance;

    // Constructor
    public User(String username, String password, String email) 
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = 0.0;
    }

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

    public double getBalance() 
    {
        return balance;
    }

    public void setBalance(double balance) 
    {
        this.balance = balance;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }
}

class week_4 
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

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        // Create new User object and add it to users array
        users[userCount] = new User(username, password, email);
        userCount++;
        System.out.println("Registration successful.");
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
