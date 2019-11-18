package com.seok2.bowling.pin.domain;

import java.util.stream.IntStream;

public class Pin {

    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final Pin [] CACHE = IntStream.rangeClosed(MIN, MAX)
                                        .mapToObj(Pin::new)
                                        .toArray(Pin[]::new);
    private final int felled;

    private Pin(int felled) {
        this.felled = felled;
    }

    public static Pin of(int felled) {
        try{
            return CACHE[felled];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("쓰러트린 핀의 갯수는 0~10 사이여야 합니다.");
        }
    }
}
