package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;


public class UiController implements Subject{
	private boolean isStart = false;
	private Dungeon dungeon;
	private ArrayList <Observer> observers = new ArrayList<>();
	
	public UiController(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	@FXML
	private GridPane UiGrid;
	
	@FXML
	private ProgressBar GoldProgress;
	
	@FXML
	private ProgressBar KeyProgress;
	
	@FXML
	private ProgressBar SwordProgress;
	
	@FXML
	private ProgressBar PotionProgress;
	
	/** 
	 * Bind all the status bars to properties
	 */
	@FXML
	public void initialize() {
		GoldProgress.progressProperty().bind(dungeon.getGoldStatus());
		Player player = dungeon.getPlayer();
		KeyProgress.progressProperty().bind(player.getKeyStatus());
		SwordProgress.progressProperty().bind(player.getSwordStatus());
		PotionProgress.progressProperty().bind(player.getPotionStatus());
		GoldProgress.setStyle("-fx-accent: Yellow;");
		KeyProgress.setStyle("-fx-accent: Green;");
		SwordProgress.setStyle("-fx-accent: Black;");
		PotionProgress.setStyle("-fx-accent: Purple");
	}
	
	public boolean getStart() {
		return isStart;
	}
	
	@Override
	public void notifyEntities(String fileName) {
		for (Observer o : observers) {
			o.update(this, fileName);
		}		
	}

	@Override
	public void addObserver(Observer obs) {
		this.observers.add(obs);
	}

	@Override
	public void deleteObserver(Observer obs) {
		this.observers.remove(obs);
		
	}
}