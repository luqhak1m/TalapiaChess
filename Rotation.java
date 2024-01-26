
import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Rotation {

    public Rotation(){}

    public void setPointPieceRotation(Piece piece){
        if(piece instanceof PointPiece){
            PointPiece pointPiece=(PointPiece) piece;
            if(pointPiece.getReversedB()||pointPiece.getReversedY()){
                Tile.getTileAtCoordinate(piece.getPosX(), piece.getPosY()).setTileRotationStatus(true);
            }
        }
    }

    public void rotateThePiece(){
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                if(Piece.piecePositions[i][j]!=null && Tile.tiles[i][j].getRotationStatus()){
                    rotateIcon(Tile.getTileAtCoordinate(i, j));
                }
                else if(Piece.piecePositions[i][j]!=null&&!Tile.tiles[i][j].getRotationStatus()){ // if rotate status is false set original image
                    Tile.getTileAtCoordinate(i, j).setIcon(IconHandler.getIconMap().get(Piece.piecePositions[i][j].getClass()).getIconImg(Piece.piecePositions[i][j].getSide()));;
                }
            }
        }
    }

    public void rotateIcon(Tile t) {
        
        if(t.getRotationStatus()){
            Icon icon = t.getDefaultImg();
            if (icon instanceof ImageIcon) {
                ImageIcon imageIcon = (ImageIcon) icon;
                Image image = imageIcon.getImage();
                Image rotatedImage = rotateImage(image, 180); // Rotate by 180 degrees
                Image resizedImage=rotatedImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                t.setRotatedImg(new ImageIcon(resizedImage));
                t.setRotatedIconAtTile();
            }
        }else{
            t.setIconAtTile();
        }
    }

    private Image rotateImage(Image image, int degrees) {
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
}
