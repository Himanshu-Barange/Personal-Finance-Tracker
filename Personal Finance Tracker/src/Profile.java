import java.util.ArrayList;
import java.util.Optional;

public class Profile {
    private final int profileID;
    private String name;
    private String email;
    private ArrayList<Expense> expenses;
    private ArrayList<Income> incomes;
    private ArrayList<Budget> budgets;
    private ArrayList<SavingsGoal> savingsGoals;

    public Profile(int profileID, String name, String email) {
        this.profileID = profileID;
        this.name = name;
        this.email = email;
        this.expenses = new ArrayList<>();
        this.incomes = new ArrayList<>();
        this.budgets = new ArrayList<>();
        this.savingsGoals = new ArrayList<>();
    }

    public int getProfileID(){
        return profileID;
    }
    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public void createProfile() {
        InMemoryDB.profiles.put(this.profileID, this);
    }

    public void deleteProfile() {
        InMemoryDB.profiles.remove(this.profileID);
    }

    public void updateProfile(String newName, String newEmail) {
        if (!InMemoryDB.profiles.containsKey(this.profileID)) {
            System.out.println("Profile with ID " + this.profileID + " not found. Cannot update.");
            return;
        }

        this.name = newName;
        this.email = newEmail;
        InMemoryDB.profiles.put(this.profileID, this); // Reflect changes in the in-memory DB
        System.out.println("Profile updated successfully!");
    }



    public void addExpenseToProfile(Expense expense) {
        this.expenses.add(expense);
    }

    public void addIncome(Income income) {
        this.incomes.add(income);
    }

    public void addBudget(Budget budget) {
        this.budgets.add(budget);
    }


    void removeExpenseFromProfile(Expense expense) {
        this.expenses.remove(expense);
    }


    public void addSavingsGoal(SavingsGoal goal) {
        this.savingsGoals.add(goal);
    }

    public void commitExpensesToDB(){
        for(Expense expense: this.expenses){
            if(!InMemoryDB.expenses.containsKey(expense.getExpenseID())){
                expense.addExpense();
            }
        }
    }
    public Optional<Expense> getExpense(int expenseID) {
        return Optional.ofNullable(InMemoryDB.expenses.get(expenseID));
    }

    public static Optional<Profile> findProfile(int profileID) {
        return Optional.ofNullable(InMemoryDB.profiles.get(profileID));
    }

    public void addExpense(Expense expense) {
        if(expenses == null){
            expenses = new ArrayList<>();
        }
        expenses.add(expense);
    }
}