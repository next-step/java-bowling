package bowling.controller;

import java.util.List;
import java.util.stream.IntStream;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.dto.BowlingGameFrameRecord;
import bowling.view.dto.BowlingGameFrameRecordConverter;
import bowling.domain.frame.Frame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    private final InputView inputView;
    private final ResultView resultView;

    public BowlingGameController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        try {
            doRun();
        } catch (Exception e) {
            resultView.printError(e.getMessage());
        }
    }

    private void doRun() {
        Player player = new Player(inputView.getPlayerName());
        BowlingGame bowlingGame = new BowlingGame();
        resultView.printScoreBoard(player.getName(), createFrameRecords(bowlingGame));

        while (bowlingGame.isGamePlayable()) {
            int falledPins = inputView.getFalledPins(bowlingGame.getCurrentFrameNumber());
            bowlingGame.bowl(falledPins);
            resultView.printScoreBoard(player.getName(), createFrameRecords(bowlingGame));
        }
    }

    private List<BowlingGameFrameRecord> createFrameRecords(BowlingGame bowlingGame) {
        List<Frame> frames = bowlingGame.getFrames();
        List<BowlingGameFrameRecord> frameRecords = BowlingGameFrameRecordConverter.convert(frames);

        IntStream.range(frames.size(), Frame.LAST_FRAME)
            .forEach(i -> frameRecords.add(BowlingGameFrameRecordConverter.convertEmptyRecord()));

        return frameRecords;
    }
}
