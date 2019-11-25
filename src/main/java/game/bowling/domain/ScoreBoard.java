package game.bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by yusik on 2019/11/23.
 */
public class ScoreBoard {

    private static final int NUMBER_OF_FRAME = 10;

    private final String playerName;
    private final List<Frame> frames;
    private int currentFrameIndex = 0;

    public ScoreBoard(String playerName) {

        this.playerName = playerName;
        frames = new ArrayList<>();

        NormalFrame frame = NormalFrame.first();
        frames.add(frame);
        for (int i = 1; i < NUMBER_OF_FRAME - 1; i++) {
            frame = frame.next();
            frames.add(frame);
        }
        frames.add(frame.last());
    }

    public List<Integer> getFrameNos() {
        return frames.stream()
                .map(Frame::getFrameNo)
                .collect(toList());
    }

    public List<String> getStatusLine() {
        return frames.stream()
                .map(Frame::getStatus)
                .collect(toList());
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean hasNextFrame() {
        return currentFrameIndex < frames.size();
    }

    public int nextFrameNo() {
        if (frames.get(currentFrameIndex).isFinish()) {
            currentFrameIndex++;
        }

        return Optional.ofNullable(frames.get(currentFrameIndex))
                .map(Frame::getFrameNo)
                .orElse(0);
    }

    public void bowl(int score) {
        Frame currentFrame = frames.get(currentFrameIndex);
        currentFrame.bowl(score);
    }
}
