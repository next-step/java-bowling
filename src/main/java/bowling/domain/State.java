package bowling.domain;

public enum State {
    NotFinish(false),
    Finish(true),
    Bonus(false);

    private final boolean finish;

    State(boolean finish) {
        this.finish = finish;
    }

    public boolean isFinish() {
        return finish;
    }
}
