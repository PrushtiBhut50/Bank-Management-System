import java.util.Scanner;
class week_1
{
    static final int MAX_USERS = 100;
    static String[] usernames = new String[MAX_USERS];
    static String[] passwords = new String[MAX_USERS];
    static double[] balances = new double[MAX_USERS];
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

            sc.nextLine(); 

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

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        usernames[userCount] = username;
        passwords[userCount] = password;
        userCount++;

        System.out.println("Registered successful.");
    	}

         static void loginUser(Scanner sc) 
    {
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        for (int i=0;i<userCount;i++) 
    {
           if(usernames[i].equals(username) && passwords[i].equals(password)) 
       {
           loggedInUserIndex = i;
            System.out.println("Login successfully. Welcome, " + username + "!");
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
            System.out.println("User " + usernames[loggedInUserIndex] + " loggedout.");
            loggedInUserIndex = -1;
        }
    }
}
