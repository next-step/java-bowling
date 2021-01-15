package bowling.domain;

abstract class InProgress implements PinMarkerState {

    @Override
    public boolean isStarted() {
        return true;
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
