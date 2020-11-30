package bowling.controller;

import bowling.domain.Game;
import bowling.view.OutputView;

public class Main {
    private Main() {}

    public static void main(String[] args) {
        int sizeOfPlayers = 1;
        Game game = new Game();
        game.registerScoreBoardPrinter(OutputView::printScoreBoard);
        for (int i = 0; i < sizeOfPlayers; i++) {
            game.addPlayer(
                    ModelMapper.getPlayer(),
                    frameNo -> ModelMapper.getRoll(frameNo)
            );
        }
        game.play();
    }
}
