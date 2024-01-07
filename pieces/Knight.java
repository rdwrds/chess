package pieces;
import java.util.ArrayList;

import board.Board;

public class Knight extends Piece
{
    public Knight(int x, int y, Color c)
    {
        super(x, y, c);
        this.icon = "N";
        this.defaultIcon = icon;
    }

    public ArrayList<String> getMoves()
    {
        return this.moves;
    }

    public void setMoves(Board board)
    {
        ArrayList<String> newMoves = new ArrayList<String>();

        Piece[][] b = board.getBoard();

        //possible knight moves
        int X[] = {1,2,2,1,-1,-2,-2,-1};
        int Y[] = {2,1,-1,-2,-2,-1,1,2};

        int x1 = this.posX;
        int y1 = this.posY;

        //non-edge cases
        for(int i = 0; i < 8; i++)
        {
            int endX = x1 + X[i];
            int endY = y1 + Y[i];

            String temp = Integer.toString(endX) + Integer.toString(endY);

            if((endX > -1 && endX < 8) && (endY > -1 && endY < 8))
            {
                if(b[endX][endY].color != this.color) newMoves.add(temp);
            }
        }

        this.moves = newMoves;
    }

    public String toString()
    {
        return this.icon;
    }
}
