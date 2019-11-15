package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

	String dungeonFile;
	
    @FXML
    private GridPane squares;
    
    //private GridPane ui;

    private List<ImageView> initialEntities;

    private Player player;
    
    private Dungeon dungeon;
    
    private Timeline timeline;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        //this.timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> this.gol.tick()));
		//timeline.setCycleCount(Animation.INDEFINITE);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        //squares.add(new TextField("kill me"),0,dungeon.getHeight());

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        //ui.add(new CheckBox("Key"), 0, 0);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
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

    String getFile() {
    	return dungeonFile;
    }
}

