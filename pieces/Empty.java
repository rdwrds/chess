package pieces;

import java.util.ArrayList;

import board.Board;

public class Empty extends Piece
{
    public Empty()
    {
        this.icon = "_";
    }

    public ArrayList<String> getMoves()
    {
        System.out.println("returning moves of empty square - this shit aint right");
        return this.moves;
    }

    public void setMoves(Board board)
    {
       System.out.println("setting moves in empty square - some shit goin haywire");
    }
    //set methods to print "getting/setting moves in empty square" to diagnose potential issues
}