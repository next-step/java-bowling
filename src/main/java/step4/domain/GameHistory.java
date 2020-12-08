package step4.domain;

import java.util.Collections;
import java.util.List;

public class GameHistory {
    private final List<String> marks;
    private final List<String> points;

    public GameHistory(List<String> marks, List<String> points) {
        this.marks = Collections.unmodifiableList(marks);
        this.points = Collections.unmodifiableList(points);
    }

    public List<String> getMarks() {
        return marks;
    }

    public List<String> getPoints() {
        return points;
    }
}
