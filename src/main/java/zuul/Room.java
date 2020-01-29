package zuul;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room {
    private String description;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param input The room's description.
     */
    public Room(String input) {
        this.description = input;
        this.exits = new HashMap<String, Room>();
    }

    /**
     * Defines a new exit to the room the room in a certain direction.
     *
     * @param directionToExit the direction of the room you want to exit to
     * @param exitTo the room in that direction
     */
    public void setExit(String directionToExit, Room exitTo) {
        if (exitTo != null) {
            exits.put(directionToExit, exitTo);
        }
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     *
     * @param north The north exit.
     * @param east  The east east.
     * @param south The south exit.
     * @param west  The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west) {
        exits.put("north", north);
        exits.put("east", east);
        exits.put("south", south);
        exits.put("west", west);
    }

     /**
     * Find the exit going a particular direction.
     * Return null if no exit
     *
     * @param direction which way to look.
     * @return the room that is connected in the desired direction. \n Null if no exit found.
     */
    public Room getExit(String direction) {
        Room toReturn = null;
        toReturn = exits.get(direction);
        return toReturn;
    }

    /**
     * @return a string of the possible directions to exit a room
     */
    public String getExits() {
        String exitString = "You are " + description + "\n";
        exitString = exitString + "Exits: ";
        for (String i : exits.keySet()) {
            if (exits.get(i) != null) {
                exitString = exitString + i + " ";
            }
        }
        exitString = exitString + "\n";
        return exitString;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }
}
