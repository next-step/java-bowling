package com.hyoj.bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test(expected = RuntimeException.class)
    public void 투구_네번재_시도() {
        Frame frame = new Frame();
        for (int i = 0; i < 4; i++) {
            frame.add(2);
        }
    }

    @Test
    public void 총투구_결과_거터() {
        Frame frame = new Frame();
        frame.add(0);
        frame.add(0);

        assertThat(frame.getFinalMarkType()).isEqualTo(MarkType.GUTTER);
    }

    @Test
    public void 총투구_결과_미스() {
        Frame frame = new Frame();
        frame.add(5);
        frame.add(3);

        assertThat(frame.getFinalMarkType()).isEqualTo(MarkType.MISS);
    }

    @Test
    public void 총투구_결과_스페어() {
        Frame frame = new Frame();
        frame.add(2);
        frame.add(8);

        assertThat(frame.getFinalMarkType()).isEqualTo(MarkType.SPARE);
    }

    @Test
    public void 총투구_결과_스트라이크() {
        Frame frame = new Frame();
        frame.add(10);

        assertThat(frame.getFinalMarkType()).isEqualTo(MarkType.STRIKE);
    }
}