package Backpack;

public class Project {
    private final String name;
    private final double expenses;
    private final double profit;

    public Project(String name, double expenses, double profit) {
        this.name = name;
        this.expenses = expenses;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public double getExpenses() {
        return expenses;
    }

    public double getProfit() {
        return profit;
    }
}

