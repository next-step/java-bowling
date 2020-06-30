package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    private static final int INDEX_ONE = 1;

    List<Frame> frames = new ArrayList<>();

    public boolean add(Frame frame) {
        return frames.add(frame);
    }

    public void replaceFinalFrame(Frame frame) {
        frames.set(frames.size() - INDEX_ONE, frame);
    }

    public List<String> showGameResult() {
        return frames.stream()
                .map(Frame::showResult)
                .collect(Collectors.toList());
    }

    public List<String> showGameScore() {
        return frames.stream()
                .map(Frame::getCurrentScore)
                .collect(Collectors.toList());
    }

    public List<String> showGameSumScore() {
        for (int i = 0; i < frames.size() - 1; i++) {
            frames.get(i).calculateAdditionalScore(frames.get(i + 1).currentScore);
        }
        // last one
        frames.get(frames.size() - 1).calculateAdditionalScore(frames.get(frames.size() - 1).currentScore);

        List<String> result = frames.stream()
                .map(Frame::getCurrentSumScore)
                .collect(Collectors.toList());
        resetSumScores();
        return result;
    }

    private void resetSumScores() {
        frames.stream().forEach(Frame::reset);
    }

    public Frame checkLastFrame() {
        return frames.stream()
                .reduce((a, b) -> b)
                .orElseThrow(() -> new IllegalStateException());
    }

    public void setNextScore(Frame nextFrame) {
        Frame lastFrame = checkLastFrame();
        lastFrame.setNextScore(nextFrame);
    }

}
