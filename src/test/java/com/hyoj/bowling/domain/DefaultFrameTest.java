package com.hyoj.bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultFrameTest {

    @Test(expected = RuntimeException.class)
    public void 투구_네번재_시도() {
        final DefaultFrame defaultFrame = new DefaultFrame();
        for (int i = 0; i < 4; i++) {
            defaultFrame.add(2);
        }
    }

    @Test
    public void 총투구_결과_거터() {
        final DefaultFrame defaultFrame = new DefaultFrame();
        defaultFrame.add(0);
        defaultFrame.add(0);

        assertThat(defaultFrame.getFinalMarkType()).isEqualTo(MarkType.GUTTER);
    }

    @Test
    public void 총투구_결과_미스() {
        final DefaultFrame defaultFrame = new DefaultFrame();
        defaultFrame.add(5);
        defaultFrame.add(3);

        assertThat(defaultFrame.getFinalMarkType()).isEqualTo(MarkType.MISS);
    }

    @Test
    public void 총투구_결과_스페어() {
        final DefaultFrame defaultFrame = new DefaultFrame();
        defaultFrame.add(2);
        defaultFrame.add(8);

        assertThat(defaultFrame.getFinalMarkType()).isEqualTo(MarkType.SPARE);
    }

    @Test
    public void 총투구_결과_스트라이크() {
        final DefaultFrame defaultFrame = new DefaultFrame();
        defaultFrame.add(10);

        assertThat(defaultFrame.getFinalMarkType()).isEqualTo(MarkType.STRIKE);
    }

    @Test
    public void 한번에_투구_횟수_모두_소모() {
        final DefaultFrame defaultFrame = new DefaultFrame();
        defaultFrame.add(10);

        assertThat(defaultFrame.isDone()).isEqualTo(true);
    }

    @Test
    public void 두번에_투구_횟수_모두_소모() {
        final DefaultFrame defaultFrame = new DefaultFrame();
        defaultFrame.add(4);
        defaultFrame.add(6);

        assertThat(defaultFrame.isDone()).isEqualTo(true);
    }

    @Test
    public void 텍스트로_표현_STRIKE() {
        final DefaultFrame defaultFrame = new DefaultFrame();
        defaultFrame.add(10);

        assertThat(defaultFrame.toString()).isEqualTo(MarkType.STRIKE.toString());
    }

    @Test
    public void 텍스트로_표현_SPARE() {
        final DefaultFrame defaultFrame = new DefaultFrame();
        defaultFrame.add(0);
        defaultFrame.add(10);

        assertThat(defaultFrame.toString()).isEqualTo("-|" + MarkType.SPARE.toString());
    }

    @Test
    public void 텍스트로_표현_GUTTUER() {
        final DefaultFrame defaultFrame = new DefaultFrame();
        defaultFrame.add(0);
        defaultFrame.add(0);

        assertThat(defaultFrame.toString()).isEqualTo(MarkType.GUTTER.toString() + "|" + MarkType.GUTTER.toString());
    }

    @Test
    public void 텍스트로_표현_MISS() {
        final DefaultFrame defaultFrame = new DefaultFrame();
        defaultFrame.add(3);
        defaultFrame.add(5);

        assertThat(defaultFrame.toString()).isEqualTo("3|5");
    }
}