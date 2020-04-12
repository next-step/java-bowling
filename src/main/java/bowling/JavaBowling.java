package bowling;

import bowling.domain.Game;
import bowling.domain.Player;
import bowling.view.InputView;

public class JavaBowling {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        String name = inputView.askName();
        Player player = new Player(name);
        Game game = new Game(player);
        do{
            int numberOfPin = inputView.askNumberOfPin(game.currentFrame());
            game.play(numberOfPin);

        }while(!player.isEndNormalFrame() || !player.isEndFinalFrame());
    }
}
