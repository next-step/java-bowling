package com.hyoj.bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FinalFrameTest {
    @Test
    public void 마지막프레임_스페어_한번더_투구_기회있음() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame().add(4).add(6);
        assertThat(finalFrame.canOneMoreShot()).isEqualTo(true);
    }

    @Test
    public void 마지막프레임_스페어_한번더_투구_이후_더이상_투구_불가() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame().add(4).add(6).add(2);
        assertThat(finalFrame.canOneMoreShot()).isEqualTo(false);
    }

    @Test
    public void 마지막프레임_스트라이크_1회_한번더_투구_기회있음() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame().add(10);
        assertThat(finalFrame.canOneMoreShot()).isEqualTo(true);
    }

    @Test
    public void 마지막프레임_두번째투구_스트라이크_실패() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame().add(10).add(2);
        assertThat(finalFrame.canOneMoreShot()).isEqualTo(false);
    }

    @Test
    public void 마지막프레임_스트라이크_2회연속_한번더_투구_기회있음() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame().add(10).add(10);
        assertThat(finalFrame.canOneMoreShot()).isEqualTo(true);
    }

    @Test
    public void 마지막프레임_스페어_미스_텍스트로_표현() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame().add(4).add(6).add(2);
        assertThat(finalFrame.toString()).isEqualTo("4|/|2");
    }

    @Test
    public void 마지막프레임_스페어_스트라이크_텍스트로_표현() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame().add(4).add(6).add(10);
        assertThat(finalFrame.toString()).isEqualTo("4|/|X");
    }

    @Test
    public void 마지막프레임_스트라이크_3회_텍스트로_표현() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame().add(10).add(10).add(10);
        assertThat(finalFrame.toString()).isEqualTo("X|X|X");
    }
}