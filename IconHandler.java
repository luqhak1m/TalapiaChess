
// This Singleton class handles the pairing of each piece with its respective icon.
// Used in: GameControl.java
// Author: Luqman

import java.util.HashMap;

public class IconHandler {

    private static HashMap<Class<? extends Piece>, PieceIcon> pieceIconMap = new HashMap<>();
    private static IconHandler iconHandlerController;

    // Constructor.
    // Written by: Luqman
    private IconHandler(){}

    // Get the Singleton object.
    // Written by: Luqman
    public static IconHandler getIconHandlerController(){
        if(iconHandlerController==null){iconHandlerController=new IconHandler();}
        return iconHandlerController;
    }

    // Get the Map of the pieces and its icons.
    // Written by: Luqman
    public static HashMap<Class<? extends Piece>, PieceIcon> getIconMap(){
        return pieceIconMap;
    }

    // Map each piece's class with its respective icon.
    // Written by: Luqman
    public void mapIcon(){
        pieceIconMap.putIfAbsent(PointPiece.class, new PieceIcon(PointPiece.class, PieceIcon.getImage("piecesPics/yellowArrow.png"), PieceIcon.getImage("piecesPics/blueArrow.png")));
        pieceIconMap.putIfAbsent(PlusPiece.class, new PieceIcon(PlusPiece.class, PieceIcon.getImage("piecesPics/yellowPlus.png"), PieceIcon.getImage("piecesPics/bluePlus.png")));
        pieceIconMap.putIfAbsent(TimePiece.class, new PieceIcon(TimePiece.class, PieceIcon.getImage("piecesPics/yellowTime.png"), PieceIcon.getImage("piecesPics/blueTime.png")));
        pieceIconMap.putIfAbsent(HourglassPiece.class, new PieceIcon(HourglassPiece.class, PieceIcon.getImage("piecesPics/yellowHourglass.png"), PieceIcon.getImage("piecesPics/blueHourglass.png")));
        pieceIconMap.putIfAbsent(SunPiece.class, new PieceIcon(SunPiece.class, PieceIcon.getImage("piecesPics/yellowSun.png"), PieceIcon.getImage("piecesPics/blueSun.png")));
    }
}
