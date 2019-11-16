package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController implements Subject{

	private String dungeonFile;
	private ArrayList <Observer> observers = new ArrayList<>();
	
    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;
    
    private Dungeon dungeon;
    
    private TimelineObject timeline = new TimelineObject(0.3);

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        for (Entity e : dungeon.getEntities()) {
        	if (e instanceof Enemy || e instanceof Player) {
        		timeline.addObserver((playerObserver)e);
        	}
        }  
    }

    @FXML
    public void initialize() {
    	Image ground = null;
    	if(dungeon.isPacman) {
    		ground = new Image("/dirt-pacman.png");
    	} else {
    		ground = new Image("/dirt_0_new.png");
    	}
        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        
        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
    }    

    @FXML
    public void handleKeyPress(KeyEvent event) {
    	if (player.getCanMove()) {
	        switch (event.getCode()) {
	        case UP:
	            player.moveUp();
	            break;
	        case DOWN:
	            player.moveDown();
	            break;
	        case LEFT:
	            player.moveLeft();
	            break;
	        case RIGHT:
	            player.moveRight();
	            break;
	        default:
	        	break;
	        }
    	}
    	switch (event.getCode()) {
        case SPACE:
        	startGame();
        	break;
        case M:
        	pauseGame();
        	notifyEntities("menu");
        	break;
        case R:
        	pauseGame();
    		notifyEntities("reset");
        	break;
        case ESCAPE:
        	pauseGame();
        	notifyEntities("close");
        default:
            break;
        }
    }

    public String getFile() {
    	return dungeonFile;
    }
    
    public void startGame() {
    	player.setCanMove(true);
    	timeline.begin();
    }
    
    public void pauseGame() {
    	player.setCanMove(false);
    	timeline.end();
    }
    
    public Dungeon getDungeon() {
    	return this.dungeon;
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

