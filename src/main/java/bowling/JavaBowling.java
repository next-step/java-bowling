package bowling;

import bowling.domain.Game;
import bowling.domain.Player;
import bowling.domain.Score;
import bowling.view.InputView;
import bowling.view.OutputView;

public class JavaBowling {
    private static InputView inputView = new InputView();
    private static OutputView outputView = new OutputView();
    private static Game game = new Game();

    public static void main(String[] args) {
        int number = inputView.askNumberOfPeople();
        askName(number);
        outputView.drawStart(game, number);
        do{
            askNumberOfPin(number);
        }while(!game.isEndGame());
    }

    private static void askName(int number) {
        for (int i = 0; i < number; i++) {
            String name = inputView.askName(i);
            Player player = new Player(name);
            game.add(player);
        }
    }

    private static void askNumberOfPin(int number) {
        for (int i = 0; i < number ; i++) {
            numberOfEachPlayer(number, i);
        }
    }

    private static void numberOfEachPlayer(int number, int index) {
        Player player;
        do{
            player = game.getPlay(index);
            Score numberOfPin = new Score(inputView.askNumberOfPin(player.getName()));
            game.play(numberOfPin, index);
            outputView.drawPlay(game, number);
        }while(!player.isNextFrame());
    }
}
