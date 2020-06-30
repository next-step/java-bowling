package bowling.view;

import bowling.domain.BowlingGameResult;
import bowling.domain.Player;
import bowling.domain.frame.FrameResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final int FRAME_COUNT = 10;

    private final FrameBowlView frameBowlView = new FrameBowlView();
    private final FrameScoreView frameScoreView = new FrameScoreView();

    public void printResult(List<Player> players, BowlingGameResult bowlingGameResult) {
        printFramesRounds();

        for (int playerPosition = 0; playerPosition < players.size(); playerPosition++) {
            frameBowlView.printBowlsResult(players.get(playerPosition),
                bowlingGameResult.get(playerPosition));
            frameScoreView.printScores(
                bowlingGameResult.get(playerPosition).stream().map(FrameResult::getScore)
                    .collect(Collectors.toList()));

        }
        System.out.print(System.lineSeparator());
    }

    private void printFramesRounds() {
        StringBuilder framesBuilder = new StringBuilder();
        framesBuilder.append("| NAME |");

        for (int i = 0; i < FRAME_COUNT; i++) {
            framesBuilder.append(String.format("  %02d  |", i + 1));
        }
        System.out.println(framesBuilder.toString());
    }

}
