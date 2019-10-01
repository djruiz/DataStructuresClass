import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyDeckOfCards implements DeckADT{

    private ArrayList<String> deck = new ArrayList<String>();



    @Override
    public void initialize(){


        for(int i = 1; i <= 13; i++){
            switch (i){
                case 1:
                    deck.add("AS");
                    deck.add("AC");
                    deck.add("AH");
                    deck.add("AD");
                    break;
                case 11:
                    deck.add("JS");
                    deck.add("JC");
                    deck.add("JH");
                    deck.add("JD");
                    break;
                case 12:
                    deck.add("QS");
                    deck.add("QC");
                    deck.add("QH");
                    deck.add("QD");
                    break;
                case 13:
                    deck.add("KS");
                    deck.add("KC");
                    deck.add("KH");
                    deck.add("KD");
                    break;
                default:
                    deck.add(i + "S");
                    deck.add(i + "C");
                    deck.add(i + "H");
                    deck.add(i + "D");
                    break;
            }
        }
    }

    @Override
    public String toString() {
        String hold = "";
        for(int i = 0; i < deck.size(); i++){
            if(i % 4 == 0){
                hold += "\n";
            }
            hold += "      " +  deck.get(i);
        }
        return hold;
    }

    @Override
    public void pushCardOnBottomOfDeck(String s) {
        deck.add(s);
    }

    @Override
    public String drawTopCardFromDeck() {

        String drawn = deck.get(0);
        deck.remove(0);
        return drawn;
    }


    @Override
    public String drawRandomCard() {
        Random numGenerator = new Random();
        int removedPos = numGenerator.nextInt(52);
        String removed = deck.get(removedPos);
        deck.remove(removed);
        return removed;

    }



    public static void main(String[] args){
        DeckADT deck1 = new MyDeckOfCards();
        deck1.initialize();
        System.out.println(deck1);



        //.drawTopCardFromDeck() testing
        String removed = deck1.drawTopCardFromDeck();
        System.out.println(deck1);
        System.out.println("Top card was drawn from the deck: " + removed);


        //test pushCardOnBottom
        deck1.pushCardOnBottomOfDeck("AS");
        System.out.println(deck1);


        //test drawRandom
        removed = deck1.drawRandomCard();
        System.out.println(deck1);
        System.out.println("Random card was drawn from the deck: " + removed);

    }

}
