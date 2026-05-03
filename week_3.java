import java.util.Scanner;

class week_3 
{
    static int MAX_USERS = 100;
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

            int choice = -1;
            try 
	    {
                choice = Integer.parseInt(sc.nextLine().trim());
        } 
		catch (NumberFormatException e) 
	    {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

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

        System.out.print("Enter email (will be used as username): ");
        String email = sc.nextLine().trim().toLowerCase();

        if (!isValidEmail(email)) 
	{
            System.out.println("Invalid email. Must contain '@' and a valid format.");
            return;
        }

        for (int i = 0; i < userCount; i++) 
	{
            if (usernames[i].equals(email)) 
	    {
                System.out.println("Email already registered.");
                return;
            }
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine().trim();

        if (!isValidPassword(password)) 
	{
            System.out.println("Password must be at least 8 characters long and contain:");
            System.out.println(" - One uppercase letter");
            System.out.println(" - One lowercase letter");
            System.out.println(" - One digit");
            System.out.println(" - One special character");
            return;
        }

        usernames[userCount] = email;
        passwords[userCount] = password;
        balances[userCount] = 0.0; // default balance
        userCount++;

        System.out.println("Registration successful.");
    }

    static void loginUser(Scanner sc) 
    {
        System.out.print("Enter email: ");
        String email = sc.nextLine().trim().toLowerCase();

        System.out.print("Enter password: ");
        String password = sc.nextLine().trim();

        for (int i = 0; i < userCount; i++) 
	{
            if (usernames[i].equals(email) && passwords[i].equals(password)) 
	    {
                loggedInUserIndex = i;
                System.out.println("Login successful. Welcome, " + email + "!");
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
            System.out.println("User " + usernames[loggedInUserIndex] + " logged out.");
            loggedInUserIndex = -1;
        }
    }


    static boolean isValidEmail(String email) 
    {
        return email.contains("@") && email.indexOf("@") < email.lastIndexOf(".");
    }

    static boolean isValidPassword(String password) 
    {
        if (password.length() < 8) return false;

        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
        for (char ch : password.toCharArray()) 
	{
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else if ("!@#$%^&*()_+-=[]{}|;':\",.<>?/`~".contains(String.valueOf(ch))) hasSpecial = true;
        }
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
}
