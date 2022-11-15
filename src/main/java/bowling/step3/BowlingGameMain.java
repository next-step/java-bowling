package bowling.step3;

import bowling.step3.domain.Player;
import bowling.step3.dto.FramesDto;
import bowling.step3.view.InputView;
import bowling.step3.view.ResultView;

public class BowlingGameMain {

    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        Player player = new Player(name);
        ResultView.printScoreBoard(player.name(), FramesDto.from(player.frames()));

        for (int i = 1; i <= 10; i++) {
            playFrame(player, i);
        }
    }

    private static void playFrame(Player player, int i) {
        while (!player.isEndedFrame(i)) {
            int fallenPinCount = InputView.inputFallenPinCounts(i);
            player.bowl(fallenPinCount);
            ResultView.printScoreBoard(player.name(), FramesDto.from(player.frames()));
        }
    }
}
