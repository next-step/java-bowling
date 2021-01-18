package bowling.domain;

import java.util.List;
import java.util.stream.Stream;

public interface PinMarker {

    default void mark(int countOfFallDown) {
        mark(PinMark.pin(countOfFallDown));
    }

    PinMarkerState getState();

    void mark(PinMark pin);

    int getCountOfAllFallDownPins();

    /**
     * pin marking 완료여부
     *
     * @return
     */
    boolean isCompleted();

    Stream<PinMark> markStream();

    List<PinMarkSymbol> toSymbols();

}
