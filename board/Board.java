package board;
//import all pieces from pieces package

import pieces.*;

import java.util.Scanner;
import java.util.ArrayList;



public class Board {

    private Piece[][] b;

    //cache the kings to know when they're in check
    private King blackKing;
    private King whiteKing;

    //white: true  black: false
    //to track which colors turn it is
    boolean state;


    public Board()
    {
        //bottom right will be own color
        //QUEEN ON ITS OWN COLOR
        //white goes on the bottom (index 7 (row 8))

        //every game starts with white
        state = true;

        b = new Piece[8][8];

        Piece[] whiteHomeRow = 
        {
            new Rook(0,0, Color.WHITE),new Knight(1,0, Color.WHITE),
            new Bishop(2,0, Color.WHITE),new Queen(3,0, Color.WHITE),
            new King(4,0, Color.WHITE),new Bishop(5,0, Color.WHITE),
            new Knight(6,0, Color.WHITE),new Rook(7,0, Color.WHITE)
        };

        Piece[] blackHomeRow = 
        {
            new Rook(0,7, Color.BLACK),new Knight(1,7, Color.BLACK),
            new Bishop(2,7, Color.BLACK),new Queen(3,7, Color.BLACK),
            new King(4,7, Color.BLACK),new Bishop(5,7, Color.BLACK),
            new Knight(6,7, Color.BLACK),new Rook(7,7, Color.BLACK)
        };

        Piece[] wPawns =
        {
            new Pawn(0,1, Color.WHITE),new Pawn(1,1, Color.WHITE),
            new Pawn(2,1, Color.WHITE),new Pawn(3,1, Color.WHITE),
            new Pawn(4,1, Color.WHITE),new Pawn(5,1, Color.WHITE),
            new Pawn(6,1, Color.WHITE),new Pawn(7,1, Color.WHITE)
        };

        Piece[] bPawns = 
        {
            new Pawn(0,6, Color.BLACK),new Pawn(1,6, Color.BLACK),
            new Pawn(2,6, Color.BLACK),new Pawn(3,6, Color.BLACK),
            new Pawn(4,6, Color.BLACK),new Pawn(5,6, Color.BLACK),
            new Pawn(6,6, Color.BLACK),new Pawn(7,6, Color.BLACK)
        };
        //pieces
        for(int i = 0; i < 8; i++)
        {
            b[i][0] = whiteHomeRow[i];
        }

        for(int i = 0; i < 8; i++)
        {
            b[i][7] = blackHomeRow[i];
        }

        //pawns
        for(int i = 0; i < 8; i++)
        {
            b[i][1] = wPawns[i];
        }

        for(int i = 0; i < 8; i++)
        {
            b[i][6] = bPawns[i];
        }
        //takes all null squares and fills them with an 'Empty' object
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(b[i][j] == null) b[i][j] = new Empty();
            }
        }

        whiteKing = (King) b[4][0];
        blackKing = (King) b[4][7];
    }

    public Board(int neg)
    {
        if(neg == -1)
        {
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < 8; j++)
                {
                    b[i][j] = new Empty();
                }
            }
        }
    }
    
    public Piece[][] getBoard()
    {
        return this.b;
    }

    public void setBoard(Piece[][] board)
    {
        this.b = board;
    }

    public void displayBoard()
    {
        for(int i = 7; i >= 0; i--)
        {
            System.out.print(i + "  ");
            for(int j = 0; j < 8; j++)
            {
                if(b[j][i] != null) System.out.print(b[j][i] + "  ");
                else System.out.print("X  ");
            }
            System.out.println();
        }
        System.out.print("   ");
        for(int i = 0; i < 8; i++) System.out.print(i + "  ");
        System.out.println();
        System.out.println();
    }

    //returns if pos is empty
    //TODO: actually use isEmpty() lol
    public boolean isEmpty(int x, int y){return b[x][y] == null;}

    public void move(int x1, int y1, int x2, int y2)
    {
        Piece start = this.b[x1][y1];
        String end = Integer.toString(x2) + Integer.toString(y2);

        ArrayList<String> moves = start.getMoves(b);

        System.out.println(moves);

        if(moves.contains(end))
        {
            String className = start.getClass().getSimpleName();

            //move piece to end square and set start to empty
            this.b[x2][y2] = start;
            this.b[x1][y1] = new Empty();

            //set moved piece's coordinates
            this.b[x2][y2].posX = x2;
            this.b[x2][y2].posY = y2;

            //toggle hasMoved booleans for pieces that need it
            switch(className)
            {
                //Piece temp;
                case "Pawn":
                {
                    Pawn temp = (Pawn) start;
                    temp.hasMoved = true;
                    break;
                }
                    
                case "King":
                {
                    King temp = (King) start;
                    temp.hasMoved = true;
                    break;
                }
                case "Rook":
                {
                    Rook temp = (Rook) start;
                    temp.hasMoved = true;
                    break;
                }
            }

        }
        else
        {
            System.out.println("cannot make illegal move");
        }

    }

    
    public void moveWithoutValidation(int x1, int y1, int x2, int y2)
    {
        Piece start = this.b[x1][y1];

        this.b[x2][y2] = start;
        this.b[x1][y1] = new Empty();

        //set moved piece's coordinates
        this.b[x2][y2].posX = x2;
        this.b[x2][y2].posY = y2;
    }

    //display a pieces moves to the player
    private void displayMoves(ArrayList<String> moves)
    {
        //highlight ours moves
        for(String square : moves)
        {
            int x, y;

            x = Character.digit(square.charAt(0), 10);
            
            y = Character.digit(square.charAt(1), 10);

            //highlight all valid squares with O's
            b[x][y].icon = "O";
        }

        //display the board
        this.displayBoard();

        //'de-highlight' the moves
        for(String square : moves)
        {
            int x, y;

            x = Character.digit(square.charAt(0), 10);
            
            y = Character.digit(square.charAt(1), 10);

            //revert all squares to their respective icon
            b[x][y].icon = b[x][y].color == null ? "_" : b[x][y].defaultIcon;
        }

    }
    //converts String inputs to int[]

    public static int[] parseInput(String p)
    {
        int[] temp = {
                      Character.digit(p.charAt(0), 10),
                      Character.digit(p.charAt(1), 10),
                     };
        return temp;
    }

    private int[] getInputs()
    {
        Scanner input = new Scanner(System.in);
        
        //inputs to get coordinates
        String pieceToMove = "";
        String moveToMake = "";

        //arrays for coordinates values
        int[] startCoord = {};
        int[] endCoord = {};

        Piece start = null;

        //return array
        int[] coords = {0,0,0,0};

        Color turn = this.state ? Color.WHITE : Color.BLACK;

        System.out.println("turn: " + turn);

        //TODO: seperate getInputs into getPieceToMove and getMoveToMake
        while(pieceToMove.length() != 2)
            {
                //TODO: CURRENTLY 1/23/23 - fixing input validation
                //if there was an error, flag to re-take input
                boolean reGetInput = false;

                System.out.println("Please enter what piece you'd like to move in the form xy.");
                System.out.println("Enter 88 to quit.");
                pieceToMove = input.nextLine();

                //dont accept numbers outside of the board's range. we use 8 for our escape character
                if(!pieceToMove.matches("[0-8][0-8]"))
                {
                    System.out.println("You have entered an invalid input. Please try again.");
                    reGetInput = true;
                }

                //since we have a valid move, lets display it
                //get an array with our start moves coordinates
                startCoord = Board.parseInput(pieceToMove);

                start = this.b[startCoord[0]][startCoord[1]];

                //are we trying to move an empty piece?
                if(start.color == null)
                {
                    System.out.println("Cannot move an empty square.");
                    reGetInput = true;
                }
                else
                {   
                    //are we trying to move out of turn?
                    if(start.color != turn)
                    {
                        System.out.println("Cannot move opponents piece. Please try again.");
                        reGetInput = true;
                    }
                }

                //display potential moves
                //but first: do we have a valid move?
                if(!reGetInput)
                {

                    //display potential moves
                    if(startCoord[0] != 8 && startCoord[1] != 8)
                    {
                        ArrayList<String> moves = start.getMoves(this.b);

                        displayMoves(moves);
                    }
                }
                else
                {
                    pieceToMove = "";
                }
                
            }
            
            while(moveToMake.length() != 2)
            {
                ArrayList<String> moves = start.getMoves(this.b);

                System.out.println("Please enter where you'd like your piece to move (same form).");
                System.out.println("Enter 88 to quit.");
                moveToMake = input.nextLine();

                if(!moveToMake.matches("[0-8][0-8]"))
                {
                    System.out.println("You have entered an invalid square. Please try again.");
                    moveToMake = "";
                }

                //if its not a move this piece can make: get the input again
                if(!moves.contains(moveToMake) && !moveToMake.equals("88"))
                {
                    System.out.println("Cannot move to invalid square. Please try again.");
                    moveToMake = "";
                }

                //check if the move is not an escape code, and is equal to itself 
                if(pieceToMove.equals(moveToMake) && !moveToMake.equals("88"))
                {
                    System.out.println("Cannot move piece to its own square! Please input another move.");
                    moveToMake = "";
                }
                //move validated; save input
                if(!moveToMake.equals("")) endCoord = Board.parseInput(moveToMake);

                //if a valid place to move piece is not made, display that shit again
                if(moveToMake.equals("")) displayMoves(start.getMoves(b));
            }

            //put start and end coords in one array, `coords`
            coords[0] = startCoord[0];
            coords[1] = startCoord[1];
            coords[2] = endCoord[0];
            coords[3] = endCoord[1];

        //temporary
        return coords;
    }

    private void setState(boolean state)
    {       
        this.state = state;
    }

    private boolean validateCheck(int x1, int y1, int x2, int y2)
    {
        //TODO: only validate move for respective king in check.
        //TODO: make sure color in check's turn is next.
        boolean ret = false;
        //use movewithoutvalidation to move piece back w/o getting 'errors'
        moveWithoutValidation(x1, y1, x2, y2);
        ret = whiteKing.getKingAttackers(this.b).size() != 0 || blackKing.getKingAttackers(this.b).size() != 0;
        System.out.println("either king in check " + ret);
        //move piece back
        moveWithoutValidation(x2, y2, x1, y1);
        return !ret;
    }

    //game loop that handles user input, updates the board, then renders the board to the terminal.
    //implemented according to https://gameprogrammingpatterns.com/game-loop.html
    public void play()
    {
        do
        {
            this.displayBoard();

            //get and save inputs
            int[] coordinates = this.getInputs();

            //if either king is in check
            if(blackKing.getKingAttackers(this.b).size() != 0 || whiteKing.getKingAttackers(this.b).size() != 0)
            {
                boolean checkRemoved = validateCheck(coordinates[0], coordinates[1], coordinates[2],coordinates[3]);
                while(!checkRemoved)
                {
                    System.out.println("Your king is in check. Please move a piece that would remove check.");
                    coordinates = this.getInputs();
                    checkRemoved = validateCheck(coordinates[0], coordinates[1], coordinates[2],coordinates[3]);
                }
            }

            //breaks game loop before calculating move if user quits
            if(coordinates[0] == 8 || coordinates[2] == 8) break;

            //updates king cache every move
            Piece checkForKing = b[coordinates[0]][coordinates[1]];
            //are we moving a king?
            if(checkForKing.getClass().getSimpleName().equals("King"))
            {
                if(checkForKing.color == Color.WHITE)
                {
                    whiteKing = (King) checkForKing;
                }
                else
                {
                    blackKing = (King) checkForKing;
                }
            }
            
            System.out.println("moves selected, altering board: ");

            this.move(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);

            System.out.println("Black King Attackers " + blackKing.getKingAttackers(this.b));
            System.out.println("White King Attackers " + whiteKing.getKingAttackers(this.b));

            this.setState(!this.state);
        }   while(true);
    }
}

