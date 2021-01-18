package bowling.domain;

public abstract class Completed implements PinMarkerState {

    @Override
    final public PinMarkerState mark(PinMark pinMark) {
        throw new IllegalStateException("더 이상 PinMark 를 추가할 수 없습니다");
    }

    @Override
    final public boolean isCompleted() {
        return true;
    }

}
