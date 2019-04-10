package com.hyoj.bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FinalFrameTest {
    @Test
    public void 마지막프레임_한번더_투구_기회있음() {
        final FinalFrame finalFrame = new FinalFrame(new DefaultFrame().add(4).add(6));
        assertThat(finalFrame.canOneMoreShot()).isEqualTo(true);
    }

    @Test
    public void 마지막프레임_텍스트로_표현() {
        final FinalFrame finalFrame = new FinalFrame(new DefaultFrame().add(4).add(6));
        finalFrame.add(10);
        assertThat(finalFrame.toString()).isEqualTo("4|/|X");
    }
}