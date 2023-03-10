package pieces;
import java.util.ArrayList;

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

    public ArrayList<String> getMoves(Piece[][] b)
    {
        int x1 = this.posX;
        int y1 = this.posY;

        //are we gonna move up or down the board?
        int yOff = this.color == Color.WHITE ? 1 : -1;

        ArrayList<String> moves = new ArrayList<String>();

        //dont look for a more concise way unless someone points it out, its not worth it 1/3/2023
        //this is also very ugly please find a more concise way ^
        if(!this.hasMoved) if(b[x1][y1 + yOff * 2].color == null) moves.add(Integer.toString(x1) + Integer.toString(y1+yOff*2));
        
        if(b[x1][y1+ yOff].color == null) moves.add(Integer.toString(x1) + Integer.toString(y1+yOff));

        if(x1 > 0) if(b[x1-1][y1+ yOff].color != null && b[x1-1][y1+ yOff].color != this.color) moves.add(Integer.toString(x1-1) + Integer.toString(y1+yOff));

        if(x1 < 7) if(b[x1+1][y1+ yOff].color != null && b[x1+1][y1+ yOff].color != this.color) moves.add(Integer.toString(x1+1) + Integer.toString(y1+yOff));

        return moves;    
    }
    
    public String toString()
    {
        return this.icon;
    }
}
