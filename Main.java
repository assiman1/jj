import java.util.Scanner;
//adding variables
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String welcomeMessage = "Welcome to the Admin Panel of \"Magnum\" Supermarket!";
    static String[] categories = {};
    static String[][] subcategories = {};
    static double[][] prices = {};
    static String[][] basket = new String[100][2]; // To store items and their prices in a simple way
    static int countItems = 0;
    //choose mode
    public static void main(String[] args) {
        while (true) {
            System.out.println("Are you an Admin or a Client?");
            System.out.print("Enter 'Admin', 'Client', or 'Exit' to close the program:");
            String mode = scanner.nextLine();
            if (mode.equals("Exit")) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            } else if (mode.equals("Admin")) {
                AdminMode();
            } else if (mode.equals("Client")) {
                ClientMode();
            } else {
                System.out.println("Invalid role. Please enter 'Admin', 'Client', or 'Exit'.");
            }
        }
    }
    //admin mode
    public static void AdminMode() {
        System.out.print("Enter Admin password: ");
        String password = scanner.nextLine();
        //password
        if (!password.equals("adminadmin")) {
            System.out.println("Incorrect password. Access denied.");
            return;
        }
        System.out.println(welcomeMessage);
        //choices in admin mode
        while (true) {
            System.out.println("Here you can do the following activities:");
            System.out.println("0. Exit Admin Mode and return to Role Selection.");
            System.out.println("1. Change Welcome Message.");
            System.out.println("2. Change Menu.");
            System.out.println("3. Change SubMenu.");
            System.out.println("4. Change Price.");
            System.out.println("5. Display Welcome Message.");
            System.out.println("6. Display Menu.");
            System.out.println("7. Display SubMenu.");
            System.out.println("8. Display Prices.");
            System.out.print("Choose an activity: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                // return back
                case 0:
                    System.out.println("Exiting Admin Mode...");
                    return;
                // change welcome message
                case 1:
                    System.out.print("Enter new Welcome Message: ");
                    welcomeMessage = scanner.nextLine();
                    System.out.println("Welcome message updated!");
                    break;
                // goes to method changeMenu()
                case 2:
                    changeMenu();
                    break;
                // goes to method changeSubMenu()
                case 3:
                    changeSubMenu();
                    break;
                // goes to method changePrice()
                case 4:
                    changePrice();
                    break;
                // shows current welcome message
                case 5:
                    System.out.println("Current Welcome Message: " + welcomeMessage);
                    break;
                // displays categories
                case 6:
                    displayMenu();
                    break;
                // displays subcategories
                case 7:
                    displaySubMenu();
                    break;
                // displays prices
                case 8:
                    displayPrices();
                    break;
                // for wrong choices
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    // adding categories
    public static void changeMenu() {
        System.out.println("Write the names of categories separated by comma (c1, c2, ...):");
        System.out.print(">");
        String input = scanner.nextLine();
        // adding categories by comma
        categories = input.split(", ");//\s*
        subcategories = new String[categories.length][];
        prices = new double[categories.length][];
        System.out.println("Categories added successfully!");
    }
    // adding subcategories
    public static void changeSubMenu() {
        // if there are no categories added
        if (categories.length == 0) {
            System.out.println("No categories available. Please add categories first.");
            return;
        }
        //displaying added categories for adding subcategories
        displayMenu();
        System.out.print("Choose a category (1-" + categories.length + "): ");
        int indexC = Integer.parseInt(scanner.nextLine()) - 1;
        if (indexC < 0 || indexC >= categories.length) {
            System.out.println("Invalid category choice.");
            return;
        }
        // adding subcategories
        System.out.println("Write the names of subcategories for " + categories[indexC] + " separated by comma: ");
        System.out.print(">");
        String input = scanner.nextLine();
        subcategories[indexC] = input.split(", ");//\s*
        prices[indexC] = new double[subcategories[indexC].length];
        System.out.println("Subcategories added successfully!");
    }
    // adding prices to items
    public static void changePrice() {
        if (categories.length == 0) {
            System.out.println("No categories available. Please add categories first.");
            return;
        }
        // display added categories and choose category
        displayMenu();
        System.out.print("Choose a category (1-" + categories.length + "): ");
        int indexC = Integer.parseInt(scanner.nextLine()) - 1;
        if (indexC < 0 || indexC >= categories.length || subcategories[indexC] == null) {
            System.out.println("Invalid category choice.");
            return;
        }
        // in chosen category add prices to subcategory
        for (int i = 0; i < subcategories[indexC].length; i++) {
            System.out.print("Enter price for " + subcategories[indexC][i] + ": ");
            prices[indexC][i] = Double.parseDouble(scanner.nextLine());
        }
        System.out.println("Prices updated successfully!");
    }
    // method to display categories
    public static void displayMenu() {
        System.out.println("Categories:");
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }
    }
    // method to display subcategories
    public static void displaySubMenu() {
        for (int i = 0; i < categories.length; i++) {
            System.out.println("Subcategories for " + categories[i] + ":");
            if (subcategories[i] != null) {
                for (String subcategory : subcategories[i]) {
                    System.out.println("- " + subcategory);
                }
            } else {
                System.out.println("No subcategories added.");
            }
        }
    }
    // method to display prices
    public static void displayPrices() {
        for (int i = 0; i < categories.length; i++) {
            System.out.println("Prices for " + categories[i] + ":");
            if (subcategories[i] != null) {
                for (int j = 0; j < subcategories[i].length; j++) {
                    System.out.println(subcategories[i][j] + ": " + prices[i][j] + " kzt");
                }
            } else {
                System.out.println("No subcategories or prices set.");
            }
        }
    }
    // client mode
    public static void ClientMode() {
        System.out.println("Welcome to Salam Bro Fast Food Station!");
        // choices in client mode
        while (true) {
            System.out.println("Client Mode Options:");
            System.out.println("1. Show Menu Categories");
            System.out.println("2. View Basket");
            System.out.println("3. Add Bank Card");
            System.out.println("4. Checkout");
            System.out.println("5. Exit Client Mode");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                // goes to method menuAndAddToBasket()
                case 1:
                    menuAndAddToBasket();
                    break;
                // goes to method viewBasket()
                case 2:
                    viewBasket();
                    break;
                // goes to method addBankCard()
                case 3:
                    addBankCard();
                    break;
                // goes to method checkout()
                case 4:
                    checkout();
                    break;
                // return back
                case 5:
                    System.out.println("Exiting Client Mode...");
                    return;
                // for wrong choices
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    // shows menu and adds to basket
    public static void menuAndAddToBasket() {
        displayMenu();
        System.out.print("Choose a category (1-" + categories.length + ") or 0 to go back: ");
        int indexC = Integer.parseInt(scanner.nextLine()) - 1;
        if (indexC == -1) return;
        if (indexC < 0 || indexC >= categories.length || subcategories[indexC] == null) {
            System.out.println("Invalid category choice.");
            return;
        }
        System.out.println("Subcategories for " + categories[indexC] + ":");
        for (int i = 0; i < subcategories[indexC].length; i++) {
            System.out.println((i + 1) + ". " + subcategories[indexC][i] + " - " + prices[indexC][i] + " kzt");
        }
        System.out.print("Select an item to add to your basket (or 0 to go back): ");
        int indexS = Integer.parseInt(scanner.nextLine()) - 1;
        if (indexS == -1) return;
        if (indexS < 0 || indexS >= subcategories[indexC].length) {
            System.out.println("Invalid subcategory choice.");
            return;
        }
        basket[countItems][0] = subcategories[indexC][indexS];
        basket[countItems][1] = String.valueOf(prices[indexC][indexS]);
        countItems++;
        System.out.println("Item added to basket.");
    }
    // view basket
    public static void viewBasket() {
        if (countItems == 0) {
            System.out.println("Your basket is empty.");
            return;
        }
        System.out.println("Items in your basket:");
        for (int i = 0; i < countItems; i++) {
            System.out.println((i + 1) + ". " + basket[i][0] + " - " + basket[i][1] + " kzt");
        }
    }
    // adding bank card
    public static void addBankCard() {
        System.out.print("Enter your bank card details (card number): ");
        String cardNumber = scanner.nextLine();
        System.out.print("Enter card expiration date (MM/YY): ");
        String date = scanner.nextLine();
        System.out.print("Enter CVV (3 digits): ");
        String cvv = scanner.nextLine();
        System.out.println("Bank card details added successfully.");
    }
    // checking basket and payment
    public static void checkout() {
        if (countItems == 0) {
            System.out.println("Your basket is empty. Add items before checkout.");
            return;
        }
        double total = 0;
        for (int i = 0; i < countItems; i++) {
            total += Double.parseDouble(basket[i][1]);
        }
        System.out.println("Your total is: " + total + " kzt.");
        System.out.print("Do you want to proceed with payment? (Yes/No): ");
        String payment = scanner.nextLine().trim();
        if (payment.equals("Yes")) {
            System.out.println("Payment successful. Thank you for your purchase!");
            countItems = 0;
        } else {
            System.out.println("Checkout cancelled.");
        }
    }
}