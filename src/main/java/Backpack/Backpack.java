package Backpack;

import DataBank.Bank;
import DataBank.UnionAgents;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Evgeny Dubov
 */
//Agents,profit
public class Backpack {
    private double tmpSum = 0;
    private double tmpExpenses;
    private double tmpProfit;
    private double budget;
    private ArrayList<Agent> agents;
    private ArrayList<Project> projects;

    public Backpack(double budget, ArrayList<Project> projects, ArrayList<Agent> agents) {
        this.budget = budget;
        this.projects = projects;
        this.agents = agents;
    }

    private ArrayList<String> backpack(ArrayList<Project> projects, double budget) {

        double[] tmpEE = new double[projects.size()];
        double[] tmpPP = new double[projects.size()];
        String[] names = new String[projects.size()];
        for (int i = 0; i < projects.size(); i++) {
            tmpEE[i] = projects.get(i).getExpenses();
            tmpPP[i] = projects.get(i).getProfit();
            names[i] = projects.get(i).getName();
        }
        final int size = (int) (Math.round(budget) + 1);
        double[] Sum = new double[size]; // Массив cуммарной прибыли
        double[] Sum_old = new double[size]; // Массив cуммарной прибыли на предыдущем шаге
        ArrayList<String> arL = new ArrayList<>(size);
        ArrayList<String> arL_old = new ArrayList<>(size);
        for (int v = 0; v < (int) Math.round(budget) + 1; v++) {
            Sum[v] = 0;
            arL.add("");
            arL_old.add("");
        } // начальное   обнуление
        for (int i = 0; i < projects.size(); i++) //поочередно все предметы
        {
            for (int w = 0; w < (int) Math.round(budget) + 1; w++) {
                Sum_old[w] = Sum[w]; // копирование массивов
                arL_old.set(w, arL.get(w));
            }
            tmpExpenses = tmpEE[i];
            tmpProfit = tmpPP[i];

            for (double v = 1; v < budget + 1; v++) //постепенно до величины затрат
            {
                if (tmpExpenses <= v) {
                    tmpSum = Sum_old[(int) ((int) v - Math.round(tmpExpenses))] + tmpProfit; //сум.прибыль если взять i-ый проект
                    if (Sum_old[(int) v] < tmpSum) //Sum_old[v] сум.прибыль если не брать i-ый проект
                    {
                        Sum[(int) v] = tmpSum; //данный проект взят в набор
                        arL.set((int) v, arL_old.get((int) ((int) v - Math.round(tmpExpenses))) + names[i] + " ");//внесение в список
                    } else {
                        Sum[(int) v] = Sum_old[(int) v];
                        arL.set((int) v, arL_old.get((int) v)); //список остается прежним
                    }
                } else {
                    Sum[(int) v] = Sum_old[(int) v];
                    arL.set((int) v, arL_old.get((int) v)); //список остается прежним
                }
            }
        }

        arL.add(String.valueOf(Sum[size - 1]));

        return arL;
    }

    private UnionAgents makeUnion(@NotNull ArrayList<String> arL) {
        String[] tmpProjects = arL.get(arL.size() - 2).split(" ");
        double profit = Double.parseDouble(arL.get(arL.size() - 1));
        ArrayList<Agent> agents = new ArrayList<>();
        ArrayList<Project> projects = new ArrayList<Project>();
        for (int i = 0; i < tmpProjects.length; i++) {
            for (int j = 0; j < this.agents.size(); j++) {
                for (int k = 0; k < this.agents.get(j).getProjects().size(); k++) {
                    if (Objects.equals(this.agents.get(j).getProjects().get(k).getName(), tmpProjects[i])) {
                        if (checkAgent(agents, this.agents.get(j))) {
                            agents.add(this.agents.get(j));
                        }
                        projects.add(this.agents.get(j).getProjects().get(k));
                        break;
                    }
                }
            }
        }
        return new UnionAgents(profit, agents, projects);
    }

    private boolean checkAgent(ArrayList<Agent> agents, Agent agent) {
        boolean flag = false;
        for (int i = 0; i < agents.size(); i++) {
            if (agents.get(i) == agent) {
                flag = true;
                break;
            }
        }
        return !flag;
    }

    private int[] generateCombinations(int[] arr, int M) {
        int N = this.agents.size();

        if (arr == null) {
            arr = new int[M];
            for (int i = 0; i < M; i++)
                arr[i] = i + 1;
            return arr;
        }
        for (int i = M - 1; i >= 0; i--)
            if (arr[i] < N - M + i + 1) {
                arr[i]++;
                for (int j = i; j < M - 1; j++)
                    arr[j + 1] = arr[j] + 1;
                return arr;
            }
        return null;
    }

    public Bank getResultBackpack() {
        Bank bank = new Bank();
        ArrayList<int[]> combinations = new ArrayList<>();
        int[] arr = null;
        for (int i = 1; i < agents.size() + 1; i++) {
            while ((arr = generateCombinations(arr, i)) != null)
                combinations.add(makeArray(arr));
        }
        ArrayList<Project> projects1 = new ArrayList<>();
        double budget;
        for (int[] combination : combinations) {
            projects1 = makeProjects(combination);
            budget = makeBudget(makeAgents(combination));
            bank.addUnion(makeUnion(backpack(projects1, budget)));
        }
        return bank;
    }

    private double makeBudget(ArrayList<Agent> agents1) {
        double res = 0;
        for (Agent agent : agents1) {
            res += agent.getBudget();
        }
        return res;
    }

    private ArrayList<Project> makeProjects(int[] index) {
        ArrayList<Agent> agents1 = makeAgents(index);
        ArrayList<Project> projects1 = new ArrayList<>();
        for (Agent agent : agents1) {
            projects1.addAll(agent.getProjects());
        }
        return projects1;
    }

    private ArrayList<Agent> makeAgents(int[] index) {
        ArrayList<Agent> agents1 = new ArrayList<>();
        for (int j : index) {
            agents1.add(this.agents.get(j - 1));
        }
        return agents1;
    }

    private int[] makeArray(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }
}

