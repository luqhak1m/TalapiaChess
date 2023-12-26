public class Main {
    public static void main(String[] args) {
        GameControl controller=new GameControl();
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
        for (int column = 0; column < 7; column++) {
            yellowPoints[column] = new PointPiece(4, column);
            bluePoints[column] = new PointPiece(1, column);
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
        for (int column=0; column<7; column++) {
            blueArrowIcons[column] = new PieceIcon(bluePoints[column], PieceIcon.getImage("piecesPics/blueArrow.png"));
            yellowArrowIcons[column] = new PieceIcon(yellowPoints[column], PieceIcon.getImage("piecesPics/yellowArrow.png"));
        }
        PieceIcon yellowPlus1Icon = new PieceIcon(yellowPlus1, PieceIcon.getImage("piecesPics/yellowPlus.png"));
        PieceIcon yellowPlus2Icon = new PieceIcon(yellowPlus2, PieceIcon.getImage("piecesPics/yellowPlus.png"));
        PieceIcon yellowHourglass1Icon = new PieceIcon(yellowHourglass1, PieceIcon.getImage("piecesPics/yellowHourglass.png"));
        PieceIcon yellowHourglass2Icon = new PieceIcon(yellowHourglass2, PieceIcon.getImage("piecesPics/yellowHourglass.png"));
        PieceIcon yellowTime1Icon = new PieceIcon(yellowTime1, PieceIcon.getImage("piecesPics/yellowTime.png"));
        PieceIcon yellowTime2Icon = new PieceIcon(yellowTime2, PieceIcon.getImage("piecesPics/yellowTime.png"));
        PieceIcon yellowSunIcon = new PieceIcon(yellowSun, PieceIcon.getImage("piecesPics/yellowSun.png"));

        controller.mapPiecesIcon(bluePlus1, bluePlus1Icon);
        controller.mapPiecesIcon(bluePlus2, bluePlus2Icon);
        controller.mapPiecesIcon(blueHourglass1, blueHourglass1Icon);
        controller.mapPiecesIcon(blueHourglass2, blueHourglass2Icon);
        controller.mapPiecesIcon(blueTime1, blueTime1Icon);
        controller.mapPiecesIcon(blueTime2, blueTime2Icon);
        controller.mapPiecesIcon(blueSun, blueSunIcon);
        for (int column = 0; column < 7; column++) {
            controller.mapPiecesIcon(bluePoints[column], blueArrowIcons[column]);
        }

        controller.mapPiecesIcon(yellowPlus1, yellowPlus1Icon);
        controller.mapPiecesIcon(yellowPlus2, yellowPlus2Icon);
        controller.mapPiecesIcon(yellowHourglass1, yellowHourglass1Icon);
        controller.mapPiecesIcon(yellowHourglass2, yellowHourglass2Icon);
        controller.mapPiecesIcon(yellowTime1, yellowTime1Icon);
        controller.mapPiecesIcon(yellowTime2, yellowTime2Icon);
        controller.mapPiecesIcon(yellowSun, yellowSunIcon);
        for (int column = 0; column < 7; column++) {
            controller.mapPiecesIcon(yellowPoints[column], yellowArrowIcons[column]);
        }

        // Setting the icons for each Piece on the board
        controller.setPiecesIcon(bluePlus1);
        controller.setPiecesIcon(bluePlus2);
        controller.setPiecesIcon(blueHourglass1);
        controller.setPiecesIcon(blueHourglass2);
        controller.setPiecesIcon(blueTime1);
        controller.setPiecesIcon(blueTime2);
        controller.setPiecesIcon(blueSun);
        for (int column = 0; column < 7; column++) {
            controller.setPiecesIcon(bluePoints[column]);
        }

        controller.setPiecesIcon(yellowPlus1);
        controller.setPiecesIcon(yellowPlus2);
        controller.setPiecesIcon(yellowHourglass1);
        controller.setPiecesIcon(yellowHourglass2);
        controller.setPiecesIcon(yellowTime1);
        controller.setPiecesIcon(yellowTime2);
        controller.setPiecesIcon(yellowSun);
        for (int column = 0; column < 7; column++) {
            controller.setPiecesIcon(yellowPoints[column]);
        }
    }
}
