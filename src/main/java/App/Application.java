package App;

import Backpack.*;
import DataBank.Bank;
import Simplex.SimplexMethods;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.math.optimization.OptimizationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("hello-view.fxml")));
        stage.setTitle("Distribute profit");
        stage.setScene(new Scene(fxmlLoader));
        stage.show();
    }

    public static void main(String[] args) throws OptimizationException {
        launch();
        ArrayList<Agent> agents = new ArrayList<>();
        agents.add(new Agent("Agent1", 600));
        agents.get(0).addProject(new Project("11", 200, 70));
        agents.get(0).addProject(new Project("12", 250, 64));
        agents.get(0).addProject(new Project("13", 120, 18));
        agents.get(0).addProject(new Project("14", 300, 120));
        agents.get(0).addProject(new Project("15", 150, 18));

        agents.add(new Agent("Agent2", 300));
        agents.get(1).addProject(new Project("21", 180, 57.6));
        agents.get(1).addProject(new Project("22", 250, 75));
        agents.get(1).addProject(new Project("23", 100, 25));
        agents.get(1).addProject(new Project("24", 200, 36));

        agents.add(new Agent("Agent3", 1000));
        agents.get(2).addProject(new Project("31", 400, 72));
        agents.get(2).addProject(new Project("32", 300, 36));
        agents.get(2).addProject(new Project("33", 200, 10));
        agents.get(2).addProject(new Project("34", 300, 80));
        agents.get(2).addProject(new Project("35", 500, 100));
        double budg = 0;
        ArrayList<Project> projects = new ArrayList<>();
        for (Agent agent : agents) {
            budg += agent.getBudget();
            projects.addAll(agent.getProjects());
        }
        Backpack backpack = new Backpack(budg, projects, agents);
        Bank bank = backpack.getResultBackpack();
        for (int i = 0; i < bank.getUnions().size(); i++) {
            for (int j = 0; j < bank.getUnions().get(i).getProjects().size(); j++) {
                System.out.println(bank.getUnions().get(i).getProjects().get(j).getName());
            }
            System.out.println("EEE");
        }
        SimplexMethods simplexMethods = new SimplexMethods(bank, agents.size(), agents);
        simplexMethods.addActive(0.4);
        simplexMethods.addPremium(40);
        simplexMethods.addPremium(50);
        simplexMethods.addActive(0.5);
        simplexMethods.addPremium(5);
        simplexMethods.addPremium(40);
        simplexMethods.addActive(0.1);
        simplexMethods.addPremium(2);
        simplexMethods.addPremium(11);
        double[] result = simplexMethods.exMethod();
        for (double v : result) {
            System.out.println(v);
        }
    }
}
