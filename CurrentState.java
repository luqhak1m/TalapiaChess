
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public class CurrentState {

    private String path;

    private int turnCountState;
    private char whoseTurnState; 
    
    private Piece[][] piecePositionsState;

    private static ArrayList<CurrentState> savedState=new ArrayList<>();

    public CurrentState(String txtPath){
        this.path=txtPath;
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

    public ArrayList<CurrentState> getSavedStates(){
        return savedState;
    }

    public void saveState(){
        savedState.add(this);  
        JOptionPane.showMessageDialog(null, "Game saved!");
    }
    
    public void writeSavedState(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path))) {
            // Save turn count and whose turn it is
            writer.write(turnCountState + ", " + whoseTurnState); 
            writer.newLine();

            // Save piece information
            for (int i = 0; i < Board.row; i++) {
                for (int j = 0; j < Board.column; j++) {
                    Piece piece = piecePositionsState[i][j];
                    if (piece != null) {
                        // Example format: "Piece: PlusPiece, X: 2, Y: 3, Status: A, Side: Y\n"
                        writer.write(piece.getClass().getSimpleName() + ", " +
                                piece.getPosX() + ", " +
                                piece.getPosY() + ", " +
                                piece.getStatus() + ", " +
                                piece.getSide());

                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // JOptionPane.showMessageDialog(null, "Failed to save the game!");
        }
    }

    
}
