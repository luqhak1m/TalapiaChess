public class Main {
    public static void main(String[] args) {
        new Board();

        String[] blueFirstRow={"bluePlus.png", "blueHourglass.png", "blueTime.png", "blueSun.png", "blueTime.png", "blueHourglass.png", "bluePlus.png"};
        String[] blueSecRow={"blueArrow.png"};
        String[] yellowFirstRow={"yellowArrow.png"};
        String[] yellowSecRow={"yellowPlus.png", "yellowHourglass.png", "yellowTime.png", "yellowSun.png", "yellowTime.png", "yellowHourglass.png", "yellowPlus.png"};

        PlusPiece yellowPlus1=new PlusPiece(5, 0);
        PlusPiece yellowPlus2=new PlusPiece(5, 6);
        PlusPiece bluePlus1=new PlusPiece(0, 0);
        PlusPiece bluePlus2=new PlusPiece(0, 6);

        HourglassPiece yellowHourglass1=new HourglassPiece(5, 1);
        HourglassPiece yellowHourglass2=new HourglassPiece(5, 5);
        HourglassPiece blueHourglass1=new HourglassPiece(0, 1);
        HourglassPiece blueHourglass2=new HourglassPiece(0, 5);
        
        TimePiece yellowTime1=new TimePiece(5, 2);
        TimePiece yellowTime2=new TimePiece(5, 4);
        TimePiece blueTime1=new TimePiece(0, 2);
        TimePiece blueTime2=new TimePiece(0, 4);

        SunPiece yellowSun=new SunPiece(5, 3);
        SunPiece blueSun=new SunPiece(0, 3);

        for(int row=1; row<5; row=row+3){
            for(int column=0; column<7; column++){
                if(row==1){
                    PointPiece yellowPoint=new PointPiece(row, column);
                }else{
                    PointPiece bluePoint=new PointPiece(row, column);   
                }
            }
        }

        PieceIcon.setIcons();

    }
}
