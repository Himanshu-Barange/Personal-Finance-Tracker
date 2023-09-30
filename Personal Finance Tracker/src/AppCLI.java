import java.util.Scanner;

public class AppCLI {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println("Choose an operation:");
            System.out.println("1. Create Profile");
            System.out.println("2. Display All Profiles");
            System.out.println("3. Add Expense");
            System.out.println("4. Display Total Expenses");
            System.out.println("5. Add Income");
            System.out.println("6. Display Total Income");
            System.out.println("7. Create Savings Goal");
            System.out.println("8. Display Monthly Expenses");
            System.out.println("9. Update Budget Limit");
            System.out.println("10. Edit Income");

            // ... (other operations can be added similarly)
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createProfileCLI();
                    break;
                case 2:
                    displayAllProfilesCLI();
                    break;
                case 3:
                    addExpenseCLI();
                    break;
                case 4:
                    displayTotalExpensesCLI();
                    break;
                case 5:
                    addIncomeCLI();
                    break;
                case 6:
                    displayTotalIncomeCLI();
                    break;
                case 7:
                    createSavingsGoalCLI();
                    break;
                // ... (handle other choices)
                case 8:
                    displayMonthlyExpensesCLI();
                    break;
                case 9:
                    updateBudgetCLI();
                    break;
                case 10:
                    editIncomeCLI();
                    break;

                case 0:
                    continueRunning = false;
                    break;
            }
        }

        scanner.close();
    }

    private static void createProfileCLI() {
        System.out.print("Enter Profile ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Profile profile = new Profile(id, name, email);
        profile.createProfile();
        System.out.println("Profile created successfully!");
    }

    private static void editIncomeCLI() {
        System.out.print("Enter Income ID to edit: ");
        int incomeID = scanner.nextInt();
        scanner.nextLine();

        Income incomeToEdit = InMemoryDB.incomes.get(incomeID);
        if (incomeToEdit == null) {
            System.out.println("Income not found!");
            return;
        }

        System.out.println("Editing Income with ID: " + incomeID);

        System.out.print("Enter New Amount (current: " + incomeToEdit.getAmount() + "): ");
        double newAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter New Date (current: " + incomeToEdit.getDate() + "): ");
        String newDate = scanner.nextLine();

        System.out.print("Enter New Source (current: " + incomeToEdit.getSource() + "): ");
        String newSource = scanner.nextLine();

        incomeToEdit.editIncome(newAmount, newDate, newSource);

        System.out.println("Income updated successfully!");
    }


    private static void displayAllProfilesCLI() {
        System.out.println("--- Profiles ---");
        InMemoryDB.profiles.values().forEach(profile -> {
            System.out.println("ID: " + profile.getProfileID() + ", Name: " + profile.getName() + ", Email: " + profile.getEmail());
        });

    }

    private static void addIncomeCLI() {
        System.out.print("Enter Income ID: ");
        int incomeID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Profile ID: ");
        int profileID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Date (e.g., 2023-09-20): ");
        String date = scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Source (e.g., Salary, Gift, etc.): ");
        String source = scanner.nextLine();  // Assuming Income has a 'source' attribute

        Income income = new Income(incomeID, profileID, amount, date, source, description);  // Assuming this is the Income constructor
        income.addIncomeToDB();  // Assuming Income class has an addIncome() method

        System.out.println("Income added successfully!");
    }

    private static void createSavingsGoalCLI() {
        System.out.print("Enter Goal ID: ");
        int goalID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Profile ID: ");
        int profileID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Goal Amount: ");
        double goalAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Current Amount: ");
        double currentAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Goal Date (e.g., 2025-12-31): ");
        String goalDate = scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        SavingsGoal goal = new SavingsGoal(goalID, profileID, goalAmount, currentAmount, goalDate, description);
        goal.createGoal();
        System.out.println("Savings Goal created successfully!");
    }

    private static void displayTotalExpensesCLI() {
        double totalExpenses = InMemoryDB.expenses.values().stream().mapToDouble(Expense::getAmount).sum();
        System.out.println("Total Expenses: $" + totalExpenses);
    }

    private static void displayTotalIncomeCLI() {
        double totalIncome = InMemoryDB.incomes.values().stream().mapToDouble(Income::getAmount).sum();
        System.out.println("Total Income: $" + totalIncome);
    }

    private static void addExpenseCLI() {
        System.out.print("Enter Expense ID: ");
        int expenseID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Profile ID: ");
        int profileID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Date (e.g., 2023-09-20): ");
        String date = scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Category ID: ");
        int categoryID = scanner.nextInt();
        scanner.nextLine();

        Budget associatedBudget = InMemoryDB.budgets.get(categoryID);
        if (associatedBudget != null && associatedBudget.isExceedingBudget(amount)) {
            System.out.println("Warning! This expense exceeds your budget for this category!");
        }

        Expense expense = new Expense(expenseID, profileID, amount, date, description, categoryID);
        expense.addExpense();
        System.out.println("Expense added successfully!");
    }

    private static void displayMonthlyExpensesCLI() {
        System.out.print("Enter Month and Year (e.g., 2023-09): ");
        String monthYear = scanner.nextLine();

        double totalMonthlyExpenses = InMemoryDB.expenses.values().stream()
                .filter(expense -> expense.getDate().startsWith(monthYear))
                .mapToDouble(Expense::getAmount)
                .sum();
        System.out.println("Total Expenses for " + monthYear + ": $" + totalMonthlyExpenses);
    }

    private static void updateBudgetCLI() {
        System.out.print("Enter Budget ID (corresponds with Category ID): ");
        int budgetID = scanner.nextInt();
        scanner.nextLine();

        Budget budgetToUpdate = InMemoryDB.budgets.get(budgetID);
        if (budgetToUpdate == null) {
            System.out.println("Budget not found!");
            return;
        }

        System.out.print("Enter New Monthly Limit: ");
        double newLimit = scanner.nextDouble();
        scanner.nextLine();

        budgetToUpdate.updateBudget(newLimit);
        System.out.println("Budget updated successfully!");
    }
}