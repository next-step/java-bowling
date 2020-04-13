package bowling;

import bowling.domain.Game;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class JavaBowling {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        String name = inputView.askName();
        Player player = new Player(name);
        Game game = new Game(player);
        OutputView outputView = new OutputView();
        outputView.drawStart(game);
        do{
            int numberOfPin = inputView.askNumberOfPin(game.currentFrame());
            game.play(numberOfPin);
            outputView.drawPlay(game);
        }while(!player.isEndNormalFrame() || !player.isEndFinalFrame());
    }
}
