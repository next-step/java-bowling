package bowling.domain;

public class Ready extends RunningState {
    private static final String READY_SYMBOL = "";

    private Ready() {
    }

    public static State create() {
        return new Ready();
    }

    @Override
    public State bowl(Pitching pitching) {
        if (pitching.isStrike()) {
            return new Strike();
        }
        return new FirstPitching(pitching);
    }

    @Override
    public String symbol() {
        return READY_SYMBOL;
    }

    @Override
    public Score score() {
        return new Score(Score.INCALCULABLE_SCORE, Score.INCALCULABLE_PITCHES);
    }

    @Override
    public Score calculatorScore(Score before) {
        throw new IllegalStateException("이전 투구가 없는 상태입니다.");
    }
}
