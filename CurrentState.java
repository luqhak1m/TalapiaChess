

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

import java.util.ArrayList;

public class CurrentState {

    private String path;

    private int turnCountState;
    private char whoseTurnState; 
    private Piece[][] piecePositionsState;

    private static ArrayList<CurrentState> savedState=new ArrayList<>();

    private static CurrentState currentStateControl;

    private CurrentState(String txtPath){
        this.path=txtPath;
    }

    public static CurrentState getCurrentStateController(){
        if(currentStateControl==null){currentStateControl=new CurrentState("chess_save.txt");}
        return currentStateControl;
    }

    public void setTurnCountState(int tCount){
        this.turnCountState=tCount;
    }

    public void setWhoseTurnState(char wTurn){
        this.whoseTurnState=wTurn;
    }

    public void setPiecesPositionState(Piece[][] piecesPos){
        this.piecePositionsState=piecesPos;
    }

    public int getTurnCountState(){
        return turnCountState;
    }

    public char getWhoseTurnState(){
        return whoseTurnState;
    }

    public Piece[][]getPiecePositionsState(){
        return piecePositionsState;
    }

    public ArrayList<CurrentState> getSavedStates(){
        return savedState;
    }

    public String getPath(){
        return path;
    }
    
    // Load the game state from a text file
    public void loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            // Read and restore game state information
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("TurnCount:")) {
                    turnCountState = Integer.parseInt(line.substring("TurnCount: ".length()));
                } else if (line.startsWith("WhoseTurn:")) {
                    whoseTurnState = line.charAt("WhoseTurn: ".length());
                } else if (line.startsWith("Piece:")) {
                    // Parse and create pieces based on the loaded information
                    String[] tokens = line.split(", ");
                    String pieceType = tokens[0].substring("Piece: ".length());
                    int x = Integer.parseInt(tokens[1].substring("X: ".length()));
                    int y = Integer.parseInt(tokens[2].substring("Y: ".length()));
                    char status = tokens[3].substring("Status: ".length()).charAt(0);
                    char side = tokens[4].substring("Side: ".length()).charAt(0);

                    Piece.piecePositions[x][y]=CreatePiece.createPiece(pieceType, x, y, status, side);

                    // Initialize the pieces on the board
                    // initializePiece(pieceType, x, y, status, side);
                }
            }

            JOptionPane.showMessageDialog(null, "Game loaded!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load the game!");
        }
    }

    
    
}
