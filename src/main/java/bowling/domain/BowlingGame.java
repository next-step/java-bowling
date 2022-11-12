package bowling.domain;

import bowling.ui.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGame {

    private static final int GAME_BEGIN_ROUND_NUMBER = 1;
    private static final int GAME_END_ROUND_NUMBER = 9;
    private final List<Frame> frameList;


    public BowlingGame() {
        this.frameList = initFrameList();
    }

    private List<Frame> initFrameList() {
        List<Frame> frames = IntStream.rangeClosed(GAME_BEGIN_ROUND_NUMBER, GAME_END_ROUND_NUMBER)
                .boxed()
                .map(NormalFrame::new)
                .collect(Collectors.toList());
        FinalFrame finalFrame = new FinalFrame(10);
        frames.add(finalFrame);

        return frames;
    }

    public void play(UserName userName) {
        List<String> playResult = new ArrayList<>();
        frameList.stream()
                .forEach(frame -> {
                    frame.play();
                    playResult.add(frame.scoringText());
                    ResultView.printRoundResult(userName, playResult, frame.score());
                });
    }

}
