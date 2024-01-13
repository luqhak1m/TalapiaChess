
// Main

public class Main {
    public static void main(String[] args) {
        GameControl controller=new GameControl();
        Board board=new Board(controller);
        MainMenu mainMenu=new MainMenu(board);
        mainMenu.displayMainMenu();
        controller.instantiatePieces();     
    }
}
