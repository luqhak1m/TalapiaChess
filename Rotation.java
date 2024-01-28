
// Anything related to rotating and resizing the View classes.
// Used in: GameControl.java
// Authors: Aisyah, Asyrani, Luqman

import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Rotation {

    public Rotation(){}

    

    // Rotate the icon on tile.
    // Written by: Aisyah
    public void rotateIcon(Tile t) {
        
        if(t.getRotationStatus()){
            Icon icon = t.getDefaultImg();
            if (icon instanceof ImageIcon) {
                ImageIcon imageIcon = (ImageIcon) icon;
                Image image = imageIcon.getImage();
                Image rotatedImage = rotateImage(image, 180); // Rotate by 180 degrees
                Image resizedImage=rotatedImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                t.setRotatedImg(new ImageIcon(resizedImage));
                t.setIconAtTile();
            }
        }else{
            t.setIconAtTile();
        }
    }

    // Rotate icon.
    // Written by: Aisyah
    public Image rotateImage(Image image, int degrees) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(degrees), width / 2, height / 2);
        g2d.setTransform(at);

        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }

    // Resize icon on tile.
    // Written by: Asyrani
    public void resizeImages(int width, int height, int x, int y) {
        if (Tile.tiles[x][y].getDefaultImg() != null) {
            Image image = Tile.tiles[x][y].getDefaultImg().getImage();
            Image resizedImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            Tile.tiles[x][y].setDefaultImg(new ImageIcon(resizedImage));
            Tile.tiles[x][y].setIconAtTile();
        }

        if (Tile.tiles[x][y].getRotatedImg() != null) {
            Image image = Tile.tiles[x][y].getRotatedImg().getImage();
            Image resizedImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            Tile.tiles[x][y].setRotatedImg(new ImageIcon(resizedImage));
            Tile.tiles[x][y].setIconAtTile();
        }
    }

    // Resize icon based on the boardd's size.
    // Written by: Asyrani
    public void resizeToOriginal() {
        int x = Board.getBoard().getComponentWidth();
        int y = Board.getBoard().getComponentHeight();
        int newX = Board.getBoard().getWidth();
        int newY = Board.getBoard().getBoardHeight();

        // if ((x > 0 && x < 400) || (y > 0 && y < 200)) {
        //     Board.getBoard().setSize(400, 200);
        //     for (int i = 0; i < Board.row; i++) {
        //         for (int j = 0; j < Board.column; j++) {
        //             resizeImages(25, 25, i, j);
        //         }
        //     }

        // } else if ((x > 400 && x < 600) || (y > 200 && y < 400)) {
        //     Board.getBoard().setSize(600, 400);
        //     for (int i = 0; i < Board.row; i++) {
        //         for (int j = 0; j < Board.column; j++) {
        //             resizeImages(65, 65, i, j);
        //         }
        //     }
        // } else if ((x > 600 && x < 800) || (y > 400 && y < 600)) {
        //     Board.getBoard().setSize(800, 600);
        //     for (int i = 0; i < Board.row; i++) {
        //         for (int j = 0; j < Board.column; j++) {
        //             resizeImages(95, 95, i, j);
        //         }
        //     }
        // } else if ((x > 800 && x < 1550) || (y > 600 && y < 800)) {
        //     Board.getBoard().setSize(1000, 800);
        //     for (int i = 0; i < Board.row; i++) {
        //         for (int j = 0; j < Board.column; j++) {
        //             resizeImages(130, 130, i, j);
        //         }
        //     }
        // } else if ((x > 1550) || (y > 800)) {
        //     for (int i = 0; i < Board.row; i++) {
        //         for (int j = 0; j < Board.column; j++) {
        //             resizeImages(175, 175, i, j);
        //         }
        //     }
        // }

        // if ((newX != x) || (newY != y)){
        //     Board.getBoard().displayMessage("Frame Size Optimized!");;
        // }

    }

    // Flip the board when necessary.
    // Written by: Aisyah, Luqman
    public void flipBoard() {

        Board.getBoard().getBoardPanel().removeAll();

        if (!Board.getBoard().getRotationStatus()) {
            for (int i = Board.row - 1; i >= 0; i--) {
                for (int j = Board.column - 1; j >= 0; j--) {
                    Board.getBoard().getBoardPanel().add(Tile.tiles[i][j]);
                }
            }
        } else {
            for (int i = 0; i < Board.row; i++) {
                for (int j = 0; j < Board.column; j++) {
                    Board.getBoard().getBoardPanel().add(Tile.tiles[i][j]);
                }
            }
        }

        Board.getBoard().getBoardPanel().revalidate();
        Board.getBoard().getBoardPanel().repaint();
    }

    // Check if icon on the tile need to be rotated.
    // Written by: Luqman
    public void checkRotation(Piece piece, char whoseTurn, int i, int j){
        if(piece.getSide()!=whoseTurn){ // if it's not the pieces' turn, rotate the icon
            Tile.tiles[i][j].setTileRotationStatus(true);
            if(piece instanceof PointPiece){
                PointPiece pointPiece=(PointPiece) piece;
                    if(pointPiece.getPosX()==0||(pointPiece.getReversedB()||pointPiece.getReversedY())){
                        Tile.tiles[i][j].setTileRotationStatus(false);
                        System.out.println("Tile " + i + ", " + j + " is " + Tile.tiles[i][j].getRotationStatus());
                    }
            }
        }else{
            Tile.tiles[i][j].setTileRotationStatus(false);
            if(piece instanceof PointPiece){
                PointPiece pointPiece=(PointPiece) piece;
                if(pointPiece.getPosX()==5||(pointPiece.getReversedB()||pointPiece.getReversedY())){
                    Tile.tiles[i][j].setTileRotationStatus(true);
                    System.out.println("Tile " + i + ", " + j + " is " + Tile.tiles[i][j].getRotationStatus());
                }
            }
        }
    }

    // Rotate the pieces if necessary.
    // Written by: Luqman
    public void rotateThePiece(){
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                if(Piece.piecePositions[i][j]!=null && Tile.tiles[i][j].getRotationStatus()){ //rotate status is true
                    rotateIcon(Tile.tiles[i][j]);
                }
                else if(Piece.piecePositions[i][j]!=null&&!Tile.tiles[i][j].getRotationStatus()){ // if rotate status is false set original image
                    Tile.tiles[i][j].setIcon(IconHandler.getIconMap().get(Piece.piecePositions[i][j].getClass()).getIconImg(Piece.piecePositions[i][j].getSide()));;
                }
            }
        }
    }
}
