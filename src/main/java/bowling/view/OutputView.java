package bowling.view;

import bowling.domain.Game;
import bowling.domain.ScoreBoard;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutputView {
    private static final String EMPTY_FRAME = "|      | ";
    private static final int MAX_FRAME_SIZE = 10;

    public static void showFrames() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void showGameFrames(Game game) {
        String usernameLabel = String.format("|  %3s | ", game.getUser());
        String resultFrameLabel = game.getFrames().stream()
                .map((frame) -> frame.toResultString())
                .map((result) -> String.format(" %-3s | ", result))
                .collect(Collectors.joining());

        String emptyFrameLabel = Stream.generate(() -> "     | ")
                .limit(game.getRemainFrameCount())
                .collect(Collectors.joining());


        System.out.println(usernameLabel + resultFrameLabel + emptyFrameLabel);
    }

    public static void showScoreFrames(ScoreBoard scoreBoard) {
        List<Integer> scoreList = scoreBoard.currentScoreList();

        String resultFrameLabel = scoreList.stream()
                .map((score) -> String.format(" %-3s | ", score))
                .collect(Collectors.joining());

        String emptyFrameLabel = Stream.generate(() -> "     | ")
                .limit(MAX_FRAME_SIZE - scoreList.size())
                .collect(Collectors.joining());

        System.out.println(EMPTY_FRAME + resultFrameLabel + emptyFrameLabel);
    }
}
