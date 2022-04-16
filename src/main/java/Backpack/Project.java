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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (Double.compare(project.getExpenses(), getExpenses()) != 0) return false;
        if (Double.compare(project.getProfit(), getProfit()) != 0) return false;
        return getName() != null ? getName().equals(project.getName()) : project.getName() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        temp = Double.doubleToLongBits(getExpenses());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getProfit());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

