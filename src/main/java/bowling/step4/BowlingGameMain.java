package bowling.step4;

import bowling.step4.domain.BowlingGame;
import bowling.step4.domain.Player;
import bowling.step4.dto.FramesDto;
import bowling.step4.view.InputView;
import bowling.step4.view.ResultView;

import java.util.List;

public class BowlingGameMain {

    public static void main(String[] args) {
        int playerCount = InputView.inputPlayerCount();
        List<String> names = InputView.inputPlayerName(playerCount);
        BowlingGame bowlingGame = new BowlingGame(names);
        ResultView.printScoreBoard(bowlingGame.createResult());

        for (int i = 1; i <= 10; i++) {
            // playFrame(player, i);
        }
    }

    private static void playFrame(Player player, int i) {
        while (!player.isEndedFrame(i)) {
            int fallenPinCount = InputView.inputFallenPinCounts(i);
            player.bowl(i, fallenPinCount);
            //ResultView.printScoreBoard(player.name(), FramesDto.from(player.frames()));
        }
    }
}
