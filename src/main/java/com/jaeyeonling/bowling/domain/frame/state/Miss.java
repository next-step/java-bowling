package com.jaeyeonling.bowling.domain.frame.state;

import com.jaeyeonling.bowling.domain.frame.KnockdownPins;

import java.util.Optional;

public class Miss extends ValidFrameState {

    private final KnockdownPins first;
    private final KnockdownPins second;

    Miss(final KnockdownPins first,
         final KnockdownPins second) {
        this.first = first;
        this.second = second;
    }

    static Miss of(final KnockdownPins knockdownPins) {
        return new Miss(knockdownPins, null);
    }

    @Override
    public boolean isFinished() {
        return getSecond().isPresent();
    }

    @Override
    FrameState validBowl(final KnockdownPins knockdownPins) {
        if (first.sum(knockdownPins).isMax()) {
            return new Spare(first);
        }

        return new Miss(first, knockdownPins);
    }

    @Override
    public String toSymbol() {
        return getSecond().map(first::toSymbol)
                .orElseGet(first::toSymbol);
    }

    private Optional<KnockdownPins> getSecond() {
        return Optional.ofNullable(second);
    }
}
