import java.util.HashMap;

public class InMemoryDB {
    public static final HashMap<Integer, Profile> profiles = new HashMap<>();
    public static final HashMap<Integer, Expense> expenses = new HashMap<>();
    public static final HashMap<Integer, Income> incomes = new HashMap<>();
    public static final HashMap<Integer, Budget> budgets = new HashMap<>();
    public static final HashMap<Integer, Category> categories = new HashMap<>();
    public static final HashMap<Integer, SavingsGoal> savingsGoals = new HashMap<>();
}