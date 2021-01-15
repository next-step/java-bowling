package bowling.domain;

import java.util.List;

public interface PinMarkerState {
    PinMarkerState mark(PinMark pinMark);

    boolean isStarted();

    boolean isCompleted();

    List<PinMarkSymbol> toSymbols();
}

