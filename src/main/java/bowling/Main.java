package bowling;

import bowling.domain.Player;
import bowling.domain.Score;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(InputView.scanName());
        for (int i = 0; i < 10; i++) {
            startFrame(player, i);
        }
    }

    private static void startFrame(Player player, int index) {
        while (player.process(index)) {
            Score score = new Score(InputView.scanScore(index));
            player.pitch(index, score);
            OutputView.printScores(player);
        }
    }
}
