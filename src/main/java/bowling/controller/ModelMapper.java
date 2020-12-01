package bowling.controller;

import bowling.domain.Game;
import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.Roll;
import bowling.view.InputView;
import bowling.view.OutputView;

class ModelMapper {
    private ModelMapper() {}

    static Game getGame() {
        Game game = new Game();
        game.registerScoreBoardPrinter(OutputView::printScoreBoard);
        getPlayers().addToGame(game, frameNo -> ModelMapper.getRoll(frameNo));
        return game;
    }

    private static Players getPlayers() {
        return Players.of(
//                InputView.askSizeOfPlayers().getSize(),
                1,
                ModelMapper::getPlayer
        );
    }

    private static Player getPlayer() {
        return new Player(InputView.askName()
                .getName()
        );
    }

    private static Roll getRoll(int frameNo) {
        return Roll.of(InputView.askCountOfPins(frameNo)
                .getCountOfPins());
    }
}
