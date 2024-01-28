
// This interface acts as the formula to creating the pieces. 
// Each pieces must provide the informations as stated in the makePiece() method's parameters.
// Used in : CreatePiece.java
// Author: Luqman

interface PieceMaker {
    // Written by: Luqman
    Piece makePiece(int x, int y, char status, char side);
}
