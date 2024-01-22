
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JFrame{
    Board board;

    public MainMenu(Board b){
        this.board=b;

        setTitle("Talabia Chess - Main Menus");
        setSize(600,600);

        JPanel panel=new JPanel();
        panel.setLayout(new FlowLayout());

        JButton playButton=new JButton("Start a new Game");
        JButton loadButton=new JButton("Load previous Game");
        JButton exitButton=new JButton("Exit");

        panel.add(playButton);
        panel.add(loadButton);
        panel.add(exitButton);
        add(panel);

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                startNewGame();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Nothing");
            }
        });
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void displayMainMenu(){
        setVisible(true);
    }

    public void startNewGame(){
        this.setVisible(false);
        board.displayBoard();
    }
}