package bowling.domain.score;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FrameScores {
    private static final int ZERO = 0;

    private final List<FrameScore> frameScores;

    private FrameScores(List<FrameScore> frameScores) {
        this.frameScores = frameScores;
    }

    public static FrameScores of(List<Frame> frames) {
        List<FrameScore> frameScores = frames.stream()
                .map(Frame::calculateFrameScore)
                .filter(Optional::isPresent)
                .flatMap(frameScore -> Stream.of(frameScore.get()))
                .collect(Collectors.toList());
        return new FrameScores(frameScores);
    }

    public List<Integer> getFrameScores() {
        List<Integer> cumulativeFrameScores = new ArrayList<>();
        int cumulativeFrameScore = ZERO;
        for (FrameScore frameScore : frameScores) {
            cumulativeFrameScore += frameScore.getFrameScore();
            cumulativeFrameScores.add(cumulativeFrameScore);
        }
        return cumulativeFrameScores;
    }

    public int getFrameScoreCounts() {
        return frameScores.size();
    }
}
