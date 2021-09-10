package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private static final int MAX_FRAME_SIZE = 10;
    private static final int LAST_FRAME_INDEX = 9;


    private final User user;
    private List<Frame> frames = new ArrayList<>();

    public Game(User user) {
        this.user = user;
        frames.add(new NormalFrame());
    }

    public void play(Shot shot) {
        if (isFinished()) {
            throw new IllegalStateException("Game is already finished");
        }

        int currentIndex = frames.size() - 1;
        Frame currentFrame = frames.get(currentIndex);
        currentFrame.playShot(shot);
        addPoint(shot);
        prepareNext(currentFrame);
    }

    public void prepareNext(Frame currentFrame) {
        if (isFinished()) {
            return;
        }

        if (!currentFrame.isFinished()) {
            return;
        }

        if (frames.size() < LAST_FRAME_INDEX) {
            frames.add(new NormalFrame());
            return;
        }

        frames.add(new LastFrame());
    }

    public boolean isFinished() {
        return this.frames.size() == MAX_FRAME_SIZE
                && this.frames.get(LAST_FRAME_INDEX)
                .isFinished();
    }

    public void addPoint(Shot shot) {
        frames.stream()
                .filter(frameScore -> !frameScore.isScoringFinished())
                .forEach((frameScore -> frameScore.addPoint(shot)));
    }

    public List<Integer> currentScoreList() {
        List<Integer> scores = new ArrayList<>();


        frames.stream()
                .filter((frame -> frame.isScoringFinished()))
                .forEach((frame -> {
                    scores.add(getPreviousScore(scores) + frame.getPoint());
                }));

        return scores;
    }

    private int getPreviousScore(List<Integer> scores) {
        if (scores.isEmpty()) {
            return 0;
        }

        int lastIndex =scores.size() - 1;
        return scores.get(lastIndex);
    }

    public boolean isNotFinished() {
        return !isFinished();
    }

    public int getCurrentFrameIndex() {
        return frames.size();
    }

    public int getRemainFrameCount() {
        return MAX_FRAME_SIZE - frames.size();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public User getUser() {
        return user;
    }

    public static Game of(User user) {
        return new Game(user);
    }
}
