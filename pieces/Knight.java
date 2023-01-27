package pieces;
import java.util.ArrayList;

public class Knight extends Piece
{
    public Knight(int x, int y, Color c)
    {
        super(x, y, c);
        this.icon = "N";
        this.defaultIcon = icon;
    }

    public ArrayList<String> getMoves(Piece[][] b)
    {
        ArrayList<String> moves = new ArrayList<String>();

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
                if(b[endX][endY].color != this.color) moves.add(temp);
            }
        }

        return moves;
    }

    public String toString()
    {
        return this.icon;
    }
}
