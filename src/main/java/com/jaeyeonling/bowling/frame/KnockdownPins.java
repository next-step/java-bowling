package com.jaeyeonling.bowling.frame;

import java.util.HashMap;
import java.util.Map;

public class KnockdownPins {

    private static final Map<Integer, KnockdownPins> POOL = new HashMap<>();

    static final int MAX_VALUE = 10;
    static final int GUTTER_VALUE = 0;

    public static final KnockdownPins MAX = valueOf(MAX_VALUE);
    public static final KnockdownPins GUTTER = valueOf(GUTTER_VALUE);

    private final int knockdownPins;

    private KnockdownPins(final int knockdownPins) {
        this.knockdownPins = knockdownPins;
    }

    public static KnockdownPins valueOf(final int knockdownPins) {
        if (knockdownPins < GUTTER_VALUE) {
            throw new ShorterThanMinKnockdownPinsException(knockdownPins);
        }
        if (knockdownPins > MAX_VALUE) {
            throw new LongerThanMaxKnockdownPinsException(knockdownPins);
        }

        return POOL.computeIfAbsent(knockdownPins, KnockdownPins::new);
    }

    public boolean isMax() {
        return knockdownPins == MAX_VALUE;
    }

    public boolean isGutter() {
        return knockdownPins == GUTTER_VALUE;
    }

    public KnockdownPins remaining() {
        return KnockdownPins.valueOf(MAX_VALUE - knockdownPins);
    }

    public KnockdownPins sum(final KnockdownPins other) {
        return valueOf(knockdownPins + other.knockdownPins);
    }

    public String toSymbol() {
        return BowlingSymbol.toSymbol(this);
    }

    public String toSymbol(final KnockdownPins next) {
        return BowlingSymbol.toSymbol(this, next);
    }

    int getKnockdownPins() {
        return knockdownPins;
    }
}
