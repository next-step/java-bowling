package camp.nextstep.edu.rebellion.bowling.domain.status;

public class Playing implements FrameStatus {
    private final int score;

    public Playing (int score) {
        this.score = score;
    }
    @Override
    public String makeSymbol() {
        return String.valueOf(score);
    }
}
