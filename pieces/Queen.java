package pieces;

import java.util.ArrayList;

public class Queen extends Piece
{

    public Queen(int x, int y, Color c)
    {
        super(x, y, c);
        this.icon = "Q";
        this.defaultIcon = icon;
    }

    public ArrayList<String> getMoves(Piece[][] b)
    {
        ArrayList<String> moves = new ArrayList<String>();

        //combined offsets of diagonal AND orthogonal moves. 0's are orthogonal
        int X[] = {0,1,1,1,0,-1,-1,-1};
        int Y[] = {1,1,0,-1,-1,-1,0,1};

        int x1 = this.posX;
        int y1 = this.posY;

        //for each direction we can go,
        for(int i = 0; i < 8; i++)
        {
            int xOff = X[i];
            int yOff = Y[i];

            //loop over each direction from the given x/y offset
            for(x1 = posX, y1 = posY; (x1 + xOff > -1 && y1 + yOff > -1) && (x1 + xOff < 8 && y1 + yOff < 8); x1 += xOff, y1 += yOff)
            {
                int endX = x1 + xOff;
                int endY = y1 + yOff;

                String temp = Integer.toString(endX) + Integer.toString(endY);

                //break out of the loop if we come across one of our pieces
                if(b[x1+xOff][y1+yOff].color == this.color) break;

                //if the color is null (an empty square), just mark the space
                if(b[x1+xOff][y1+yOff].color == null)
                {
                    moves.add(temp);
                }
                //if its not our color and its not empty, then it must be the opponents. mark it and break the loop
                else
                {
                    moves.add(temp);
                    break;
                }
            } 

        }
        
        return moves;
    }

    
    public String toString()
    {
        return this.icon;
    }
}
