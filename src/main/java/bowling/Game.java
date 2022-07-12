package bowling;

import bowling.Input.InputView;
import bowling.output.ResultView;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Frame> frames = new ArrayList<>();
    ResultView resultView = new ResultView(InputView.question());


    public void play() {
        Frame frame = new NormalFrame();
        frame.throwBall(InputView.settingCount(frame.index));
        frames.add(frame);

        if (frame.isSpare() || frame.isStrike()) {
            play(frame.next());
            return;
        }

        frame.throwBall(InputView.settingCount(frame.index));
        play(frame.next());
    }

    public void play(Frame next) {
        if (next == null) {
            return;
        }

        resultView.print(frames);
        frames.add(next);

        Frame frame = next;
        frame.throwBall(InputView.settingCount(frame.index));

        if (frame.isSpare() || frame.isStrike()) {
            play(frame.next());
            return;
        }

        frame.throwBall(InputView.settingCount(frame.index));
        play(frame.next());
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
