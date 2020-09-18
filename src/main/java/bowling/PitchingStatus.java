package bowling;

public enum PitchingStatus {
    Ready,
    Done;

    public boolean isDone() {
        return this.equals(Done);
    }
}
