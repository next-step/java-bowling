package bowling.domain;

import java.util.Collections;
import java.util.List;

public class FrameTotalScores {
    private final List<Integer> totalScores;

    public FrameTotalScores(List<Integer> totalScores) {
        this.totalScores =  totalScores;
    }

    public List<Integer> getTotalScores() {
        return Collections.unmodifiableList(this.totalScores);
    }

    public int getSize() {
        return this.totalScores.size();
    }
}
