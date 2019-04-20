package com.hyoj.bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class PinsTest {
    @Test(expected = RuntimeException.class)
    public void 핀_음수개_시도() {
        Pins.getInstanceOf(-1);
    }

    @Test(expected = RuntimeException.class)
    public void 핀_11개_시도() {
        Pins.getInstanceOf(11);
    }

    @Test
    public void 모든_핀을_쓰러뜨림() {
        List<Pins> pins = new ArrayList<>();
        pins.add(Pins.getInstanceOf(10));
        assertThat(Pins.isAllDown(pins)).isEqualTo(true);
    }

    @Test
    public void 모든_핀을_두번에_넘어뜨림() {
        List<Pins> pins = new ArrayList<>();
        pins.add(Pins.getInstanceOf(1));
        pins.add(Pins.getInstanceOf(9));
        assertThat(Pins.isAllDown(pins)).isEqualTo(true);
    }
}