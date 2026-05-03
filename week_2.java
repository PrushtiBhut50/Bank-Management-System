import java.util.Scanner;
class week_2 
{
    static final int MAX_USERS = 100;
    static String[][] userDetails = new String[MAX_USERS][2];//username,password 
    static double[] balances = new double[MAX_USERS];
    static int userCount = 0;

    static Scanner sc = new Scanner(System.in);

    	public static void main(String[] args) 
	{
        int choice;
        int loggedInIndex = -1;
	//(loggedInIndex)tracks which user is currently logged in (-1 means no one is logged in).

        do 
	{
            showMenu(loggedInIndex != -1);
            choice = Integer.parseInt(sc.nextLine());

            	switch (choice) 
		{
                case 1:
                    registerUser();
                    break;

                case 2:
                    loggedInIndex = loginUser();
                    break;

                case 3:
                    loggedInIndex = -1;
                    System.out.println("Logged out.");
                    break;

                case 4:
                    if (loggedInIndex != -1) 
			depositMoney(loggedInIndex);
                    else 
			System.out.println("Please login first.");
                    break;

                case 5:
                    if (loggedInIndex != -1) 
			withdrawMoney(loggedInIndex);
                    else 
			System.out.println("Please login first.");
                    break;
	
                case 6:
                    if (loggedInIndex != -1) 
			showBalance(loggedInIndex);
                    else 
			System.out.println("Please login first.");
                    break;

                case 7:
                    if (loggedInIndex != -1) 
			viewAccountDetails(loggedInIndex);
                    else 
			System.out.println("Please login first.");
                    break;

                case 8:
                    System.out.println("Exiting form program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

        } 
	while (choice != 0);
    }

    	static void showMenu(boolean loggedIn) 
	{
        System.out.println("\n--- User Account Manager ---");
        if (!loggedIn) 
	{
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("8. Exit");
        } 
	else 
	{
            System.out.println("3. Logout");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Show Balance");
            System.out.println("7. View Account Details");
            System.out.println("8. Exit");
        }
        System.out.print("Enter your choice: ");
    }

    	static void registerUser() 
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
            	if (userDetails[i][0].equals(username)) 
		{
                System.out.println("Username already exists.");
                return;
            }
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        userDetails[userCount][0] = username;
        userDetails[userCount][1] = password;
        balances[userCount] = 0.0;
        userCount++;

        System.out.println("Registration successful!");
    }


    	static int loginUser() 
	{
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        for (int i = 0; i < userCount; i++) 
	{
            	if (userDetails[i][0].equals(username) && userDetails[i][1].equals(password)) 
		{
                System.out.println("Login successful!");
                return i;
            }
        }

        System.out.println("Invalid credentials.");
        return -1;
    }


    	static void depositMoney(int index) 
	{
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(sc.nextLine());

        if (amount <= 0) 
	{
            System.out.println("Amount must be greater than zero.");
            return;
        }

        balances[index] += amount;
        System.out.println("Deposit successful. \nNew balance: $" + balances[index]);
    }

    	static void withdrawMoney(int index) //Identifies the currently logged-in use
	{
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(sc.nextLine());

        if (amount <= 0) 
	{
            System.out.println("Amount must be greater than zero.");
        } else if (amount > balances[index]) {
            System.out.println("Insufficient balance.");
        } 
	else 
	{
            balances[index] -= amount;
            System.out.println("Withdrawal successful. \nRemaining balance: $" + balances[index]);
        }
   	}

    	static void showBalance(int index) 
	{
        System.out.println("Current balance: $" + balances[index]);
    	}

    	static void viewAccountDetails(int index) 
	{
        System.out.println("\n--- Account Details ---");
        System.out.println("Username: " + userDetails[index][0]);
        System.out.println("Balance: $" + balances[index]);
    }
}
