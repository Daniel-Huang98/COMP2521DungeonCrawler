package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import movement.BlueGhostCloser;
import movement.BlueGhostFurther;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image boulderImage;
    private Image potionImage;
    private Image doorImage;
    private Image enemyImage;
    private Image exitImage;
    private Image goldImage;
    private Image swordImage;
    private Image keyImage;
    private Image portalImage;
    private Image switchImage;
    
    private Image playerImagePacman;
    private Image wallImagePacman;
    private Image potionImagePacman;
    private Image doorImagePacman;
    private Image ghostImage1;
    private Image ghostImage2;
    private Image ghostImage3;
    private Image ghostImage4;
    private Image goldImagePacman;
    private Image portalImagePacman;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        boulderImage = new Image("boulder.png");
        potionImage = new Image("brilliant_blue_new.png");
        doorImage = new Image("closed_door.png");
        enemyImage = new Image("ghost.png");
        exitImage = new Image("exit.png");
        goldImage = new Image("gold_pile.png");
        swordImage = new Image("greatsword_1_new.png");
        keyImage = new Image("key.png");
        portalImage = new Image("portal.png");
        switchImage = new Image("pressure_plate.png");
        
        
        playerImagePacman = new Image("/pacman.png");
        wallImagePacman = new Image("/wall-pacman.png");
        potionImagePacman = new Image("/potion-pacman.png");
        doorImagePacman = new Image("/door-pacman.png");
        ghostImage1 = new Image("/ghost1-pacman.png");
        ghostImage2 = new Image("/ghost2-pacman.png");
        ghostImage3 = new Image("/ghost3-pacman.png");
        ghostImage4 = new Image("/ghost4-pacman.png");
        goldImagePacman = new Image("/point-pacman.png");
        portalImagePacman = new Image("/dirt-pacman.png");
    }

    @Override
    public void onLoad(Entity player, boolean pacman) {
    	ImageView view = null;
    	if(pacman) view = new ImageView(playerImagePacman);
    	else view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall,boolean pacman) {
    	ImageView view = null;
    	if(pacman) view = new ImageView(wallImagePacman);
    	else view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Boulder boulder,boolean pacman) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }
    
	@Override
	public void onLoad(Exit exit,boolean pacman) {
		ImageView view = new ImageView(exitImage);
        addEntity(exit, view);	
	}

	@Override
	public void onLoad(Potion potion,boolean pacman) {
		ImageView view = null;
    	if(pacman) view = new ImageView(potionImagePacman);
    	else view = new ImageView(potionImage);
        addEntity(potion, view);
	}

	@Override
	public void onLoad(Door door,boolean pacman) {
		ImageView view = null;
    	if(pacman) view = new ImageView(doorImagePacman);
    	else view = new ImageView(doorImage);
        addEntity(door, view);
	}

	@Override
	public void onLoad(Enemy enemy,boolean pacman) {
		ImageView view = null;
    	view = new ImageView(enemyImage);
        addEntity(enemy, view);
	}
	
	@Override
	public void onLoad(Enemy enemy,boolean pacman, int ghost) {
		ImageView view = null;
		switch(ghost) {
			case 0:
				view = new ImageView(ghostImage1);
				enemy.setFurther(new BlueGhostFurther());
				break;
			case 1:
				view = new ImageView(ghostImage2);
				enemy.setFurther(new BlueGhostFurther());
				break;
			case 2:
				view = new ImageView(ghostImage3);
				enemy.setFurther(new BlueGhostFurther());
				break;
			case 3:
				view = new ImageView(ghostImage4);
				enemy.setFurther(new BlueGhostFurther());
				break;
		}
		addEntity(enemy, view);
	}
	
	@Override
	public void onLoad(FloorSwitch floorSwitch,boolean pacman) {
		ImageView view = new ImageView(switchImage);
        addEntity(floorSwitch, view);
	}

	@Override
	public void onLoad(Gold gold,boolean pacman) {
		ImageView view = null;
    	if(pacman) view = new ImageView(goldImagePacman);
    	else view = new ImageView(goldImage);
        addEntity(gold, view);
	}

	@Override
	public void onLoad(Key key,boolean pacman) {
		ImageView view = new ImageView(keyImage);
        addEntity(key, view);
	}

	@Override
	public void onLoad(Portal portal,boolean pacman) {
		ImageView view = null;
    	if(pacman) view = new ImageView(portalImagePacman);
    	else view = new ImageView(portalImage);
        addEntity(portal, view);
	}

	@Override
	public void onLoad(Sword sword,boolean pacman) {
		ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
	}

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entity.setView(view);
        //hides the image when boolean value set to true;
        entity.gone().addListener(new ChangeListener<Boolean>() {
        	@Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {
                if(newValue) {
                	view.setImage(null);
                }
            }
        });
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

}
