package bowling.pitching;

public enum PitchingState {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-");

    private String mark;

    PitchingState(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return mark;
    }
}
