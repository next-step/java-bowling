package com.seok2.bowling.pin.domain;

import com.seok2.bowling.frame.domain.Score;
import java.util.Objects;
import java.util.stream.IntStream;

public class Pin {

    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final Pin[] CACHE = IntStream.rangeClosed(MIN, MAX)
        .mapToObj(Pin::new)
        .toArray(Pin[]::new);
    private final int felled;

    private Pin(int felled) {
        this.felled = felled;
    }

    public static Pin of(int felled) {
        try {
            return CACHE[felled];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("쓰러트린 핀의 갯수는 0~10 사이여야 합니다.");
        }
    }

    public Pin add(Pin felled) {
        if (felled.felled == MIN) {
            return this;
        }
        return of(this.felled + felled.felled);
    }

    public boolean isAllFelled() {
        return felled == MAX;
    }

    public boolean isFelledAtAll() {
        return felled == MIN;
    }

    public Score toScore() {
        return Score.of(felled);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pin)) {
            return false;
        }
        Pin pin = (Pin) o;
        return felled == pin.felled;
    }

    @Override
    public int hashCode() {
        return Objects.hash(felled);
    }

    @Override
    public String toString() {
        return String.valueOf(felled);
    }
}
