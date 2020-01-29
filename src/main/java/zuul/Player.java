package zuul;

public class Player {
    private Room currentRoom;

    /**
     * Create a player.
     * @param startingRoom is the room where the player will start.
     */
    public Player(Room startingRoom) {
        currentRoom = startingRoom;
    }

    /**
     * @return current room.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * sets the current room.
     * @param newCurrentRoom the new current room.
     */
    public void setCurrentRoom(Room newCurrentRoom) {
        currentRoom = newCurrentRoom;
    }
}
