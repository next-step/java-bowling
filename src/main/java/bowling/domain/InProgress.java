package bowling.domain;

abstract class InProgress implements PinMarkerState {

    @Override
    public boolean isCompleted() {
        return false;
    }

}
