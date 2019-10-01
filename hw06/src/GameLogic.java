// ---------------------------------------------------------------------------
// C343 / Summer 2019
// ---------------------------------------------------------------------------

import java.util.LinkedList;
import java.util.List;
import java.util.*;

// ---------------------------------------------------------------------------
// GameLogic implements the "Flood Fill" logic behind each move in the game
// ---------------------------------------------------------------------------
public class GameLogic {

    private FloodFillGame theGame;

    // floodList is declared as a LinkedList here,
    // alas declared as public (bad practice!),
    // because it is used in the FloodFillGame class...
    // ... you may make it private
    //     and provide methods for accessing it instead:
    // (not compulsory, but would make the code "neater")

    // C343 Homework 06 TODO:
    //    you may have to work with the floodList in your floodFill() method:
    public List<Coordinates> floodList = new LinkedList<Coordinates>();

    // ---------------------------------------------------------------------------
    // constructor for the game logic
    // ---------------------------------------------------------------------------
    public GameLogic(FloodFillGame pGame) {
        theGame = pGame;

        // when the game begins,
        //   the tile at the left/top corner
        //   is the only one considered "filled"
        floodList.add(
                new Coordinates(0, 0)
        );
    } // end of GameLogic()



    // ---------------------------------------------------------------------------
    // C343 Homework 06 TODO:
    //    implement the floodFill() function!
    //
    // ---------------------------------------------------------------------------
    public void floodFill(int colorToFlood) {
        // C343 Homework 06 TODO:
        //    implement the floodFill() function!
        //
        System.out.println(floodList.size());
        for(int i = 0; i < floodList.size(); i++){
            if(onBoard(toTheLeft(floodList.get(i)))){
                if(this.theGame.getColor(toTheLeft(floodList.get(i))) == colorToFlood && !floodList.contains(toTheLeft(floodList.get(i)))){
                    floodList.add(toTheLeft(floodList.get(i)));
                }
            }
            if(onBoard(toTheRight(floodList.get(i)))){
                if(this.theGame.getColor(toTheRight(floodList.get(i))) == colorToFlood && !floodList.contains(toTheRight(floodList.get(i)))){
                    floodList.add(toTheRight(floodList.get(i)));
                }
            }
            if(onBoard(above(floodList.get(i)))){
                if(this.theGame.getColor(above(floodList.get(i))) == colorToFlood && !floodList.contains(above(floodList.get(i)))){
                    floodList.add(above(floodList.get(i)));
                }
            }
            if(onBoard(below(floodList.get(i)))){
                if(this.theGame.getColor(below(floodList.get(i))) == colorToFlood && !floodList.contains(below(floodList.get(i)))){
                    floodList.add(below(floodList.get(i)));
                }
            }
        }

    } // end of floodFill()
    // ---------------------------------------------------------------------------
    public int bestColor(){

        int[] colors  = new int[6];
        for(int i = 0; i < colors.length; i++){
            colors[i] = 0;
        }


        for(int i = 0; i < floodList.size(); i++) {
            if(onBoard(above(floodList.get(i)))) {
                if(!floodList.contains(above(floodList.get(i)))){
                    colors[this.theGame.getColor(above(floodList.get(i)))] += 1;
                }
            }
            if(onBoard(below(floodList.get(i)))) {
                if(!floodList.contains(below(floodList.get(i)))){
                    colors[this.theGame.getColor(below(floodList.get(i)))] += 1;
                }
            }
            if(onBoard(toTheRight(floodList.get(i)))) {
                if(!floodList.contains(toTheRight(floodList.get(i)))){
                    colors[this.theGame.getColor(toTheRight(floodList.get(i)))] += 1;
                }
            }
            if(onBoard(toTheLeft(floodList.get(i)))) {
                if(!floodList.contains(toTheLeft(floodList.get(i)))){
                    colors[this.theGame.getColor(toTheLeft(floodList.get(i)))] += 1;
                }
            }
        }
        int best = 0;
        int bestIndex = 0;
        for(int i = 0; i < colors.length; i++){
            if(colors[i] > best){
                best = colors[i];
                bestIndex = i;
            }
        }
        System.out.println(Arrays.toString(colors));
        System.out.println(best);
        return bestIndex;
    }


    // ---------------------------------------------------------------------------
    // onBoard() returns true, if coords specify a tile *on* the game board
    // ---------------------------------------------------------------------------
    public boolean onBoard(final Coordinates coords) {
        final int x = coords.x;
        final int y = coords.y;
        final int size = this.theGame.size;
        return x > -1 && x < size && y > -1 && y < size;
    } // end of onBoard()

    // ---------------------------------------------------------------------------
    // return Coordinates of the tile above the tile at coords
    // ---------------------------------------------------------------------------
    public Coordinates above(final Coordinates coords) {
        return new Coordinates(coords.x, coords.y-1);
    }

    // ---------------------------------------------------------------------------
    // return Coordinates of the tile below the tile at coords
    // ---------------------------------------------------------------------------
    public Coordinates below(final Coordinates coords) {
        // instantiate new Coordinates object:
        return new Coordinates(coords.x, coords.y+1);
    }

    // ---------------------------------------------------------------------------
    // return Coordinates of the tile to the left of the tile at coords
    // ---------------------------------------------------------------------------
    public Coordinates toTheLeft(final Coordinates coords) {
        // instantiate new Coordinates object:
        return new Coordinates(coords.x-1, coords.y);
    }

    // ---------------------------------------------------------------------------
    // return Coordinates of the tile to the toTheRight of the tile at coords
    // ---------------------------------------------------------------------------
    public Coordinates toTheRight(final Coordinates coords) {
        // instantiate new Coordinates object:
        return new Coordinates(coords.x + 1, coords.y);
    }

    // ---------------------------------------------------------------------------
    // get the color of the tile at coords
    // ---------------------------------------------------------------------------
    public int colorAtCoordinates(final Coordinates coords) {
        // ask theGame object, which color at coords:
        return this.theGame.getColor(coords);
    }

} // end of class GameLogic
// ---------------------------------------------------------------------------