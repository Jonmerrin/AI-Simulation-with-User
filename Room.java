import java.util.*;

/**
 * Creates the rooms that will contain characters (and eventually other stuff too)
 */
class Room{

    private String roomName = "Room";

    //will eventually store items that the AI can pick up to sell or trade.
    private Inventory items;

    public int howLongForToString=3; //Sets the substring length for the method toString

    //keeps track of who is currently in the room.
    LinkedList<Mover> peopleInRoom;

    public Room(){
        this("Room");
    }

    public Room(String newNameOfRoom){
        roomName=newNameOfRoom;
        howLongForToString = 3;

        items = new Inventory(this);
        peopleInRoom = new LinkedList<Mover>();
    }

    public String getRoomName(){
        return roomName;
    }

    //returns the list of movers in the room
    public LinkedList<Mover> getMovers(){
        return peopleInRoom;
    }

    //returns the amount of movers in the room.
    public int getMoversNum(){
        return peopleInRoom.size();
    }

    //adds item to room
    public void addItem(Item newItem){
        if(newItem!=null){
            items.add(newItem);
        }
    }

    //removes item from room
    public void removeItem(Item newItem){
        items.remove(newItem);
    }

    /**
     * Adds a mover to the room.
     * Ensures that if there is more than 1 person in the room, they will interact.
     * 
     * The purpose for "rollForInitiative", aside from being a reference, is to randomize which of the two movers will be interacting.
     * Because I go through through the list of movers one at a time to make them move, there would always be the same hierarchy of who would get the priority in the interaction.
     * (i.e. who acts and who reacts)
     * So I randomized it for the sake of fairness.
     */
    public void addMover(Mover newMover){
        if (newMover != null){
            int rollForInitiative = (int)Math.floor(Math.random()*2);
            peopleInRoom.add(newMover);
            if (peopleInRoom.size()>1&&(!peopleInRoom.get(0).getTag().equals("user")&&!peopleInRoom.get(1).getTag().equals("user"))){
                peopleInRoom.get(rollForInitiative).interact(peopleInRoom.get(1-rollForInitiative));
            }
            else{
                if(peopleInRoom.size()>1){
                    if(peopleInRoom.get(rollForInitiative).getTag().equals("mover")){
                        peopleInRoom.get(rollForInitiative).setNotice(true);
                        
                    }
                }
            }
        }
    }

    /**
     * Takes removes a mover from the room.
     */
    public void removeMover(Mover newMover){
        if (newMover != null){
            peopleInRoom.remove(newMover);
        }
    }

    /**
     * I thought about having some fun with little green men running around, but didn't get to finish it before the deadline.
     * I'll finish it after.
     */
    public boolean hasAlien( ) {
        if (peopleInRoom.size()<1){
            return false;
        }else {
            for(int x=0; x<peopleInRoom.size()-1; x++){
                Mover currMover = peopleInRoom.get(x);
                if (currMover.isAlien) {
                    //if (currMover.instanceOf (Class.Alien)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verify that the room has the specified item
     */
    public Item checkItem(String itemName){
        for(int x=0; x<items.size(); x++){
            if(items.get(x).getName().equals(itemName)){
                return items.get(x);
            }
        }
        return null;
    }

    /**
     * looks for a specific item by type.
     * like the one further down.
     */
    public Item checkItemType(String itemType){
        for(int x=0; x<items.size(); x++){
            if( items.get(x).getItemType().equals(itemType)){
                return items.get(x);
            }
        }
        return null;
    }

    /**
     * Gives a list of all the items in the room
     * 
     * not used for the AI, just for my main game, but I think there could be a use for it here too.
     */
    public String tabulateItems(){
        StringBuffer temp;
        if(items.size()>0){
            temp=new StringBuffer("The room contains: ");
            for(int x=0; x<items.size()-1; x++){
                temp.append(items.get(x).getName()+", ");
            }
            if(items.size()>1){
                temp.append("and ");
            }
            temp.append(items.get(items.size()-1).getName()+".");
        }
        else{
            temp=new StringBuffer("This room contains no items that you can take.");
        }
        return temp.toString();
    }

    /**
     * ?? Incomplete search -- search for particular types of items
     * 
     * This will eventually allow you to sift through the room for specific item types.
     * Helpful if a character has an item specific profession (locksmith is partial to keys)
     * More helpful for my main game though.
     * 
     */
    public String searchItemType(){
        StringBuffer temp;
        if(items.size()>0){
            temp=new StringBuffer("The room contains: ");
            for(int x=0; x<items.size()-1; x++){
                temp.append(items.get(x).getName()+", ");
            }
            if(items.size()>1){
                temp.append(temp+"and ");
            }
            temp.append(items.get(items.size()-1).getName()+".");
        }
        else{
            temp=new StringBuffer("This room contains no items that you can take.");
        }
        return temp.toString();
    }

    /**
     * returns specified number of blank spaces
     */
    public String padding(int howManyPads){
        if(howManyPads>0){
            return "";
        }
        else{
            StringBuffer temp=new StringBuffer();
            for(int x=1; x<=howManyPads; ++x){
                temp.append(" ");
            }
            return temp.toString();
        }

    }

    /**
     * 
     *        |       |                  |       |
     *        |       |                  |       |
     *        |       |                  |       |
     *        |       |                  |       |
     *        |       |                  |       |
     *        |       |                  |       |
     *        |       |                  |       |
     *        |_______|                  |_______|
     *        |                                  |
     *        |                                  |
     *        |                                  |
     *        |                                  |
     *        |                                  |
     *        |                                  |
     *       /                                    \
     *     /                                        \
     *   /                                            \
     * /                                                \
     *                       empty
     * 
     * 
     * 
     *
     *        |       |       ...        |       |
     *        |       |    .       .     |       |
     *        |       |   .  O   O  .    |       |
     *        |       |   .    >    .    |       |
     *        |       |    .  \_/  .     |       |
     *        |       |     . ... .      |       |
     *        |       |     .     .      |       |
     *        |_______|  . . . . . . . . |_______|
     *        |        ....................      |
     *        |       ......................     |
     *        |      ........................    |
     *        |      ..... ............ .....    |
     *        |      ..... ............ .....    |
     *        |      ..... ............ .....    |
     *       /       ..... ............ .....     \
     *     /         ..... ............ .....       \
     *   /            | |  ............  | |          \
     * /             /   \....... ....../   \           \
     *                        interacting
     * 
     * 
     *"        |       |                  |       |\n"
     *"        |       |        ..        |       |\n"+
     *"        |       |       ....       |       |\n"+
     *"        |       |        ..        |       |\n"+
     *"        |       |     ........     |       |\n"+
     *"        |       |    .. .... ..    |       |\n"+
     *"        |       |    .. .... ..    |       |\n"+
     *"        |_______|      ..  ..      |_______|\n"+
     *"        |.....         ..  ..         .....|\n"+
     *"        |..                              ..|\n"+
     *"       /                                    \\\n"+
     *"     /                                        \\\n"+
     *"   /                                            \\\n"+
     *" /                                                \\\n"
     *   from a distance and/or unrecognized
     * 
     * 
     */
    public String firstPersonView(){
        String printString = null;
        String topPart ="        |       |                  |       |\n";
        String middlePart = "        |                                  |\n";
        String firstPerson = null;
        if(peopleInRoom.size()==1){
            printString = topPart+topPart+topPart+topPart+topPart+topPart+topPart+"        |_______|                  |_______|\n"+middlePart+middlePart+middlePart+middlePart+middlePart+middlePart+"       /                                    \\ "+"\n"+"      /                                      \\ "+"\n"+"     /                                        \\ ";
        }
        else if(peopleInRoom.size()>=2){
            printString = "        |       |                  |       |\n"+"        |       |        ..        |       |\n"+"        |       |       ....       |       |\n"+"        |       |        ..        |       |\n"+"        |       |     ........     |       |\n"+"        |       |    .. .... ..    |       |\n"+"        |       |    .. .... ..    |       |\n"+"        |_______|      ..  ..      |_______|\n"+"        |.....         ..  ..         .....|\n"+"        |..                              ..|\n"+"       /                                    \\\n"+"      /                                      \\\n"+"     /                                        \\\n"+"    /                                          \\\n";
        }
        return printString;
    }

    /**
     * Returns shorthand form, with blank padding if necessary
     * to be exactly howLongForToString  characters long.
     */
    public String toString(){
        //         if ((this.getMovers( ).length( )) > 0) {
        //             return 
        //         }
        if(roomName.length()<howLongForToString){
            int howMuchPaddingNeeded = howLongForToString-roomName.length();
            return "["+roomName+padding(howMuchPaddingNeeded)+"]";

        }
        else{
            return "["+roomName.substring(0, howLongForToString)+"]";
        }
    }

}
