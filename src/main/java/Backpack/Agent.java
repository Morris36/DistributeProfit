package Backpack;

import java.util.ArrayList;

public class Agent {
    private final ArrayList<Project> projects = new ArrayList<Project>();
    private final String name;
    private final double budget;

    public Agent(String name, double budget) {
        this.name = name;
        this.budget = budget;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public void addProject(Project project) {
        boolean flag = false;
            for (Project value : projects) {
                flag = value.equals(project);
            }
        if (!flag)
            projects.add(project);
    }
}
