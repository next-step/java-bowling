package bowling.frame.domain;

import bowling.pin.domain.Pin;
import bowling.player.domain.Player;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Board {

    public static final int END_FRAME_COUNT = 10;
    private static final int NORMAL_FRAME_COUNT = 9;

    private final ScorePublisher publisher = new ScorePublisher();
    private final Player player;
    private final Deque<Frame> frames;

    private Board(Player player, Deque<Frame> frames) {
        this.frames = frames;
        this.player = player;
    }

    public static Board init(Player player) {
        return new Board(player, new LinkedList<>(Arrays.asList(Frame.ofNormal())));
    }

    public void roll(Pin felled) {
        Frame current = frames.getLast();
        current.roll(felled);
        publisher.update(felled);
        if (current.isEnd() && !isGameOver()) {
            generate();
        }
    }

    private void generate() {
        ((NormalFrame) frames.getLast()).createScore(publisher);
        if (frames.size() < NORMAL_FRAME_COUNT) {
            frames.add(Frame.ofNormal());
            return;
        }
        frames.add(Frame.ofFinal());
    }

    public int size() {
        return frames.size();
    }

    public boolean isGameOver() {
        return frames.size() == END_FRAME_COUNT && frames.getLast().isEnd();
    }

    protected Deque<Frame> getFrames() {
        return this.frames;
    }

    public Player getPlayer() {
        return this.player;
    }
}
