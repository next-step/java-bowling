package bowling.view;

import bowling.domain.Game;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutputView {
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
}
