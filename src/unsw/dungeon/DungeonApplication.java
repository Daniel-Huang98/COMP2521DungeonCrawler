package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application implements Observer {

	Stage primaryStage;
	
    @Override
    public void start(Stage primaryStage) throws IOException {
    	this.primaryStage = primaryStage;
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

    }
    
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void update(Subject obj, String fileName) {	
		try {
			primaryStage.setTitle("Dungeon");
			
	        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(fileName);

	        DungeonController controller = dungeonLoader.loadController();

	        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
	        
	        UiController uiController = new UiController(controller.getDungeon());
	        uiController.addObserver((Observer)controller);
	        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("UiView.fxml"));
	        
	        loader2.setController(uiController);
	        Parent root2 = loader2.load();	        
	        Stage secondStage = new Stage();
	        secondStage.setScene(new Scene(root2));
            secondStage.show();
	        
	        loader.setController(controller);
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        root.requestFocus();
	        primaryStage.setScene(scene);
	        primaryStage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
