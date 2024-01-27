// View

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Board extends JFrame {

    private JPanel gamePanel;
    private static Board theBoard;
    static int row = 6, column = 7;
    private boolean rotate = false;
    private Dimension originalSize;
    private int width = 1000;
    private int height = 800;

    JButton saveGameButton, seeSaveFilesButton;

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
        seeSaveFilesButton = new JButton("See all saved files");
        JButton exitGame = new JButton("Exit Game");

        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.add(saveGameButton);
        sideBar.add(seeSaveFilesButton);
        sideBar.add(exitGame);
        sideBar.setSize(100, gamePanel.getHeight());
        sideBar.setVisible(true);
        add(gamePanel, BorderLayout.CENTER);
        add(sideBar, BorderLayout.EAST);

        gamePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (gamePanel.getSize() != originalSize) {
                    resizeToOriginal();
                }
            }
        });

        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JButton getSaveButton() {
        return saveGameButton;
    }

    public JButton getSavedFiles() {
        return seeSaveFilesButton;
    }

    public void resizeToOriginal() {
        int x = getWidth();
        int y = getHeight();
        int newX = width;
        int newY = height;

        if ((x > 0 && x < 400) || (y > 0 && y < 200)) {
            setSize(400, 200);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    Tile.tiles[i][j].resizeImages(25, 25);
                }
            }

        } else if ((x > 400 && x < 600) || (y > 200 && y < 400)) {
            setSize(600, 400);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    Tile.tiles[i][j].resizeImages(65, 65);
                }
            }
        } else if ((x > 600 && x < 800) || (y > 400 && y < 600)) {
            setSize(800, 600);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    Tile.tiles[i][j].resizeImages(95, 95);
                }
            }
        } else if ((x > 800 && x < 1550) || (y > 600 && y < 800)) {
            setSize(1000, 800);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    Tile.tiles[i][j].resizeImages(130, 130);
                }
            }
        } else if ((x > 1550) || (y > 800)) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    Tile.tiles[i][j].resizeImages(175, 175);
                }
            }
        }

        if ((newX != x) || (newY != y)){
        JOptionPane.showMessageDialog(this, "Frame Size Optimized!");
        }

    }

    public static Board getBoard() {
        if (theBoard == null) {
            theBoard = new Board();
        }
        return theBoard;
    }

    public void displayBoard() {
        getBoard().setVisible(true);
    }

    public void flipBoard() {

        gamePanel.removeAll();

        if (!rotate) {
            for (int i = row - 1; i >= 0; i--) {
                for (int j = column - 1; j >= 0; j--) {
                    gamePanel.add(Tile.tiles[i][j]);
                    rotate = true;
                }
            }
        } else {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    gamePanel.add(Tile.tiles[i][j]);
                    rotate = false;
                }
            }
        }

        gamePanel.revalidate();
        gamePanel.repaint();
    }

    public void setRotationStatus(boolean r) {
        rotate = r;
    }

    public void displayInvalidMove(){
        JOptionPane.showMessageDialog(this, "Invalid Move!");

    }

}