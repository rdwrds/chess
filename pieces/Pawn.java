package pieces;
import java.util.ArrayList;

import board.Board;

//pawn and king need to know if they've moved for 1st move/castling
public class Pawn extends Piece
{

    public boolean hasMoved;

    public Pawn(int x, int y, Color c)
    {
        super(x, y, c);
        this.icon = "P";
        this.defaultIcon = "P";
        hasMoved = false;
    }
    
    public ArrayList<String> getMoves()
    {
        return this.moves;
    }

    public void setMoves(Board board)
    {
        int x1 = this.posX;
        int y1 = this.posY;

        Piece[][] b = board.getBoard();
        ArrayList<String> removedMoves = new ArrayList<String>();

        //are we gonna move up or down the board?
        int yOff = this.color == Color.WHITE ? 1 : -1;

        ArrayList<String> newMoves = new ArrayList<String>();

        //dont look for a more concise way unless someone points it out, its not worth it 1/3/2023
        //this is also very ugly please find a more concise way ^

        //1st move
        if(!this.hasMoved) if(b[x1][y1 + yOff * 2].color == null) newMoves.add(Integer.toString(x1) + Integer.toString(y1+yOff*2));
        
        //regular move
        if(b[x1][y1+ yOff].color == null) newMoves.add(Integer.toString(x1) + Integer.toString(y1+yOff));
        
        //diag attacks
        if(x1 > 0) if(b[x1-1][y1+ yOff].color != null && b[x1-1][y1+ yOff].color != this.color) newMoves.add(Integer.toString(x1-1) + Integer.toString(y1+yOff));

        if(x1 < 7) if(b[x1+1][y1+ yOff].color != null && b[x1+1][y1+ yOff].color != this.color) newMoves.add(Integer.toString(x1+1) + Integer.toString(y1+yOff));

        this.moves = newMoves;    
    }
    
    public String toString()
    {
        return this.icon;
    }
}
