package pieces;

import java.util.ArrayList;

import board.Board;

public class Rook extends Piece
{
    
    //in order to castle- the rook must not've moved.
    public boolean hasMoved;
    public Rook(int x, int y, Color c)
    {
        super(x, y, c);
        this.icon = "R";
        this.defaultIcon = icon;
        hasMoved = false;
    }

    public ArrayList<String> getMoves()
    {
        return this.moves;
    }

    public void setMoves(Board board)
    {
        ArrayList<String> newMoves = new ArrayList<String>();

        Piece[][] b = board.getBoard();
        //TODO: 11/10/23: get this bitch to be implemented in classes so its easier to access
        King king = this.color == Color.WHITE ? board.whiteKing : board.blackKing;


        //variables we use to save moves in `moves` list
        int endX, endY;
        String temp;

        //preloading x/y coords
        int x1 = this.posX;
        int y1 = this.posY;

        int X[] = {0,1,0,-1};
        int Y[] = {1,0,-1,0};

        //top-right
        for(int i = 0; i < 4; i++)
        {
            int xOff = X[i];
            int yOff = Y[i];

            for(x1 = posX, y1 = posY; (x1 + xOff > -1 && y1 + yOff > -1) && (x1 + xOff < 8 && y1 + yOff < 8); x1 += xOff, y1 += yOff)
            {
                endX = x1 + xOff;
                endY = y1 + yOff;

                temp = Integer.toString(endX) + Integer.toString(endY);

                if(b[endX][endY].color == this.color) break;

                //if the color is null (an empty square), just mark the space
                if(b[endX][endY].color == null)
                {
                    newMoves.add(temp);
                }
                //if its not our color and its not empty, then it must be the opponents. mark it and break the loop
                else
                {
                    newMoves.add(temp);
                    break;
                }
            }
        }
        this.moves = newMoves;  
    }

    public String toString()
    {
        return this.icon;
    }
}
