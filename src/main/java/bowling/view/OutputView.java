package bowling.view;

import bowling.domain.Game;

import java.util.stream.Collectors;

public class OutputView {
    public void showFrames() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public void showGameFrames(Game game) {
        String usernameLabel = String.format("|  %3s | ", game.getUsername());
        String resultFrameLabel = game.getFrames().stream()
                .map((frame) -> frame.toResultString())
                .map((result) -> String.format(" %-3s | ", result))
                .collect(Collectors.joining());

        System.out.println(usernameLabel + resultFrameLabel);
    }
}
