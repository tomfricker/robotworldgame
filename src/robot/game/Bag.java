/**
 * Bag
 * @author James Galbraith
 * The bag allows for the player to pickup objects which may be used for scoring or solving puzzles within a level. 
 * It can be used for general items when the inventory is used.
 * The bag is kept separate from the Player as it is not required in the majority of levels and would add
 * unnecessary complication to the Player class, particularly with the Player being stored in the Manager Class
 * @author jatg
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
	
	//handles level specific pickups dfg
	public static HashMap<PICKUPID,Integer> pickups = new HashMap<>();
	
	//can be used to add general objects to an inventory
	private static HashSet<GameObjects> inventory = new HashSet<>();
	
	/**
	 * Checks to see if an instance of the Bag class exists and, if 
	 * one does not, creates one and returns it
	 * @return Bag an instance of the Bag static Class
	 */
	public static Bag getInstance() {
		if(instance == null)
			instance = new Bag();
		//System.out.println("Bag created");
		return instance;
		
		}
	
	
	// The following methods are used for the collection level and potentially for pickup objects which are not necessarily of use to the player but are used for the scoring of a level.
	/**
	 * Adds a PickUp object to the Bag. 
	 * If the Bag does not have a PickUp with the same PICKUPID as the parameterised object, 
	 * it places one in the Bag. 
	 * @param o Pickup object 
	 */
	public static void addToBag(Pickup o) {
		PICKUPID pid = o.getPID();
		if(pickups.get(pid)!= null) {
			pickups.put(pid,pickups.get(pid)+1);
		}
		else{
			pickups.put(pid,1);
			}
		
	}
	
	/**
	 * places the integer 0 in the Bag with a key of the PICKUPID passed to the Method in the parameter.
	 * @param p the PICKUPID to be used as a key in the Pickups HashMap. 
	 */
	public static void removePickups(PICKUPID p) {
		pickups.put(p, 0);
	}
	
	/**
	 * Gives the Bag an in game type and sets it according to the String parameter of the Method. 
	 * The String corresponds to a key in the CollectionNPC's revidmap Hashmap and a value in the Pickup's idmap. 
	 * @param s The in game type to be assigned to the Bag. 
	 */
	public static void setType(String s) {
		typed = true;
		bagType = s;
	}
	
	/**
	 * gives the in game type of the Bag as a string. 
	 * @return The bag's in game type as a string.
	 */
	public static String getBagType() {
		return bagType;
	}
	
	/**
	 * returns the pickups HashMap
	 * @return Pickups HashMap
	 */
	public static HashMap getPickups() {
		return pickups;
	}
	//--------------------------------------------
	
	//Inventory Methods may be more useful for objects which play more of a role in the level.
	
	/**
	 * adds a GameObjects object to the Inventory in the Bag class.  
	 * @param o GameObject object to be added to the Bag's inventory. 
	 */
	public static void addToInventory(GameObjects o) {
		inventory.add(o);
	}
	
	/**
	 * removes the specified GameObjects object from the inventory in the Bag
	 * @param o the GameObjects object to be removed from the inventory. 
	 */
	public static void removeFromInventory(GameObjects o) {
		inventory.remove(o);
	}
	
	
	
	}
