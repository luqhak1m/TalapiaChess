
// Every event that happens when clicking a button will be declared here.
// Used in: GameControl.java
// Authors: Asyrani, Luqman

import java.awt.event.*;

public class ClickHandler {

    private Board board=Board.getBoard();
    private GameControl gameControl;

    // Setting Facade class.
    // Written by: Luqman
    public ClickHandler(GameControl gameControl){
        this.gameControl=gameControl;
    }

    // Add listener to each buttons in Main Menu and Board.
    // Written by: Asyrani
    public void addListener(){
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                Tile tile=Tile.tiles[i][j];
                int x=i;
                int y=j;
                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        gameControl.clickTile(x, y);
                    }
                });
            }
        }

        board.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameControl.saveState();
                gameControl.saveGame();
            }
        });

        board.exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        MainMenu.getMainMenu().getPlayButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                MainMenu.getMainMenu().setVisible(false);
                board.displayBoard();
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

        Board.getBoard().getBoardPanel().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                gameControl.resizeBoard();
                for(int i=0; i<Board.row; i++){
                    for(int j=0; j<Board.column; j++){
                        if(Piece.piecePositions[i][j]!=null){
                            gameControl.setPieceAtTile(Piece.piecePositions[i][j]);
                        }
                    }
                }
            }
        });
    }
}
