package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class MenuController implements Subject{
	
	ArrayList <Observer> observers = new ArrayList<>();
	
    @FXML
    private GridPane squares;
    
    @FXML
    private Button Dungeon0;
    
    @FXML
    private Button Dungeon1;
    
    @FXML
    private Button Dungeon2;
    
    @FXML
    private Button Dungeon3;
    
    @FXML
    private Button Dungeon4;
    
    @FXML
    private Button Dungeon5;
    
    @FXML
    private Button Dungeon6;
    
    @FXML
    private Button Dungeon7;
    
    
    public MenuController() {
    }

    @FXML
    public void handleDungeon0(ActionEvent event) {
    	notifyEntities("pacman.json");
    }
    
    @FXML
    public void handleDungeon1(ActionEvent event) {
    	notifyEntities("advanced3.json");
    }
    
    @FXML
    public void handleDungeon2(ActionEvent event) {
    	notifyEntities("advanced3.json");
    }
    
    @FXML
    public void handleDungeon3(ActionEvent event) {
    	notifyEntities("advanced3.json");
    }
    
    @FXML
    public void handleDungeon4(ActionEvent event) {
    	notifyEntities("advanced3.json");
    }
    
    @FXML
    public void handleDungeon5(ActionEvent event) {
    	notifyEntities("advanced3.json");
    }
    
    @FXML
    public void handleDungeon6(ActionEvent event) {
    	notifyEntities("advanced3.json");
    }
    @FXML
    public void handleDungeon7(ActionEvent event) {
    	notifyEntities("advanced3.json");
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

