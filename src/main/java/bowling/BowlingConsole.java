package bowling;

import bowling.dto.ResultDto;
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
        ResultDto resultDto = facade.getResult();
        ResultView.scoreBoard(resultDto);

        int i = 1;
        while (!frames.isEnd()) {
            ResultView.newLine();
            Pin hitCount = Pin.from(InputView.pitch(i++));
            facade.pitch(hitCount);
            resultDto = facade.getResult();
            ResultView.scoreBoard(resultDto);
        }
    }
}
