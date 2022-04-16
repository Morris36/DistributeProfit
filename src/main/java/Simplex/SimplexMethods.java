package Simplex;

import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.Relationship;
import org.apache.commons.math.optimization.linear.SimplexSolver;

import java.util.ArrayList;
import java.util.Collection;

public class SimplexMethods {
    private final Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
    private final LinearObjectiveFunction function;
    private final ArrayList<Double>  premium = new ArrayList<>();
    private final ArrayList<Double> activeStatus = new ArrayList<>();
    public SimplexMethods(double[] elem, double constantTerm) {
        this.function = new LinearObjectiveFunction(elem, constantTerm);
    }

    public void addPremium(double premium){
        this.premium.add(premium);
    }
    public void addActive(double active){
        this.activeStatus.add(active);
    }

    public ArrayList<Double> getPremium() {
        return premium;
    }

    public ArrayList<Double> getActiveStatus() {
        return activeStatus;
    }

    public double[] makeConstraintsForTwo(double[] values) throws OptimizationException {
        constraints.add(new LinearConstraint(new double[]{-1, 0, 1, 0}, Relationship.GEQ, values[0]));
        constraints.add(new LinearConstraint(new double[]{0, -1, 0, 1}, Relationship.GEQ, values[1]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 1, 1}, Relationship.EQ, values[2]));
        constraints.add(new LinearConstraint(new double[]{1, 0, 0, 0}, Relationship.GEQ, values[3]));
        constraints.add(new LinearConstraint(new double[]{0, 1, 0, 0}, Relationship.GEQ, values[4]));
        constraints.add(new LinearConstraint(new double[]{1, 0, 0, 0}, Relationship.LEQ, values[5]));
        constraints.add(new LinearConstraint(new double[]{0, 1, 0, 0}, Relationship.LEQ, values[6]));
        return makeResult(2);
    }

    /*
    @param - double array size = 13
    values >= 0
    AgentsCount*2 - size array

    1,3
    [0,0,0,1,0,1]
     */

    public double[] makeConstraintsForThree(double[] values) throws OptimizationException {
        constraints.add(new LinearConstraint(new double[]{-1, 0, 0, 1, 0, 0}, Relationship.GEQ, values[0]));
        constraints.add(new LinearConstraint(new double[]{0, -1, 0, 0, 1, 0}, Relationship.GEQ, values[1]));
        constraints.add(new LinearConstraint(new double[]{0, 0, -1, 0, 0, 1}, Relationship.GEQ, values[2]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 1, 1, 0}, Relationship.GEQ, values[3]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 0, 1, 1}, Relationship.GEQ, values[4]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 1, 0, 1}, Relationship.GEQ, values[5]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 1, 1, 1}, Relationship.EQ, values[6]));
        constraints.add(new LinearConstraint(new double[]{1, 0, 0, 0, 0, 0}, Relationship.GEQ, values[7]));
        constraints.add(new LinearConstraint(new double[]{0, 1, 0, 0, 0, 0}, Relationship.GEQ, values[8]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 1, 0, 0, 0}, Relationship.GEQ, values[9]));
        constraints.add(new LinearConstraint(new double[]{1, 0, 0, 0, 0, 0}, Relationship.LEQ, values[10]));
        constraints.add(new LinearConstraint(new double[]{0, 1, 0, 0, 0, 0}, Relationship.LEQ, values[11]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 1, 0, 0, 0}, Relationship.LEQ, values[12]));
        return makeResult(3);
    }
    //Для четырёх агентов в разработке

    private double[] makeResult(int countAgent) throws OptimizationException {
        RealPointValuePair solution = new SimplexSolver().optimize(function, constraints, GoalType.MAXIMIZE, false);
        double[] result = new double[countAgent];
        int count = 0;
        for (int i = countAgent; i < countAgent * 2; i++) {
            result[count] = solution.getPoint()[i];
            count++;
        }
        return result;
    }

    //AgentName + " " + profit || AgentName + profit
    //Agent 15678
}
