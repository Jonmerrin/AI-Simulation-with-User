import java.io.*;
import java.util.*;

/**
 * The Main class.
 */
class AI{
    public static void main(String[] args){
        //boolean makes sure game runs continuously
        boolean run = true;
        String name = null;

        //Creates the grid on which the characters move
        World theWorld = new World(10,10,"Earth");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //sets the username
        System.out.println("Welcome User. Please enter your NAME below.");
        try{
            name = br.readLine();
        }
        catch(IOException ioe){
            System.out.println("Wow. You broke it. All I asked for was a name.");
            System.exit(1);
        }
        System.out.println("\n\n\n\n\n\nWelcome, "+name+", to the world.");
        User b = new User(name,theWorld);
        Viewer a = new Viewer(theWorld,b);
         double str=0;
         double sma=0;
         double mor=0;

        try{
            System.out.println("Now, tell me a little about yourself.\n\nOn a scale of one to ten, how strong are you?\n1 is having trouble lifting this laptop, 10 is a participant in the 'World's Strongest Man' competitions.\nFeel free to use decimals.");
            str = (Double.parseDouble(br.readLine()));
            System.out.println("\n\nGreat! Now, on a scale of one to ten, how would you place your intellect?\n1 is never having passed the first grade, 10 is Stephen Hawking.");
            sma = (Double.parseDouble(br.readLine()));
            System.out.println("\n\nWell done! Now, for the final question, on a scale of one to ten, how moral are you?\n1 is cheating, stealing, and possibly even murdering without regret, 10 is never doing anything wrong ever, and feeling terrible when you tell even a little white lie.");
            mor = (Double.parseDouble(br.readLine()));
            b.setStats(str, sma, 10.7,0,0,0,0,0,500,mor);
        }
        catch(IOException ioe){
            System.out.println("DON'T DO THAT. EVER.");
            System.exit(1);
        }
        try{
            br.readLine();
        }
        catch(IOException ioe){
            System.out.println("Really? You just had to hit ENTER. That's it. Nothing special, just a button I know you know how to press, because you just pressed it. God you're stupid. I'm glad you broke it. Saves me the trouble of watching you play.");
            System.exit(1);
        }

        //selects a random number of movers. Movers are the class that are the "characters"
        int characterNum = (int)Math.ceil(Math.random()*20);

        //makes sure that there are no less than 10 movers at a time. It would be pretty boring to just have one guy walking around on his own.
        while (characterNum<10){
            characterNum = (int)Math.ceil(Math.random()*20);
        }

        //Populates the grid with movers. Their respective locations are randomly selected in their constructors.
        for(int x=0; x<characterNum; x++){
            theWorld.createMover();
        }

        //Printing the world prints out a grid that shows where all of the movers are.
        System.out.println(theWorld);

        //Start of it all.
        while(run){

            //Makes sure the input is readable
            String input = null;
            String input2 = null;
            String miscString = null;
            try{
                input=br.readLine();

            }
            catch(IOException ioe){
                System.out.println("I don't know what you're saying. You lose. Automatically. Forever.");
                System.exit(1);
            }
            input=input.toLowerCase();

            if(!b.getMode()){
                a.command(input);
            }
            else{
                b.start(input);
            }

            //allows the player to "hit enter to continue"
            System.out.println("");
            System.out.println("Type a command, or hit enter to continue");
        }
    }
}