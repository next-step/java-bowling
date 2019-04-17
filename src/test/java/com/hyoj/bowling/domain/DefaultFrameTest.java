package com.hyoj.bowling.domain;

import com.hyoj.bowling.domain.status.Gutter;
import com.hyoj.bowling.domain.status.Miss;
import com.hyoj.bowling.domain.status.Spare;
import com.hyoj.bowling.domain.status.Strike;
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

        assertThat(frame.getResultStatus()).isEqualTo(Gutter.getInstance());
    }

    @Test
    public void 총투구_결과_미스() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(5);
        frame.add(3);

        assertThat(frame.getResultStatus()).isEqualTo(Miss.getInstance());
    }

    @Test
    public void 총투구_결과_스페어() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(2);
        frame.add(8);

        assertThat(frame.getResultStatus()).isEqualTo(Spare.getInstance());
    }

    @Test
    public void 총투구_결과_스트라이크() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(10);

        assertThat(frame.getResultStatus()).isEqualTo(Strike.getInstance());
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

        assertThat(frame.toString()).isEqualTo(Strike.getInstance().toString());
    }

    @Test
    public void 텍스트로_표현_SPARE() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(0);
        frame.add(10);

        assertThat(frame.toString()).isEqualTo("-|" + Spare.getInstance().toString());
    }

    @Test
    public void 텍스트로_표현_GUTTUER() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(0);
        frame.add(0);

        assertThat(frame.toString()).isEqualTo(Gutter.getInstance().toString() + "|" + Gutter.getInstance().toString());
    }

    @Test
    public void 텍스트로_표현_MISS() {
        final Frame frame = DefaultFrame.createFirstFrame();
        frame.add(3);
        frame.add(5);

        assertThat(frame.toString()).isEqualTo("3|5");
    }
}