public class SavingsGoal {
    private final int goalID;
    private final int profileID;
    private double goalAmount;
    private double currentAmount;
    private String goalDate; // Using String for simplicity. Consider java.util.Date or java.time.LocalDate for a real system.
    private String description;

    public SavingsGoal(int goalID, int profileID, double goalAmount, double currentAmount, String goalDate, String description) {
        this.goalID = goalID;
        this.profileID = profileID;
        this.goalAmount = goalAmount;
        this.currentAmount = currentAmount;
        this.goalDate = goalDate;
        this.description = description;
    }

    public void createGoal() {
        InMemoryDB.savingsGoals.put(this.goalID, this);
        Profile associatedProfile = InMemoryDB.profiles.get(this.profileID);
        associatedProfile.addSavingsGoal(this);
    }

    public void updateGoal(double newGoalAmount, String newGoalDate, String newDescription) {
        this.goalAmount = newGoalAmount;
        this.goalDate = newGoalDate;
        this.description = newDescription;
        InMemoryDB.savingsGoals.put(this.goalID, this); // Reflect changes in the in-memory database.
        System.out.println("Savings goal updated successfully!");
    }

    public void deleteGoal() {
        InMemoryDB.savingsGoals.remove(this.goalID);
        System.out.println("Savings goal deleted successfully!");
    }

    public void contributeToGoal(double contribution) {
        currentAmount += contribution; // Basic logic to contribute to a goal
        System.out.println("Contributed to savings goal. Current amount: " + currentAmount);
    }

    // Getter and Setter methods (optional but can be useful for further functionalities)
    public double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(double goalAmount) {
        this.goalAmount = goalAmount;
    }

    public String getGoalDate() {
        return goalDate;
    }

    public void setGoalDate(String goalDate) {
        this.goalDate = goalDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}