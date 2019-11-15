package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DungeonApplication extends Application implements Observer {

	Stage primaryStage;
	Stage secondStage;
	String fileName;
	
	int state = 0;
	
    @Override
    public void start(Stage primaryStage) throws IOException {   	
    	this.primaryStage = primaryStage;
    	loadMenu();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void update(Subject obj, String fileName) {	
		if ((state == 0 && obj instanceof MenuController) || (obj instanceof UiController && fileName.equals("reset"))) {
			loadDungeon(fileName);
		}
		else if (state == 1 && fileName.equals("menu")){
			loadMenu();
		}
	}
	
	public void loadMenu() {
		try {
			try {
				secondStage.close();
			}
			catch(Exception e) {
			}
	    	primaryStage.setTitle("Menu");
	
	        MenuController controller = new MenuController();
	        controller.addObserver(this);
	
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
	        
	        loader.setController(controller);
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        root.requestFocus();
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        state = 0;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadDungeon(String fileName) {
		try {
			primaryStage.setTitle("Dungeon");
			if (!fileName.equals("reset"))
				this.fileName = fileName;
	        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(this.fileName);
	        DungeonController controller = dungeonLoader.loadController();

	        controller.getDungeon().getAlive().addListener(new ChangeListener<Boolean>() {
	        	@Override
	            public void changed(ObservableValue<? extends Boolean> observable,
	                    Boolean oldValue, Boolean newValue) {
	                if(!newValue) {
	                	update(null,"menu");
	                	controller.pauseGame();
	                }
	            }
	        });
	        
	        controller.getDungeon().getHasWon().addListener(new ChangeListener<Boolean>() {
	        	@Override
	            public void changed(ObservableValue<? extends Boolean> observable,
	                    Boolean oldValue, Boolean newValue) {
	                if(!newValue) {
	                	update(null,"menu");
	                	controller.pauseGame();
	                }
	            }
	        });
	        
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
	        
	        UiController uiController = new UiController(controller.getDungeon());
	        uiController.addObserver((Observer)controller);
	        uiController.addObserver(this);
	        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("UiView.fxml"));
	        
	        loader2.setController(uiController);
	        Parent root2 = loader2.load();
	        try{
	        	secondStage.close();
	        }
	        catch (Exception e){	
	        }
	        
	        loader.setController(controller);
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        root.requestFocus();
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
	        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
	        secondStage = new Stage();
	        secondStage.setScene(new Scene(root2));
            secondStage.show();
            secondStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2 + primaryStage.getWidth());
            secondStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);            
	        state = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
