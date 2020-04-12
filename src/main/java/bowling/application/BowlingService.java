package bowling.application;

import bowling.domain.frame.Bowling;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.player.Player;

import java.util.LinkedList;
import java.util.Objects;

public class BowlingService {

    private static final int START_FRAME_NUMBER = 1;

    private LinkedList<Frame> frames;

    public BowlingService() {
        this.frames = new LinkedList<>();
        this.frames.add(new NormalFrame(START_FRAME_NUMBER));
    }

    public Bowling bowl(Request request) {
        Bowling bowling = new Bowling(frames, new Player(request.getName()));
        if (Objects.isNull(request.getPin())) {
            return bowling;
        }
        bowling.bowl(request.getPin());
        return bowling;
    }
}
