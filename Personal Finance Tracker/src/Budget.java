public class Budget {
    private final int budgetID;
    private final int profileID;
    private final int categoryID;
    private double monthlyLimit;

    public Budget(int budgetID, int profileID, int categoryID, double monthlyLimit) {
        this.budgetID = budgetID;
        this.profileID = profileID;
        this.categoryID = categoryID;
        this.monthlyLimit = monthlyLimit;
    }

    public void setBudget() {
        InMemoryDB.budgets.put(this.budgetID, this);
        Profile associatedProfile = InMemoryDB.profiles.get(this.profileID);
        associatedProfile.addBudget(this);  // Assuming you have an addBudget method in the Profile class
    }

    public void updateBudget(double newLimit) {
        this.monthlyLimit = newLimit;
        // Reflect these changes in the in-memory DB
        InMemoryDB.budgets.put(this.budgetID, this);
        System.out.println("Budget updated successfully!");
    }

    public void checkBudget() {
        double totalExpensesForThisMonth = getTotalExpensesForCategory(this.categoryID);

        if (totalExpensesForThisMonth > this.monthlyLimit) {
            System.out.println("You have exceeded your budget for this month!");
        } else {
            System.out.println("You are within your budget. Remaining: " + (this.monthlyLimit - totalExpensesForThisMonth));
        }
    }

    private double getTotalExpensesForCategory(int categoryID) {

        return InMemoryDB.expenses.values().stream()
                .filter(expense -> expense.getCategoryID() == categoryID)
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public boolean isExceedingBudget(double expenseAmount) {
        return expenseAmount > this.monthlyLimit;
    }

}