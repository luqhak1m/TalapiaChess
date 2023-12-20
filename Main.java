public class Main {
    public static void main(String[] args) {
        new Board();

        PlusPiece yellowPlus1 = new PlusPiece(5, 0);
        PlusPiece yellowPlus2 = new PlusPiece(5, 6);
        PlusPiece bluePlus1 = new PlusPiece(0, 0);
        PlusPiece bluePlus2 = new PlusPiece(0, 6);
        HourglassPiece yellowHourglass1 = new HourglassPiece(5, 1);
        HourglassPiece yellowHourglass2 = new HourglassPiece(5, 5);
        HourglassPiece blueHourglass1 = new HourglassPiece(0, 1);
        HourglassPiece blueHourglass2 = new HourglassPiece(0, 5);
        TimePiece yellowTime1 = new TimePiece(5, 2);
        TimePiece yellowTime2 = new TimePiece(5, 4);
        TimePiece blueTime1 = new TimePiece(0, 2);
        TimePiece blueTime2 = new TimePiece(0, 4);
        SunPiece yellowSun = new SunPiece(5, 3);
        SunPiece blueSun = new SunPiece(0, 3);
        PointPiece[] yellowPoints = new PointPiece[7];
        PointPiece[] bluePoints = new PointPiece[7];
        for (int col = 0; col < 7; col++) {
            yellowPoints[col] = new PointPiece(4, col);
            bluePoints[col] = new PointPiece(1, col);
        }

        PieceIcon bluePlus1Icon = new PieceIcon(bluePlus1, PieceIcon.getImage("piecesPics/bluePlus.png"));
        PieceIcon bluePlus2Icon = new PieceIcon(bluePlus2, PieceIcon.getImage("piecesPics/bluePlus.png"));
        PieceIcon blueHourglass1Icon = new PieceIcon(blueHourglass1, PieceIcon.getImage("piecesPics/blueHourglass.png"));
        PieceIcon blueHourglass2Icon = new PieceIcon(blueHourglass2, PieceIcon.getImage("piecesPics/blueHourglass.png"));
        PieceIcon blueTime1Icon = new PieceIcon(blueTime1, PieceIcon.getImage("piecesPics/blueTime.png"));
        PieceIcon blueTime2Icon = new PieceIcon(blueTime2, PieceIcon.getImage("piecesPics/blueTime.png"));
        PieceIcon blueSunIcon = new PieceIcon(blueSun, PieceIcon.getImage("piecesPics/blueSun.png"));
        PieceIcon[] blueArrowIcons = new PieceIcon[7];
        PieceIcon[] yellowArrowIcons = new PieceIcon[7];
        for (int col = 0; col < 7; col++) {
            blueArrowIcons[col] = new PieceIcon(bluePoints[col], PieceIcon.getImage("piecesPics/blueArrow.png"));
            yellowArrowIcons[col] = new PieceIcon(yellowPoints[col], PieceIcon.getImage("piecesPics/yellowArrow.png"));
        }
        PieceIcon yellowPlus1Icon = new PieceIcon(yellowPlus1, PieceIcon.getImage("piecesPics/yellowPlus.png"));
        PieceIcon yellowPlus2Icon = new PieceIcon(yellowPlus2, PieceIcon.getImage("piecesPics/yellowPlus.png"));
        PieceIcon yellowHourglass1Icon = new PieceIcon(yellowHourglass1, PieceIcon.getImage("piecesPics/yellowHourglass.png"));
        PieceIcon yellowHourglass2Icon = new PieceIcon(yellowHourglass2, PieceIcon.getImage("piecesPics/yellowHourglass.png"));
        PieceIcon yellowTime1Icon = new PieceIcon(yellowTime1, PieceIcon.getImage("piecesPics/yellowTime.png"));
        PieceIcon yellowTime2Icon = new PieceIcon(yellowTime2, PieceIcon.getImage("piecesPics/yellowTime.png"));
        PieceIcon yellowSunIcon = new PieceIcon(yellowSun, PieceIcon.getImage("piecesPics/yellowSun.png"));
        

        Piece.getPiecesPositionAtIndex(0, 0).setPosXY(5, 6); //
        PieceIcon.piecesIcons[0][0].setIconAtPosition(0,0);
    }
}
