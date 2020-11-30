package bowling.controller;

import bowling.domain.Game;
import bowling.view.OutputView;

import java.util.stream.IntStream;

public class Main {
    private Main() {}

    public static void main(String[] args) {
        int sizeOfPlayers = 1;
        Game game = new Game();
        IntStream.range(0, sizeOfPlayers)
                .forEach(i -> game.addPlayer(
                        ModelMapper.getPlayer(),
                        frameNo -> ModelMapper.getRoll(frameNo)
                ));
        game.play();
        OutputView.printScoreBoard(
                game.exportScoreBoardDto()
        );
    }
}
