
import java.awt.event.*;

public class ClickHandler {

    private Board board=Board.getBoard();

    private GameControl gameControl;


    public ClickHandler(GameControl gameControl1){
        //  public ClickHandler(CurrentState sControl, Gameplay gControl, PieceMovement pMove)

        this.gameControl=gameControl1;
    }

    public void addListener(){
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                Tile tile=Tile.tiles[i][j];
                tile.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e){
                        gameControl.clickTile(tile.xCoord, tile.yCoord);
                    }
                });
            }
        }

        board.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameControl.saveState();
                gameControl.saveGame();
                // JOptionPane.showMessageDialog( Board.this, "Game saved!");
            }
        });

        MainMenu.getMainMenu().getPlayButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                MainMenu.getMainMenu().setVisible(false);
                Board.getBoard().displayBoard();
            }
        });

        MainMenu.getMainMenu().getLoadButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                MainMenu.getMainMenu().setVisible(false);
                gameControl.loadGame();
            }
        });
        MainMenu.getMainMenu().getExitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }

    
}
