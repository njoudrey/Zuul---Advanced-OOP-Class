package zuul;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * method.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game {
    private Parser parser;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        player = new Player(createRooms());
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     * @return starting Room
     */
    private Room createRooms() {
        Room outside;
        Room theater;
        Room pub;
        Room lab;
        Room office;
        Room library;
        Room coopOffice;
        Room athleticCentre;
        Room arena;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        library = new Room("in the library");
        coopOffice = new Room("in the co-op office");
        athleticCentre = new Room("in the athletic centre");
        arena = new Room("in the arena");
        // initialise room exits
        outside.setExits(null, theater, lab, pub);
        theater.setExits(null, null, null, outside);
        pub.setExits(library, outside, null, null);
        lab.setExits(outside, office, coopOffice, null);
        office.setExits(null, null, null, lab);
        library.setExits(null, athleticCentre, pub, null);
        coopOffice.setExits(lab, null, null, null);
        athleticCentre.setExits(null, arena, null, library);
        arena.setExits(null, null, null, athleticCentre);
        return outside;
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.print(player.getCurrentRoom().getExits());
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("look")) {
            System.out.println(player.getCurrentRoom().getExits());
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help look");
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * @param command  the command given
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = null;

        nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            player.setCurrentRoom(nextRoom);
            System.out.print(player.getCurrentRoom().getExits());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @param command  the command given
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }

    /**
    * Main method.
    * @param args  command line arguments
    **/
    public static void main(String[] args) {
        Game toPlay = new Game();
       toPlay.play();
    }
}
