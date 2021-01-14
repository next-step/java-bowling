package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface PinMarkerState {
    PinMarkerState mark(PinMark pinMark);

    boolean isStarted();

    boolean isCompleted();

    List<PinMarkSymbol> toSymbols();
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

    @Override
    public List<PinMarkSymbol> toSymbols() {
        return Collections.unmodifiableList(new ArrayList<>());
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

abstract class Completed implements PinMarkerState {

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
