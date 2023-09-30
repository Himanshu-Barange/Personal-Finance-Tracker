import java.util.Optional;

public class Income {
    private final int incomeID;
    private final int profileID;
    private double amount;
    private String date;
    private String source;
    private String description;

    public Income(int incomeID, int profileID, double amount, String date, String source, String description) {
        this.incomeID = incomeID;
        this.profileID = profileID;
        this.amount = amount;
        this.date = date;
        this.source = source;
        this.description = description;
    }



    public double getAmount() {
        return this.amount;
    }


    public void addIncomeToDB() {
        if (InMemoryDB.incomes.containsKey(this.incomeID)) {
            System.out.println("Income with ID " + this.incomeID + " already exists. Not added.");
            return;
        }
        InMemoryDB.incomes.put(this.incomeID, this);
        System.out.println("Income added successfully!");
    }

    public void removeIncomeFromDB() {
        if (!InMemoryDB.incomes.containsKey(this.incomeID)) {
            System.out.println("Income with ID " + this.incomeID + " not found. Nothing removed.");
            return;
        }
        InMemoryDB.incomes.remove(this.incomeID);
        System.out.println("Income removed successfully!");
    }
    public void editIncome(double newAmount, String newDate, String newSource) {
        this.amount = newAmount;
        this.date = newDate;
        this.source = newSource;
    }


    public String getDate() {
        return date;
    }

    public String getDescription() {
        return this.description; }

    public String getSource() {
        return source;
    }


}