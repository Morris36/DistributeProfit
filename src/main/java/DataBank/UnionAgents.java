package DataBank;

import Backpack.Agent;
import Backpack.Project;

import java.util.ArrayList;
import java.util.Arrays;

public class UnionAgents {
    private final ArrayList<Agent> agents;
    private final ArrayList<Project> projects;
    private final double profit;

    public UnionAgents(double profit, ArrayList<Agent> agents, ArrayList<Project> projects) {
        this.profit = profit;
        this.agents = agents;
        this.projects = projects;
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public double getProfit() {
        return profit;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }
}
