
// View
// Main Menu. Singleton is to prevent multiple Mian Menus.
// Author: Asyrani, Luqman

import javax.swing.*;
import java.awt.*;


public class MainMenu extends JFrame{
    
    private static MainMenu mainMenu;
    
    JButton playButton, loadButton, exitButton;

    // Getters and SettersConstructor
    // Written by: Asyrani
    private MainMenu(){

        setTitle("Talabia Chess - Main Menu");
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

    // Get Singleton object.
    // Written by: Luqman
    public static MainMenu getMainMenu(){
        if(mainMenu==null){
            mainMenu=new MainMenu();
        }
        return mainMenu;
    }

    // Getters.
    // Written by: Asyrani
    public JButton getPlayButton(){
        return playButton;
    }
    public JButton getLoadButton(){
        return loadButton;
    }
    public JButton getExitButton(){
        return exitButton;
    }

    // Display main menu.
    // Written by: Luqman
    public void displayMainMenu(){
        setVisible(true);
    }
}