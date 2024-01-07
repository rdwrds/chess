package pieces;
import java.util.ArrayList;
import board.Board;

public class Piece 
{
    public String icon = ":3";
    public String defaultIcon;
    public int posX;
    public int posY;
    public Color color;
    ArrayList<String> moves;

    public Piece()
    {
        //dont use this constructor
    }

    public Piece(int x, int y, Color c)
    {
        this.posX = x;
        this.posY = y;
        this.color = c;
        this.icon = this.defaultIcon;
        ArrayList<String> moves = new ArrayList<>();
    }

    public ArrayList<String> getMoves()
    {
        System.out.println("returning moves of default piece class - is this shit right?");
        return this.moves;
    }

    //change board to use the piece[][] board, not object board
    public void setMoves(Board board)
    {
       //System.out.println("setting moves in default piece class - some shit goin haywire");
    }

    public void setIcon(String i)
    {
        this.icon = i;
    }

    public String toString()
    {
        return this.icon;
    }
}







