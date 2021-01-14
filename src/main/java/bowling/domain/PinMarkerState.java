package bowling.domain;

public interface PinMarkerState {
    PinMarkerState mark(PinMark pinMark);

    boolean isStarted();

    boolean isCompleted();
}

abstract class BeforeStart implements PinMarkerState {
    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}

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

class Completed implements PinMarkerState {

    @Override
    public PinMarkerState mark(PinMark pinMark) {
        throw new IllegalStateException("더 이상 PinMark 를 추가할 수 없습니다");
    }

    @Override
    public boolean isStarted() {
        return true;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
