package camp.nextstep.edu.nextstep8.bowling;

public class Frame {
    private final int number;
    private final int score;

    public Frame(int number, int score) {
        this.number = number;
        this.score = score;
    }

    public boolean match(int number) {
        return this.number == number;
    }

    public int score() {
        return this.score;
    }
}
