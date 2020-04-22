package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FrameScores {
    private List<Integer> frameScores;

    public FrameScores(List<Integer> frameScores) {
        this.frameScores = frameScores;
    }

    public FrameScores() {
        this.frameScores = new ArrayList<>();
    }

    public List<String> getScore() {
        return frameScores.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return frameScores.get(0).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        FrameScores that = (FrameScores) o;
        return Objects.equals(frameScores, that.frameScores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameScores);
    }

    public void add(int score) {
        this.frameScores.add(score);
    }
}
