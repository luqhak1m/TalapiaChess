
// Main

public class Main {
    public static void main(String[] args) {
        GameControl controller = new GameControl();
        new Board(controller);

        controller.initializePiece("PlusPiece", 5, 0, 'A', 'Y', "piecesPics/yellowPlus.png");
        controller.initializePiece("PlusPiece", 5, 6, 'A', 'Y', "piecesPics/yellowPlus.png");
        controller.initializePiece("PlusPiece", 0, 0, 'A', 'B', "piecesPics/bluePlus.png");
        controller.initializePiece("PlusPiece", 0, 6, 'A', 'B', "piecesPics/bluePlus.png");
        controller.initializePiece("HourglassPiece", 5, 1, 'A', 'Y', "piecesPics/yellowHourglass.png");
        controller.initializePiece("HourglassPiece", 5, 5, 'A', 'Y', "piecesPics/yellowHourglass.png");
        controller.initializePiece("HourglassPiece", 0, 1, 'A', 'B', "piecesPics/blueHourglass.png");
        controller.initializePiece("HourglassPiece", 0, 5, 'A', 'B', "piecesPics/blueHourglass.png");
        controller.initializePiece("TimePiece", 5, 2, 'A', 'Y', "piecesPics/yellowTime.png");
        controller.initializePiece("TimePiece", 5, 4, 'A', 'Y', "piecesPics/yellowTime.png");
        controller.initializePiece("TimePiece", 0, 2, 'A', 'B', "piecesPics/blueTime.png");
        controller.initializePiece("TimePiece", 0, 4, 'A', 'B', "piecesPics/blueTime.png");
        controller.initializePiece("SunPiece", 5, 3, 'A', 'Y', "piecesPics/yellowSun.png");
        controller.initializePiece("SunPiece", 0, 3, 'A', 'B', "piecesPics/blueSun.png");
        for (int column = 0; column < 7; column++) {
            controller.initializePiece("PointPiece", 4, column, 'A', 'Y', "piecesPics/yellowArrow.png");
            controller.initializePiece("PointPiece", 1, column, 'A', 'B', "piecesPics/blueArrow.png");
        }
    }
}
