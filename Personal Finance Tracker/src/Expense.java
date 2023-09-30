public class Expense {
    private final int expenseID;
    private final int profileID;
    private double amount;
    private String date; // Using String for simplicity. In a real system, java.util.Date or java.time.LocalDate would be better.
    private String description;
    private final int categoryID;

    public Expense(int expenseID, int profileID, double amount, String date, String description, int categoryID) {
        this.expenseID = expenseID;
        this.profileID = profileID;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryID = categoryID;
    }

    public int getCategoryID(){ return categoryID;}
    public int getExpenseID(){
        return this.expenseID;
    }
    public void addExpense() {
        InMemoryDB.expenses.put(this.expenseID, this);
        Profile associatedProfile = InMemoryDB.profiles.get(this.profileID);
        if(associatedProfile == null){
            System.out.println("Error: Profile with ID " + this.profileID + " not found. Expense not added to profile.");
            return;
        }
    }

    public void editExpense(double newAmount, String newDate, String newDescription) {
        if (!InMemoryDB.expenses.containsKey(this.expenseID)) {
            System.out.println("Expense with ID " + this.expenseID + " not found. Cannot edit.");
            return;
        }

        this.amount = newAmount;
        this.date = newDate;
        this.description = newDescription;

        // Reflect these changes in the in-memory DB
        InMemoryDB.expenses.put(this.expenseID, this);
        System.out.println("Expense updated successfully!");
    }

    public void removeExpense() {
        if (!InMemoryDB.expenses.containsKey(this.expenseID)) {
            System.out.println("Expense with ID " + this.expenseID + " not found. Nothing removed.");
            return;
        }

        // Remove from associated profile's expenses list
        Profile associatedProfile = InMemoryDB.profiles.get(this.profileID);
        associatedProfile.removeExpenseFromProfile(this);

        // Remove from the centralized DB
        InMemoryDB.expenses.remove(this.expenseID);
        System.out.println("Expense removed successfully!");
    }



    public double getAmount(){
        return this.amount;
    }

    public String getDate() {
        return date;
    }
}