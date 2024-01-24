
// Main

public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu=MainMenu.getMainMenu();
        
        mainMenu.displayMainMenu();

        GameControl controller=new GameControl();
        controller.instantiatePieces();     
    }
}
