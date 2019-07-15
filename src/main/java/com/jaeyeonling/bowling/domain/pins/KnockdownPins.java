package com.jaeyeonling.bowling.domain.pins;

import java.util.Objects;

public class KnockdownPins {

    static final int MAX_VALUE = 10;
    static final int MIN_VALUE = 0;

    private final int knockdownPins;

    private KnockdownPins(final int knockdownPins) {
        this.knockdownPins = knockdownPins;
    }

    public static KnockdownPins valueOf(final int knockdownPins) {
        if (knockdownPins < MIN_VALUE) {
            throw new ShorterThanMinKnockdownPinsException(knockdownPins);
        }
        if (knockdownPins > MAX_VALUE) {
            throw new LongerThanMaxKnockdownPinsException(knockdownPins);
        }


        return new KnockdownPins(knockdownPins);
    }

    public int getKnockdownPins() {
        return knockdownPins;
    }

    public boolean isMax() {
        return knockdownPins == MAX_VALUE;
    }

    public boolean isMin() {
        return knockdownPins == MIN_VALUE;
    }

    public KnockdownPins sum(final KnockdownPins other) {
        return new KnockdownPins(knockdownPins + other.knockdownPins);
    }

    public KnockdownPins remaining() {
        return new KnockdownPins(MAX_VALUE - knockdownPins);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KnockdownPins)) {
            return false;
        }

        final KnockdownPins that = (KnockdownPins) o;
        return knockdownPins == that.knockdownPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(knockdownPins);
    }
}
