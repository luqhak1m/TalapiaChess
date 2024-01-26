

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

import java.util.ArrayList;

public class CurrentState {

    private String path;

    private int turnCountState;
    private char whoseTurnState; 
    private PieceState[][] piecePositionsState=new PieceState[Board.row][Board.column];

    private static ArrayList<CurrentState> savedState=new ArrayList<>();

    private static CurrentState currentStateControl;

    private CurrentState(String txtPath){
        this.path=txtPath;
    }


    public void setTurnCountState(int tCount){
        this.turnCountState=tCount;
    }

    public void setWhoseTurnState(char wTurn){
        this.whoseTurnState=wTurn;
    }

    public void setPiecesPositionState(PieceState pieceState){
        this.piecePositionsState[pieceState.getPosX()][pieceState.getPosY()]=pieceState;
    }

    public int getTurnCountState(){
        return turnCountState;
    }

    public char getWhoseTurnState(){
        return whoseTurnState;
    }

    public PieceState[][]getPiecePositionsState(){
        return piecePositionsState;
    }

    public ArrayList<CurrentState> getSavedStates(){
        return savedState;
    }

    public String getPath(){
        return path;
    }
    
    public static CurrentState getCurrentStateController(){
        if(currentStateControl==null){currentStateControl=new CurrentState("chess_save.txt");}
        return currentStateControl;
    }

    // Load the game state from a text file
    public void loadState() {

        clearPiecesPositions();

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

                    piecePositionsState[x][y]=new PieceState(pieceType, x, y, status, side);
                }
            }

            JOptionPane.showMessageDialog(null, "Game loaded!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load the game!");
        }
    }

    public void clearPiecesPositions(){
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                piecePositionsState[i][j]=null;
            }
        }
    }   
}
