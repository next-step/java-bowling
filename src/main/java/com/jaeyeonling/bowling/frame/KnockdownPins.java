package com.jaeyeonling.bowling.frame;

import java.util.HashMap;
import java.util.Map;

public class KnockdownPins {

    private static final Map<Integer, KnockdownPins> POOL = new HashMap<>();

    static final int MAX_VALUE = 10;
    static final int MIN_VALUE = 0;

    public static final KnockdownPins MAX = valueOf(MAX_VALUE);
    public static final KnockdownPins MIN = valueOf(MIN_VALUE);

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

        return POOL.computeIfAbsent(knockdownPins, KnockdownPins::new);
    }

    int getKnockdownPins() {
        return knockdownPins;
    }

    public boolean isMax() {
        return knockdownPins == MAX_VALUE;
    }

    public boolean isMin() {
        return knockdownPins == MIN_VALUE;
    }

    public String toSymbol() {
        return BowlingSymbol.toSymbol(this);
    }

    public String toSymbol(final KnockdownPins next) {
        return BowlingSymbol.toSymbol(this, next);
    }
}
