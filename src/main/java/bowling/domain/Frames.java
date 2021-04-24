package bowling.domain;

import bowling.domain.frame.Frame;import bowling.domain.frame.NormalFrame;import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Frames {
    private List<Frame> frames;
    private Round round;

    public Frames(final Round round) {
        this.frames = new ArrayList<>();
        this.round = round;
    }

    public static Frames newInstance() {
        return new Frames(Round.from(Round.BOWLING_MIN_ROUND));
    }

    public void init() {
        this.frames.add(NormalFrame.newInstance());
    }

    public boolean isPlayable() {
        if (frames.size() < Round.BOWLING_MAX_ROUND) {
            return true;
        }
        return !frames.get(Round.BOWLING_MAX_ROUND - 1).isEnd();
    }

    public void play(final int countOfDownPin) {
        Frame frame = frames.get(round.getRound() - 1);
        frame.play(countOfDownPin);

        if (frame.isNextFrame()) {
            this.round = round.next();
            this.frames.add(frame.next(round));
        }
    }

    public int getRound() {
        return round.getRound();
    }

    public List<Frame> getProgressFrames() {
        return frames.stream()
                .filter(frame -> frame.getTotalPinCount() > 0)
                .collect(toList());
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
