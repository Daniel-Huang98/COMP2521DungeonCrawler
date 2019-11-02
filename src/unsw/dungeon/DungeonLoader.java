package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import wincheck.AndWinCheck;
import wincheck.OrWinCheck;
import wincondition.EnemyWin;
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

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);
        JSONArray jsonEntities = json.getJSONArray("entities");
        
        JSONObject goalCondition = json.getJSONObject("goal-condition");
        String goal = goalCondition.getString("goal");
        JSONArray goals = goalCondition.getJSONArray("subgoals");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        
        if(goal.equals("AND")) {
        	dungeon.setWinCheck(new AndWinCheck());
        } else if(goal.equals("OR")) {
        	dungeon.setWinCheck(new OrWinCheck());
        } 
        
        System.out.println(goal);
        for(int i = 0; i < goals.length(); i++) {
        	loadGoal(dungeon, goals.getJSONObject(i));
        }
        
        
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
        				if(e2 instanceof Portal && !e2.equals(e)) {
        					((Portal)e).setPortalPair((Portal)e2);
        				}
        			}
        		} else if (e instanceof Enemy) {
        			enemyList.add((Enemy)e);
        			for (Entity e2: dungeon.getEntities()) {
        				if(e2 instanceof Player) {
        					((Enemy)e).addObserver((playerObserver)e2);
        					System.out.println("player is added to enemy");
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

    private void loadGoal(Dungeon dungeon, JSONObject jsonObject) {
		WinCondition condition = null;
		String goal = jsonObject.getString("goal");
		System.out.println(goal);
		switch(goal) {
			case "treasure":
				condition = new GoldWin();
				break;
			case "switch":
				condition = new SwitchWin();
				break;
			case "enemy":
				condition = new EnemyWin();
				break;
		}
    	if(condition != null)dungeon.addWinCondition(condition);
	}

	private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            dungeon.addEntity(entity);
            return;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "switch":
        	FloorSwitch floorSwitch = new FloorSwitch(x,y, dungeon);
        	onLoad(floorSwitch);
            entity = floorSwitch;
            break;
        case "sword":
        	Sword sword = new Sword(x,y);
        	onLoad(sword);
            entity = sword;
            break;
        case "enemy":
        	Enemy enemy = new Enemy(x,y);
        	onLoad(enemy);
            entity = enemy;
            break;
        case "treasure":
        	Gold gold = new Gold(x,y);
        	onLoad(gold);
            entity = gold;
            break;
        case "invincibility":
        	Potion potion = new Potion(x,y);
        	onLoad(potion);
            entity = potion;
            break;
    	case "portal":
	    	Portal portal = new Portal(x,y);
	    	onLoad(portal);
	        entity = portal;
	        break;
    	case "exit":
    		Exit exit = new Exit(x,y);
    		onLoad(exit);
    		entity = exit;
    		break;
        }
        
        dungeon.addEntity(entity);
        if(entity instanceof Gold) dungeon.incTotalGold();
        else if(entity instanceof FloorSwitch) dungeon.incTotalSwitch();
        else if(entity instanceof Enemy) dungeon.incTotalEnemies();
        
    }

    public abstract void onLoad(Entity player);
    public abstract void onLoad(Wall wall);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Potion potion);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Enemy enemy);
    public abstract void onLoad(FloorSwitch floorSwitch);
    public abstract void onLoad(Gold gold);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Portal portal);
    public abstract void onLoad(Sword sword);    
}
