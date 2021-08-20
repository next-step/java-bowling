package bowling;

import bowling.frame.Frames;
import bowling.pin.Pin;
import bowling.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingConsole {
    public static void main(String[] args) {
        String name = InputView.playerName();
        Player player = Player.init(name);
        Frames frames = Frames.init();
        BowlingFacade facade = BowlingFacade.of(player, frames);

        int i = 1;
        while (!frames.isEnd()) {
            ResultView.scoreBoard(name);
            Pin hitCount = Pin.from(InputView.pitch(i++));
            frames.pitch(hitCount);
        }
    }
}
