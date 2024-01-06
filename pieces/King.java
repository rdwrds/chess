package pieces;
import java.util.ArrayList;

import board.Board;

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

    public ArrayList<String> getMoves()
    {
        return this.moves;
    }

    public void setMoves(Board board)
    {
        ArrayList<String> newMoves = new ArrayList<String>();
        Piece[][] b = board.getBoard();

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
            
            // 11/8/23 - im pretty sure this will break if we take any pieces with the king. use the board so we can check if we can take pieces
            if((endX > -1 && endX < 8) && (endY > -1 && endY < 8))
            {
                if(b[endX][endY].color != this.color) newMoves.add(temp);
            }
        }
        
        this.moves = newMoves;
    }

    //1/5 - we cant use getMoves b/c we need gKA in getmoves, thatll cause a stack overflow
    public ArrayList<String> getKingAttackers(Board board)
    {
        //TODO: implement in getInputs, to disable moves that dont remove king from check

        //kings coords in text
        String kingCoords = Integer.toString(posX, 10) + Integer.toString(posY, 10);

        ArrayList<String> kingAttackers = new ArrayList<String>();

        Piece[][] b = board.getBoard();

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
                if(current.color == this.color) continue;

                //get possbile moves for this piece
                //sysout "getting currents moves"
                String pieceType = current.getClass().getSimpleName();

                //see if the piece is attacking manually instead of getting moves
                boolean isAttacker;
                switch (pieceType) {
                    case "King":
                        //ignore since kings cant attack each other
                        break;
                    case "Queen":
                        //check row/columns/diags
                        break;
                    case "Bishop":
                        //check diags
                        //see if difference between
                        break;
                    case "Knight":
                        //...check Ls (should be 8?)
                        break;
                    case "Rook":
                        //check rows/cols
                        //if(posx == i || posy == j) isAttacker = true;
                        break;
                    case "Pawn":
                        //check in front to left/right of piece
                        break;

                    default:
                        //do nuffin since its prolly empty
                        break;
                }

                //if its attacking the king, add it to the list
                //kingAttackers.add(currentCoords);


            }
        }

        return kingAttackers;
    }

    public String toString()
    {
        return this.icon;
    }

}
