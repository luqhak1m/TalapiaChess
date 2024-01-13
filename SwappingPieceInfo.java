public class SwappingPieceInfo {
    Class<? extends Piece> pieceType;
    String yellowIcon, blueIcon;

    public SwappingPieceInfo(Class<? extends Piece> p, String y, String b){
        this.pieceType=p;
        this.yellowIcon=y;
        this.blueIcon=b;
    }

    public String getYellow(){
        return this.yellowIcon;
    }
    public String getBlue(){
        return this.blueIcon;
    }
    public Class<? extends Piece> getPieceType(){
        return this.pieceType;
    }
}