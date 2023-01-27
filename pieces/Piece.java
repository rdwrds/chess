package pieces;
import java.util.ArrayList;

public class Piece 
{
    public String icon = ":3";
    public String defaultIcon;
    public int posX;
    public int posY;
    public Color color;

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
    }

    public ArrayList<String> getMoves(Piece[][] b)
    {
        ArrayList<String> moves = new ArrayList<String>();
        //System.out.println("calling super class legal func - returning empty ArrayList<String>");
        return moves;
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







