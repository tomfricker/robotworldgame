/**
 * Bag
 * Author James
 * The bag allows for the player to pickup objects which may be used for scoring or solving puzzles within a level. 
 * It can be used for general items when the inventory is used 
 */
package robot.game;

import java.util.HashMap;
import java.util.HashSet;

import npcs.PICKUPID;
import npcs.Pickup;

public final class Bag {

	
	public static Bag instance = null;
	public static String bagType = "a";
	public static boolean typed = false;
	
	//handles level specific pickups
	public static HashMap<PICKUPID,Integer> pickups = new HashMap<>();
	
	//can be used to add general objects to an inventory
	private static HashSet<GameObjects> inventory = new HashSet<>();
	
	public static Bag getInstance() {
		if(instance == null)
			instance = new Bag();
		//System.out.println("Bag created");
		return instance;
		
		}
	
	// The following methods are used for the collection level and potentially for pickup objects which are not necessarily of use to the player but are used for the scoring of a level.
	public static void addToBag(Pickup o) {
		PICKUPID pid = o.getPID();
		if(pickups.get(pid)!= null) {
			pickups.put(pid,pickups.get(pid)+1);
		}
		else{
			pickups.put(pid,1);
			}
		
	}
	
	public static void removePickups(PICKUPID p) {
		pickups.put(p, 0);
	}
	
	
	public static void setType(String s) {
		typed = true;
		bagType = s;
	}
	
	public static String getBagType() {
		return bagType;
	}
	
	public static HashMap getPickups() {
		return pickups;
	}
	//--------------------------------------------
	
	//Inventory Methods may be more useful for objects which play more of a role in the level.
	
	public static void addToInventory(GameObjects o) {
		inventory.add(o);
	}
	
	public static void removeFromInventory(GameObjects o) {
		inventory.remove(o);
	}
	
	
	
	}//eoc
