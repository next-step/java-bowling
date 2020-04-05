package bowling.application;

import bowling.domain.frame.Bowling;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

import java.util.LinkedList;
import java.util.Objects;

public class BowlingService {

    private LinkedList<Frame> bowlFrames;

    public BowlingService() {
        this.bowlFrames = new LinkedList<>();
        this.bowlFrames.add(new Frame(1));
    }

    public Frames bowl(Request request) {
        Frames frames = new Frames(bowlFrames, new Player(request.getName()));
        if (Objects.nonNull(request.getFallenPins())) {
            frames.bowl(request.getFallenPins());
        }
        return frames;
    }

    public Bowling bowl2(Request request) {
        Bowling bowling = new Bowling(new Player(request.getName()));
        bowling.bowl(request.getFallenPins());
        return bowling;
    }
}
