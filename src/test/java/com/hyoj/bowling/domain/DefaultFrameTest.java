package com.hyoj.bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultFrameTest {

    @Test(expected = RuntimeException.class)
    public void 투구_네번재_시도() {
        final Frame frame = DefaultFrame.createFirstFrame();
        for (int i = 0; i < 4; i++) {
            frame.add(2);
        }
    }

    @Test
    public void 총투구_결과_거터() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(0);
        frame.add(0);

        assertThat(frame.getFinalMarkType()).isEqualTo(MarkType.GUTTER);
    }

    @Test
    public void 총투구_결과_미스() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(5);
        frame.add(3);

        assertThat(frame.getFinalMarkType()).isEqualTo(MarkType.MISS);
    }

    @Test
    public void 총투구_결과_스페어() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(2);
        frame.add(8);

        assertThat(frame.getFinalMarkType()).isEqualTo(MarkType.SPARE);
    }

    @Test
    public void 총투구_결과_스트라이크() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(10);

        assertThat(frame.getFinalMarkType()).isEqualTo(MarkType.STRIKE);
    }

    @Test
    public void 한번에_투구_횟수_모두_소모() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(10);

        assertThat(frame.isDone()).isEqualTo(true);
    }

    @Test
    public void 두번에_투구_횟수_모두_소모() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(4);
        frame.add(6);

        assertThat(frame.isDone()).isEqualTo(true);
    }

    @Test
    public void 텍스트로_표현_STRIKE() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(10);

        assertThat(frame.toString()).isEqualTo(MarkType.STRIKE.toString());
    }

    @Test
    public void 텍스트로_표현_SPARE() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(0);
        frame.add(10);

        assertThat(frame.toString()).isEqualTo("-|" + MarkType.SPARE.toString());
    }

    @Test
    public void 텍스트로_표현_GUTTUER() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(0);
        frame.add(0);

        assertThat(frame.toString()).isEqualTo(MarkType.GUTTER.toString() + "|" + MarkType.GUTTER.toString());
    }

    @Test
    public void 텍스트로_표현_MISS() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(3);
        frame.add(5);

        assertThat(frame.toString()).isEqualTo("3|5");
    }
}