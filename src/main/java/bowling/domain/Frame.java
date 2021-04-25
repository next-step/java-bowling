package bowling.domain;

abstract class Frame {

    protected final int number;
    protected final Pitches pitches;

    protected Frame(int number, Pitches pitches) {
        this.number = number;
        this.pitches = pitches;
    }

    public int getNumber() {
        return number;
    }

    abstract public void pitch(Pitch pitch);
    abstract public boolean isFinished();
    abstract public String printScoreBoard();
    abstract int spare();
}
