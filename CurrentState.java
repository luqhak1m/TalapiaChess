
// Save the current state of the game and loading it into the game.
// Singleton is applied here because there shall be only ONE saved state of the game only.
// Used in: GameControl.java
// Authors: Haiqal, Luqman

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class CurrentState {

    private String path;

    private int turnCountState;
    private char whoseTurnState; 
    private PieceState[][] piecePositionsState=new PieceState[Board.row][Board.column];

    private static ArrayList<CurrentState> savedState=new ArrayList<>();

    private static CurrentState currentStateControl;
    
    // Constructor
    // Written by: Luqman
    private CurrentState(String txtPath){
        this.path=txtPath;
    }

    // Getters and Setters.
    // Written by: Luqman
    public void setTurnCountState(int tCount){
        this.turnCountState=tCount;
    }
    public void setWhoseTurnState(char wTurn){
        this.whoseTurnState=wTurn;
    }
    public void setPiecesPositionState(PieceState pieceState){
        this.piecePositionsState[pieceState.getPosX()][pieceState.getPosY()]=pieceState;
    }
    public void setPiecesPositionStateNull(int x, int y){
        this.piecePositionsState[x][y]=null;
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
    
    // Get Singleton object.
    // Written by: Luqman
    public static CurrentState getCurrentStateController(){
        if(currentStateControl==null){currentStateControl=new CurrentState("chess_save.txt");}
        return currentStateControl;
    }

    // Save the current state of the game such as the turn count, whose turn, pieces status.
    // Written by: Haiqal
    public void saveState(int turnCount, char whoseTurn){
        setTurnCountState(turnCount);
        setWhoseTurnState(whoseTurn);

        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                Piece piece=Piece.piecePositions[i][j];
                if(piece!=null){
                    PieceState pieceState=new PieceState(piece.getClass().getSimpleName(), piece.getPosX(), piece.getPosY(), piece.getStatus(), piece.getSide());
                    setPiecesPositionState(pieceState);
                }else{
                    setPiecesPositionStateNull(i, j);;
                }
            }
        }
    }

    // Write the current state of the game to the txt file.
    // Written by: Haiqal
    public void saveGame(int turnCount, char whoseTurn, PieceState[][] piecePositionsState){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            // Save turn count and whose turn it is
            writer.write("TurnCount: " + turnCount + "\n");
            writer.write("WhoseTurn: " + whoseTurn + "\n");

            // Save piece information
            for (int i = 0; i < Board.row; i++) {
                for (int j = 0; j < Board.column; j++) {
                    PieceState pieceState = piecePositionsState[i][j];
                    if (pieceState != null) {
                        // Example format: "Piece: PlusPiece, X: 2, Y: 3, Status: A, Side: Y\n"
                        writer.write("Piece: " + pieceState.getPieceType() +
                                ", X: " + pieceState.getPosX() +
                                ", Y: " + pieceState.getPosY() +
                                ", Status: " + pieceState.getStatus() +
                                ", Side: " + pieceState.getSide());
                        writer.newLine();
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Game saved!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to save the game!");
        }
    }

    // Load the game state from a text file.
    // Written by: Haiqal
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

    // Clear pieces states from its array.
    // Written by: Luqman
    public void clearPiecesPositions(){
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                piecePositionsState[i][j]=null;
            }
        }
    }   
}
