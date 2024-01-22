
// Main

public class Main {
    public static void main(String[] args) {
        Board board=Board.getBoard();
        MainMenu mainMenu=new MainMenu(board);
        
        mainMenu.displayMainMenu();

        GameControl controller=new GameControl();
        controller.instantiatePieces();     
    }
}
