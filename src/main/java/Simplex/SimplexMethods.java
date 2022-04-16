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
    private Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
    private LinearObjectiveFunction function;

    public SimplexMethods(double[] elem, double constantTerm) {
        this.function = new LinearObjectiveFunction(elem, constantTerm);
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
}
