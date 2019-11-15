package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;


public class UiController implements Subject{
	private boolean isStart = false;
	Dungeon dungeon;
	ArrayList <Observer> observers = new ArrayList<>();
	FloatProperty f = new SimpleFloatProperty((float)0.4);
	public UiController(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	@FXML
	private Button Start;
	
	@FXML
	private Button Pause;
	
	@FXML
	private Button Menu;
	
	@FXML
	private Button Reset;
	
	@FXML
	private ProgressBar GoldProgress;
	
	@FXML
	private ProgressBar KeyProgress;
	
	@FXML
	private ProgressBar SwordProgress;
	
	@FXML
	private ProgressBar PotionProgress;
	
	@FXML
	public void handleStart(ActionEvent event) {
		isStart = true;
		notifyEntities("start");
	}
	
	@FXML
	public void handlePause(ActionEvent event) {
		isStart = false;
		notifyEntities("pause");
	}
	
	@FXML
	public void handleMenu(ActionEvent event) {
		notifyEntities("menu");
	}
	
	@FXML
	public void handleReset(ActionEvent event) {
		isStart = false;
		notifyEntities("reset");
	}
	
	@FXML
	public void initialize() {
		GoldProgress.progressProperty().bind(dungeon.getGoldStatus());
		Player player = dungeon.getPlayer();
		KeyProgress.progressProperty().bind(player.getKeyStatus());
		SwordProgress.progressProperty().bind(player.getSwordStatus());
		PotionProgress.progressProperty().bind(player.getPotionStatus());
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