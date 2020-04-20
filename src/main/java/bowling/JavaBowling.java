package bowling;

import bowling.domain.Game;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class JavaBowling {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        int number = inputView.askNumberOfPeople();
        Game game = new Game();
        Player player = null;
        askName(inputView, number, game);
        OutputView outputView = new OutputView();
        outputView.drawStart(game, number);
        do{
            for (int i = 0; i < number ; i++) {
                do{
                    player = game.getPlay(i);
                    int numberOfPin = inputView.askNumberOfPin(player.getName());
                    game.play(numberOfPin, i);
                    outputView.drawPlay(game, number);
                }while(!player.isNextFrame());

            }
        }while(!game.isEndGame());
    }

    private static void askName(InputView inputView, int number, Game game) {
        Player player;
        for (int i = 0; i < number; i++) {
            String name = inputView.askName(i);
            player = new Player(name);
            game.add(player);
        }
    }
}
