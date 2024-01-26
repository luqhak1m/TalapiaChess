
import javax.swing.*;
import java.awt.*;


public class MainMenu extends JFrame{
    
    private static MainMenu mainMenu;
    
    JButton playButton, loadButton, exitButton;

    private MainMenu(){

        setTitle("Talabia Chess - Main Menus");
        setSize(600,600);

        JPanel panel=new JPanel();
        panel.setLayout(new FlowLayout());

        playButton=new JButton("Start a new Game");
        loadButton=new JButton("Load previous Game");
        exitButton=new JButton("Exit");

        panel.add(playButton);
        panel.add(loadButton);
        panel.add(exitButton);
        add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static MainMenu getMainMenu(){
        if(mainMenu==null){
            mainMenu=new MainMenu();
        }
        return mainMenu;
    }

    public JButton getPlayButton(){
        return playButton;
    }
    public JButton getLoadButton(){
        return loadButton;
    }
    public JButton getExitButton(){
        return exitButton;
    }

    public void displayMainMenu(){
        setVisible(true);
    }
}