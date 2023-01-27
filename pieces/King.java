package pieces;
import java.util.ArrayList;

public class King extends Piece
{
    public boolean hasMoved;

    public King(int x, int y, Color c)
    {
        super(x, y, c);
        this.icon = "K";
        this.defaultIcon = icon;
        hasMoved = false;
    }

    //check if move is an attempt to castle
    public ArrayList<String> getMoves(Piece[][] b)
    {
        ArrayList<String> moves = new ArrayList<String>();

        //all possible spaces, and their offset from the start position
        int X[] = {1,1, 1, 0,-1,-1,-1,0};
        int Y[] = {1,0,-1,-1,-1, 0, 1,1};

        int x1 = this.posX;
        int y1 = this.posY;

        for(int i = 0; i < 8; i++)
        {
            int endX = x1 + X[i];
            int endY = y1 + Y[i];

            String temp = Integer.toString(endX) + Integer.toString(endY);
            
            if((endX > -1 && endX < 8) && (endY > -1 && endY < 8))
            {
                moves.add(temp);
            }
        }
        
        return moves;
    }

    //iterates over board and see if any pieces are attacking the king
    public ArrayList<String> getKingAttackers(Piece[][] b)
    {
        //TODO: implement in getInputs, to disable moves that dont remove king from check

        //kings coords in text
        String kingCoords = Integer.toString(posX, 10) + Integer.toString(posY, 10);

        ArrayList<String> kingAttackers = new ArrayList<String>();

        //check the board
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                Piece current = b[i][j];
                String currentCoords = Integer.toString(i, 10) + Integer.toString(j, 10);
                
                //ignore empty squares
                if(current.color == null) continue;

                //is it the opps piece?
                if(current.color != this.color)
                {
                    //get possbile moves for this piece
                    ArrayList<String> moves = current.getMoves(b);

                    //if its attacking the king, add it to the list
                    if(moves.contains(kingCoords)) kingAttackers.add(currentCoords);
                }
            }
        }

        return kingAttackers;
    }

    public String toString()
    {
        return this.icon;
    }

}
