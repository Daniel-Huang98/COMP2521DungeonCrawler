package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BannerController implements Subject{
	ArrayList <Observer> observers = new ArrayList<>();
	Dungeon dungeon;
	
	public BannerController(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	@FXML
	private Label Message;
	
	@FXML
	private Button Retry;
	
	@FXML
	private Button Menu;
	
	@FXML
	public void handleRetry(ActionEvent event) {
		notifyEntities("reset");
	}
	
	@FXML
	public void handleMenu(ActionEvent event) {
		notifyEntities("menu");
	}

	@FXML
	public void initialize() {
		Message.textProperty().bind(dungeon.getMessage());
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