package bowling.contorller;

import bowling.view.InputView;

public class GameController {

    private final Player player;
    private final BowlingGame bowlingGame;


    public GameController(String playerName) {
        this.player = Player.of(playerName);
        this.bowlingGame = BowlingGame.of();
    }


    public static void main(String[] args) {
        String playerName = InputView.inputPlayer();
        GameController gameController = new GameController(playerName);
    }
}
