package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import compositecheck.CompositeCheck;
import compositecheck.LeafCheck;
import compositecheck.NodeCheck;
import javafx.scene.image.ImageView;
import wincheck.AndWinCheck;
import wincheck.OrWinCheck;
import wincondition.EnemyWin;
import wincondition.ExitWin;
import wincondition.GoldAndSwitch;
import wincondition.GoldWin;
import wincondition.SwitchWin;
import wincondition.WinCondition;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    int ghostNum = 0;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }
    
    public CompositeCheck loadGoals(JSONObject goalCondition, Dungeon dungeon) {
    	CompositeCheck obj = null;
    	String goal = goalCondition.getString("goal");
    	JSONArray subgoals = null;
    	if(goalCondition.has("subgoals")) {
    		subgoals = goalCondition.getJSONArray("subgoals");
    	} 
        if(subgoals == null) {
        	System.out.println("goal: " + goal);
        	switch(goal) {
        		case "enemy":
        			obj = new LeafCheck(dungeon,new EnemyWin());
        			break;
        		case "treasure":
        			obj = new LeafCheck(dungeon,new GoldWin());
        			break;
        		case "exit":
        			obj = new LeafCheck(dungeon,new ExitWin());
        			break;
        		case "switch":
        			obj = new LeafCheck(dungeon,new SwitchWin());
        			break;
        	}
        } else {
        	switch(goal) {
        		case "AND":
        			obj = new NodeCheck(dungeon, new AndWinCheck());
        			break;
        		case "OR":
        			obj = new NodeCheck(dungeon, new OrWinCheck());
        			break;
        	}
        	for(int i = 0; i < subgoals.length(); i++) {
				obj.addCheck(loadGoals(subgoals.getJSONObject(i),dungeon));
			}
        }
        return obj;
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");
        boolean pacman = false;
        Dungeon dungeon = new Dungeon(width, height);
        JSONArray jsonEntities = json.getJSONArray("entities");
        
        JSONObject goalCondition = json.getJSONObject("goal-condition");
        
        System.out.println("Goals are: " + goalCondition.toString(2));
        
        if(json.has("pacman")) {
        	pacman = json.getBoolean("pacman");
        	dungeon.isPacman = pacman;
        }
        
        if(pacman) {
        	for (int x = 0; x < dungeon.getWidth(); x++) {
    	        for (int y = 0; y < dungeon.getHeight(); y++) {
    	        	boolean add = true;
    	        	for(Entity e: dungeon.getEntities()) {
    	        		if(e.getX() == x && e.getY() == y) {
    	        			add = false;
    	        			break;
    	        		}
    	        	}
    	        	for (int i = 0; i < jsonEntities.length(); i++) {
    	                if(jsonEntities.getJSONObject(i).getInt("x") == x && jsonEntities.getJSONObject(i).getInt("y") == y) {
    	        			add = false;
    	        			break;
    	        		}
    	            }
    	        	if(add) {
    	       	 		Entity entity = null;
    	       	 		Gold gold = new Gold(x,y);
    	       	 		onLoad(gold,true);
    	            	entity = gold;
    	            	dungeon.addEntity(entity);
    	            	dungeon.incTotalGold();
    	        	}
    	        }
        	}
        }
        
        

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        
        
       
        
        dungeon.setWinCheck(loadGoals(goalCondition,dungeon));
        
        ArrayList<ArrayList<Entity>> map = new ArrayList<ArrayList<Entity>>();
        for (int i = 0; i < dungeon.getHeight(); i++) {
        	ArrayList<Entity> inner = new ArrayList <Entity>();
        	for (int j = 0; j < dungeon.getWidth(); j++) {
        		inner.add(null);
        	}
        	map.add(inner);
        }
        
        
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
        for(Entity e: dungeon.getEntities()) {
        	map.get(e.getY()).set(e.getX(), e);
        	if(!(e instanceof Player)) {
        		dungeon.getPlayer().addObserver((playerObserver)e);
        		if (e instanceof Boulder) {
	        		for (Entity e2: dungeon.getEntities()) {
	        			if(e2 instanceof Wall || (e2 instanceof Boulder && !e2.equals(e)) || e2 instanceof FloorSwitch)
	        				((Boulder) e).addObserver((playerObserver)e2);
	        		}
        		} else if (e instanceof Portal) {
        			for (Entity e2: dungeon.getEntities()) {
        				if(e2 instanceof Portal && !e2.equals(e) && ((Portal)e2).getId() == ((Portal)e).getId()) {
        					((Portal)e).setPortalPair((Portal)e2);
        				}
        			}
        		} else if (e instanceof Enemy) {
        			enemyList.add((Enemy)e);
        			for (Entity e2: dungeon.getEntities()) {
        				if(e2 instanceof Player) {
        					((Enemy)e).addObserver((playerObserver)e2);
        					((Enemy)e).setPlayer((Player)e2);
        				}
        			}
        		}
        	}
        }
        for (Enemy enemy: enemyList) {
        	enemy.setMap(map);
        }
        
        
        return dungeon;
    }


	private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id;

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player,dungeon.isPacman);
            entity = player;
            dungeon.addEntity(entity);
            return;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall,dungeon.isPacman);
            entity = wall;
            break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
            onLoad(boulder,dungeon.isPacman);
            entity = boulder;
            break;
        case "switch":
        	FloorSwitch floorSwitch = new FloorSwitch(x,y, dungeon);
        	onLoad(floorSwitch, dungeon.isPacman);
            entity = floorSwitch;
            break;
        case "sword":
        	Sword sword = new Sword(x,y);
        	onLoad(sword,dungeon.isPacman);
            entity = sword;
            break;
        case "ghost":
        	Enemy ghost = new Enemy(x,y);
        	onLoad(ghost,dungeon.isPacman,ghostNum++);
            entity = ghost;
            break;
        case "enemy":
        	Enemy enemy = new Enemy(x,y);
        	onLoad(enemy,dungeon.isPacman);
            entity = enemy;
            break;
        case "treasure":
        	Gold gold = new Gold(x,y);
        	onLoad(gold,dungeon.isPacman);
            entity = gold;
            break;
        case "invincibility":
        	Potion potion = new Potion(x,y);
        	onLoad(potion,dungeon.isPacman);
            entity = potion;
            break;
    	case "portal":
    		id = Integer.parseInt(json.getString("id"));
	    	Portal portal = new Portal(x,y,id);
	    	onLoad(portal,dungeon.isPacman);
	        entity = portal;
	        break;
    	case "exit":
    		Exit exit = new Exit(x,y);
    		onLoad(exit,dungeon.isPacman);
    		entity = exit;
    		break;
        case "door":
        	id = Integer.parseInt(json.getString("id"));
    		Door door = new Door(x,y, id);
    		onLoad(door,dungeon.isPacman);
    		entity = door;
    		break;
        case "key":
        	id = Integer.parseInt(json.getString("id"));
    		Key key = new Key(x,y, id);
    		onLoad(key,dungeon.isPacman);
    		entity = key;
    		break;
	}   
        dungeon.addEntity(entity);
        if(entity instanceof Gold) dungeon.incTotalGold();
        else if(entity instanceof FloorSwitch) dungeon.incTotalSwitch();
        else if(entity instanceof Enemy) dungeon.incTotalEnemies();
        
    }

    public abstract void onLoad(Entity player,boolean pacman);
    public abstract void onLoad(Wall wall,boolean pacman);
    public abstract void onLoad(Boulder boulder,boolean pacman);
    public abstract void onLoad(Exit exit,boolean pacman);
    public abstract void onLoad(Potion potion,boolean pacman);
    public abstract void onLoad(Door door,boolean pacman);
    public abstract void onLoad(Enemy enemy,boolean pacman);
    public abstract void onLoad(Enemy enemy,boolean pacman, int ghost);
    public abstract void onLoad(FloorSwitch floorSwitch,boolean pacman);
    public abstract void onLoad(Gold gold,boolean pacman);
    public abstract void onLoad(Key key,boolean pacman);
    public abstract void onLoad(Portal portal,boolean pacman);
    public abstract void onLoad(Sword sword,boolean pacman);    
}
