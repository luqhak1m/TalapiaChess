
// factory method
public class CreatePiece{
    public static Piece createPiece(String type, int x, int y, char status, char side){
        switch(type){
            case "PlusPiece":
                return new PlusPiece(x, y, status, side);
            case "TimePiece":
                return new TimePiece(x, y, status, side);
            case "SunPiece":
                return new SunPiece(x, y, status, side);
            case "HourglassPiece":
                return new HourglassPiece(x, y, status, side);
            case "PointPiece":
                return new PointPiece(x, y, status, side);
            default: 
                throw new IllegalArgumentException(type + " is not a valid piece");
        }
    }
}