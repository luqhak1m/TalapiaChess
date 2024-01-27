// View

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

    private JPanel gamePanel;
    private static Board theBoard;
    static int row = 6, column = 7;
    private boolean rotate = false;
    private Dimension originalSize;
    private int width = 1000;
    private int height = 800;

    JButton saveGameButton, exitGameButton;

    private Board() {
        super("Talapia Chess");

        gamePanel = new JPanel(new GridLayout(6, 7));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                Tile.tiles[i][j] = new Tile(i, j);
                gamePanel.add(Tile.tiles[i][j]);
            }
        }

        JPanel sideBar = new JPanel();
        saveGameButton = new JButton("Save Game");
        exitGameButton = new JButton("Exit Game");

        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.add(saveGameButton);
        sideBar.add(exitGameButton);
        sideBar.setSize(100, gamePanel.getHeight());
        sideBar.setVisible(true);
        add(gamePanel, BorderLayout.CENTER);
        add(sideBar, BorderLayout.EAST);

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JButton getSaveButton() {
        return saveGameButton;
    }
    public JButton getExiteButton() {
        return exitGameButton;
    }

    public static Board getBoard() {
        if (theBoard == null) {
            theBoard = new Board();
        }
        return theBoard;
    }

    public JPanel getBoardPanel() {
        return gamePanel;
    }
    public Dimension getOriginalSize() {
        return originalSize;
    }

    public int getComponentWidth(){
        return getWidth();
    }
    public int getComponentHeight(){
        return getHeight();
    }
    public int getBoardWidth(){
        return width;
    }
    public int getBoardHeight(){
        return height;
    }

    public void displayBoard() {
        getBoard().setVisible(true);
    }

    public void setRotationStatus(boolean r) {
        rotate = r;
    }

    public void displayMessage(String s){
        JOptionPane.showMessageDialog(this, s);

    }
    public boolean getRotationStatus(){
        return rotate;

    }

    
}