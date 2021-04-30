package bowling.domain;

import java.util.List;

public class Marks {
    private final List<Mark> marks;

    public Marks(List<Mark> marks) {
        this.marks = marks;
    }

    public List<Mark> marks() {
        return marks;
    }
}
