package App;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import Backpack.Agent;
import DataBase.Connector;
import DataBase.Edit;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller {
    ArrayList<Agent> agents = new ArrayList<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AgentAdd;

    @FXML
    private ChoiceBox<String> AgentChoice;

    @FXML
    private TextField AgentCountField;

    @FXML
    private TextField AgentField;

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button End;

    @FXML
    private ScrollBar HS;

    @FXML
    private Button MarkAdd;

    @FXML
    private TextField MarkField;

    @FXML
    private TextField MaxProfField;

    @FXML
    private TextField MinProfField;

    @FXML
    private Button ProfAdd;

    @FXML
    private Button ProjectAdd;

    @FXML
    private ChoiceBox<?> ProjectsChoice;

    @FXML
    private TextField ProjectsField;

    @FXML
    private TextArea TextArea;

    @FXML
    private ScrollBar VS;

    public Controller() throws SQLException {
    }

    @FXML
    void initialize() {
        AgentAdd.setOnAction(actionEvent -> {
            String s[] = AgentField.getText().split(",");
            checkAgents(s[0]);
            agents.add(new Agent(s[0], Double.parseDouble(s[1])));
            if (!checkAgents(s[0])) {
                String tmp[] = new String[agents.size()];
                for (int i = 0; i < agents.size(); i++) {
                    tmp[i] = agents.get(i).getName();
                }
                AgentChoice.getItems().remove(s[0]);
                AgentChoice.getItems().add(s[0]);
            }
        });
        ProjectAdd.setOnAction(actionEvent -> {
            String s[] = ProjectsField.getText().split(",");
            checkAgents(s[0]);
            agents.add(new Agent(s[0], Double.parseDouble(s[1])));
            if (!checkAgents(s[0])) {
                String tmp[] = new String[agents.size()];
                for (int i = 0; i < agents.size(); i++) {
                    tmp[i] = agents.get(i).getName();
                }
                AgentChoice.getItems().remove(s[0]);
                AgentChoice.getItems().add(s[0]);
            }
        });
    }


    private boolean checkAgents(String name) {
        boolean flag = false;
        for (int i = 0; i < agents.size() ; i++) {
            if (Objects.equals(agents.get(i).getName(), name)){
                agents.remove(i);
                flag = true;
            }
        }
        return !flag;
    }
    private boolean checkProjects(String name) {
        boolean flag = false;
        for (int i = 0; i < agents.size() ; i++) {
            if (Objects.equals(agents.get(i).getName(), name)){
                agents.remove(i);
                flag = true;
            }
        }
        return !flag;
    }

}
