package bowling;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.domain.ResultLines;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.Optional;

public class GameController {

    public static void main(String[] args) {
        Frames frames = new Frames();
        Player player = InputView.getPlayer();

        OutputView.printFrameResult(new ResultLines(player, frames.results()));

        while (!frames.gameFinished()) {
            frames.bowl(InputView.getNextPin(frames.currentFrameNumber()));

            // TODO ResultDTO Frames에서 생성하도록 변경하기
            OutputView.printFrameResult(new ResultLines(player, frames.results()));
            for (Frame f : frames.results()) {
                Optional<Integer> score = f.calculateScore();
                score.ifPresent(System.out::println);
            }
        }
    }

}
