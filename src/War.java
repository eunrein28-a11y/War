import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class War {

    // ADD ADDITIONAL CONSTANTS HERE, IF DESIRED...

    static Pile pile1;
    static Pile pile2;
    static int numTurns;

    // ADD ADDITIONAL VARIABLES HERE, IF DESIRED...

    public static void printPiles() {

        // For your convenience, this prints the cards in
        // pile1 and pile2 along with the turn number, as specified
        // by numTurns

        System.out.println("Turn: " + numTurns + System.lineSeparator());
        System.out.println("player1:");
        System.out.println(pile1.toString() + System.lineSeparator());
        System.out.println("player2:");
        System.out.println(pile2.toString());
        System.out.println("--------------------------------------");
    }

    public static void printWar() {

        // Call this method every time there is a tie in the ranks
        // of cards compared

        System.out.println("                                        *** WAR! ***" + System.lineSeparator());
    }

    // ADD ADDITIONAL METHODS HERE, IF DESIRED...

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java War <deck-file>");
            return;
        }

        // args[0] is the deck filename.
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            System.out.println("Loading deck from: " + args[0]);
            pile1 = new Pile(scanner.nextLine());
            pile2 = new Pile(scanner.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + args[0]);
            return;
        }

        numTurns = 1;
        QueueArray<Card> pot = new QueueArray<>();
        // TODO: ADD CODE HERE TO COMPLETE THIS METHOD...
        while(numTurns <= 5000 && !pile1.isEmpty() && !pile2.isEmpty()){
            printPiles();
            Card one = pile1.dequeue();
            Card two = pile2.dequeue();
            pot.enqueue(one);
            pot.enqueue(two);
            if(one.compareTo(two) < 0){
                while(!pot.isEmpty()){
                    pile2.enqueue(pot.dequeue());
                }
            } else if(one.compareTo(two) > 0) {
                while(!pot.isEmpty()) {
                    pile1.enqueue(pot.dequeue());
                }
            }
            else{
                printWar();
                boolean ranOut = false;

                for(int i = 0; i < 3; i++){
                    if(pile1.isEmpty() || pile2.isEmpty()){
                        ranOut = true;
                        break;
                    }
                    pot.enqueue(pile1.dequeue());
                    pot.enqueue(pile2.dequeue());
                }
                if(ranOut){
                    break;
                }
            }
        }
        if(pile1.isEmpty() && pile2.isEmpty()){
            System.out.println("draw!");
        }
        else if(pile1.isEmpty()){
            System.out.println("player 2 wins!");
        }
        else if(pile2.isEmpty()){
            System.out.println("player 1 wins!");
        }
        else{
            System.out.println("draw!");
        }
    }
}
