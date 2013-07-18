import java.util.*;
import java.io.*;
/**
 * This class reads input, and follows through by calling the appropriate methods.
 * It has 2 modes:
 * One for user input.
 * One for user participation.
 * 
 * @author (Jonathan Merrin) 
 * @version (July 9, 2013)
 */
public class User extends Mover
{

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //user specific bools
    private boolean mode;
    private boolean initiative;
    private boolean interacting;
    ////////////////////////////
    private World theWorld;
    private String name;
    private String tag="user";
    private int xLocation = 0;
    private int yLocation = 0;
    protected double strength=1;
    protected double intellect=2;
    protected double health=10.7; //because, why not?
    protected double senseOfHumor=3; //unnecessary for now
    protected double silliness=5.0; //unnecessary for now
    protected double stubbornness = 2.3; //unnecessary for now
    protected double artsiness = .01; //unnecessary for now
    protected double speed = 1.0; //unnecessary for now
    protected double wealth = 1.0; 
    protected double morality = 4;
    private int statsNum = 5;
    protected double[] stats;
    LinkedList<Interactions> impressions;
    boolean isAlien = false; //more future fun with little green men
    private double aggression=0;
    private double compatibility=0;
    private double violence=0;
    private double cunning=0;
    private int hunger = 0;
    private Room currentRoom;

    /**
     * Constructor for objects of class User
     */
    //     public User(){
    //         this(null, null);
    //     }
    //     public User(String newName){
    //         this(null, newName);
    //     }
    //     public User(World newWorld){
    //         this(newWorld, null);
    //     }
    public User(String n, World a){
        theWorld = a;
        name = "*"+n;
        mode = false;
        initiative = false;
        interacting = false;
        // initialise instance variables
        stats = new double[statsNum];
        xLocation = 5;
        yLocation = 4;
        currentRoom= theWorld.getRoom(xLocation,yLocation);
        impressions = new LinkedList<Interactions>();
    }

    public void setStr(double x){
        stats[0] = x;
    }

    public void setInt(double x){
        stats[1] = x;
    }

    public void setHP(double x){
        health = x;
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }

    /**
     * Not used for now
     */
    public void setSOH(double x){
        senseOfHumor = x;
    }

    /**
     * Not used for now
     */
    public void setSilly(double x){
        silliness = x;
    }

    /**
     * Not used for now
     */
    public void setStub(double x){
        stubbornness = x;
    }

    /**
     * Not used for now
     */
    public void setArt(double x){
        artsiness = x;
    }

    /**
     * Not used for now
     */
    public void setSpeed(double x){
        speed = x;
    }

    public void setWealth(double x){
        stats[4] = x;
    }

    public void setMorality(double x){
        stats[2] = x;
    }

    public void setStats(double a, double b, double c, double d, double e, double f, double g, double h, double i, double j){
        this.setStr(a);
        this.setInt(b);
        this.setHP(c);
        this.setSOH(d);//unnecessary for now
        this.setSilly(e);//unnecessary for now
        this.setStub(f);//unnecessary for now
        this.setArt(g);//unnecessary for now
        this.setSpeed(h);//unnecessary for now
        this.setWealth(i);
        this.setMorality(j);
    }

    public double getStat(int x){
        return stats[x];
    }

    public double getStrength(){
        return stats[0];
    }

    public double getIntellect(){
        return stats[1];
    }

    public double getMorality(){
        return stats[2];
    }

    public double getHealth(){
        return stats[3];
    }

    //Still deciding if it is better to have a public variable or a set/get.
    public void setMode(boolean phase){
        mode = phase;
    }

    public boolean getMode(){
        return mode;
    }

    public boolean getInteracting(){
        return interacting;
    }

    public String getTag(){
        return tag;
    }

    public boolean getInitiative(){
        return initiative;
    }

    public void setInitiative(boolean n){
        initiative = n;
    }

    public void start(String input){
        if(input.equals("viewer")){
            mode = false;
        }
        else if(input.equals("map")){
            System.out.println(theWorld);
        }
        else if(input.equals("room")){
            System.out.println(xLocation+"\n"+yLocation);
        }
        else if(input.equals("stay")){
            start("go "+input);
        }
        else if(input.equals("north")||input.equals("south")||input.equals("west")||input.equals("east")){
            start("go "+input);
        }
        else if(input.startsWith("go ")){

            theWorld.moveAll();
            input = input.substring(input.indexOf(" ")+1);
            //System.out.println(input);
            if(input.equals("north")&&yLocation>0){
                yLocation = yLocation-1;
            }
            else if(input.equals("south")&&yLocation<10){
                yLocation = yLocation+1;
            }
            else if(input.equals("east")&&xLocation<10){
                xLocation = xLocation+1;
            }
            else if(input.equals("west")&&xLocation>0){
                xLocation = xLocation-1;
            }
            else if(input.equals("northeast")&&yLocation>0&&xLocation<10){
                yLocation = yLocation-1;
                xLocation = xLocation+1;
            }
            else if(input.equals("northwest")&&yLocation>0&&xLocation>0){
                yLocation = yLocation-1;
                xLocation = xLocation-1;
            }
            else if(input.equals("southeast")&&yLocation<10&&xLocation<10){
                yLocation = yLocation+1;
                xLocation = xLocation+1;
            }
            else if(input.equals("north")&&yLocation<10&&xLocation>0){
                yLocation = yLocation+1;
                xLocation = xLocation-1;
            }
            else if(input.equals("stay")){
                theWorld.moveAll();
            }
            else{
                System.out.println("You can't go that way.");
            }
            theWorld.getRoom(xLocation,yLocation).addMover(this);
            System.out.println(theWorld.getRoom(xLocation,yLocation).firstPersonView());
            if(theWorld.getRoom(xLocation,yLocation).getMoversNum()>1){
                for(int x = 0; x<theWorld.getRoom(xLocation,yLocation).getMoversNum(); x++){
                    if(theWorld.getRoom(xLocation,yLocation).getMovers().get(x).getTag().equals("mover")){
                        if(theWorld.getRoom(xLocation,yLocation).getMovers().get(x).getKnown()){
                            System.out.println("You encounter "+theWorld.getRoom(xLocation,yLocation).getMovers().get(x).getName()+".");
                        }
                        else{
                            System.out.println("There is someone else in the room that you have not met before.");
                        }
                        if(theWorld.getRoom(xLocation,yLocation).getMovers().get(x).getNotice()){
                            if(theWorld.getRoom(xLocation,yLocation).getMovers().get(x).rollFor()){
                                System.out.println("He initiates an interaction.");
                                theWorld.getRoom(xLocation,yLocation).getMovers().get(x).interact(this);
                            }
                            else{
                                System.out.println("He seems to have noticed you, but doesn't look like he's going to initiate conversation.");
                            }
                        }
                        else{
                            System.out.println("He doesn't seem to have noticed you yet.");
                        }
                    }

                }
            }
        }

        else if(input.startsWith("talk")||input.startsWith("interact")||input.startsWith("speak")){
            interacting = true;
            for(int x = 0; x<theWorld.getRoom(xLocation,yLocation).getMoversNum(); x++){
                if(theWorld.getRoom(xLocation,yLocation).getMovers().get(x).getTag().equals("mover")){
                    theWorld.getRoom(xLocation,yLocation).getMovers().get(x).setKnown(true);
                }
            }
        }

    }

    public void respond(String input){
    }
}

