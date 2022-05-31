package bowling.domain.state;

public interface State {

    static State ofSpare(int firstHitCount) {
        return new Spare(firstHitCount);
    }

    static State ofStrike() {
        return new Strike();
    }

    static State ofMiss(int firstHitCount, int secondHitCount) {
        return new Miss(firstHitCount, secondHitCount);
    }

    static State ofReady() {
        return new Ready();
    }

    State bowl(int hitCount);

    boolean isDone();
}
