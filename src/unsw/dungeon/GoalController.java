package unsw.dungeon;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class GoalController{
	private StringProperty goals;
	
	public GoalController() {		
	}
	
	@FXML
	private Pane pane;
	
	@FXML
	private Label label;
	
	public void setGoals(StringProperty goals) {
		this.goals = goals;
		label.textProperty().bind(goals);
	}
}