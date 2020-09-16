package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.game.Game;
import bowling.domain.player.Player;
import bowling.domain.score.ScoreConverter;
import bowling.domain.view.InputView;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        Game game = new Game(player);
        Frame frame = game.getNextFrame();

        while (frame != null) {
            askPinRoll(frame);
            System.out.println(ScoreConverter.convert(frame));
            frame = game.getNextFrame();

        }
    }

    private static void askPinRoll(Frame frame) {
        int pins = InputView.inputPinRoll(frame.getIndex());
        frame.roll(pins);
    }
}
