package bowling;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.domain.dto.FrameResultsDto;
import bowling.view.InputView;
import bowling.view.OutputView;

public class GameController {

    public static void main(String[] args) {
        Frames frames = new Frames();
        Player player = InputView.getPlayer();

        OutputView.printFrameResult(new FrameResultsDto(player, frames.results()));

        while (!frames.gameFinished()) {
            frames.bowl(InputView.getNextPin(frames.currentFrameNumber()));

            OutputView.printFrameResult(new FrameResultsDto(player, frames.results()));
        }
    }

}
