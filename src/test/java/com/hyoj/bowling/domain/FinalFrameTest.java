package com.hyoj.bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FinalFrameTest {
    @Test
    public void 마지막프레임_투구_1회는_투구기회_아직있음() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame()
            .throwBowlingBall(Pins.getInstanceOf(1));

        assertThat(finalFrame.isDone()).isEqualTo(false);
    }

    @Test(expected = RuntimeException.class)
    public void 마지막프레임_미스는_추가_투구불가() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame()
            .throwBowlingBall(Pins.getInstanceOf(1))
            .throwBowlingBall(Pins.getInstanceOf(2))
            .throwBowlingBall(Pins.getInstanceOf(10));
    }

    @Test
    public void 마지막프레임_스페어_한번더_투구_기회있음() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame()
            .throwBowlingBall(Pins.getInstanceOf(4))
            .throwBowlingBall(Pins.getInstanceOf(6));
        assertThat(finalFrame.isDone()).isEqualTo(false);
    }

    @Test
    public void 마지막프레임_스페어_한번더_투구_이후_더이상_투구_불가() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame()
            .throwBowlingBall(Pins.getInstanceOf(4))
            .throwBowlingBall(Pins.getInstanceOf(6)).throwBowlingBall(Pins.getInstanceOf(2));
        assertThat(finalFrame.isDone()).isEqualTo(true);
    }

    @Test
    public void 마지막프레임_스트라이크_1회_한번더_투구_기회있음() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame()
            .throwBowlingBall(Pins.getInstanceOf(10));
        assertThat(finalFrame.isDone()).isEqualTo(false);
    }

    @Test
    public void 마지막프레임_두번째투구_스트라이크_실패() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame()
            .throwBowlingBall(Pins.getInstanceOf(10))
            .throwBowlingBall(Pins.getInstanceOf(2));
        assertThat(finalFrame.isDone()).isEqualTo(true);
    }

    @Test
    public void 마지막프레임_스트라이크_2회연속_한번더_투구_기회있음() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame()
            .throwBowlingBall(Pins.getInstanceOf(10))
            .throwBowlingBall(Pins.getInstanceOf(10));
        assertThat(finalFrame.isDone()).isEqualTo(false);
    }

    @Test
    public void 마지막프레임_스페어_미스_텍스트로_표현() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame()
            .throwBowlingBall(Pins.getInstanceOf(4))
            .throwBowlingBall(Pins.getInstanceOf(6))
            .throwBowlingBall(Pins.getInstanceOf(2));
        assertThat(finalFrame.toString()).isEqualTo("4|/|2");
    }

    @Test
    public void 마지막프레임_스페어_스트라이크_텍스트로_표현() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame()
            .throwBowlingBall(Pins.getInstanceOf(4))
            .throwBowlingBall(Pins.getInstanceOf(6))
            .throwBowlingBall(Pins.getInstanceOf(10));
        assertThat(finalFrame.toString()).isEqualTo("4|/|X");
    }

    @Test
    public void 마지막프레임_스트라이크_3회_텍스트로_표현() {
        final DefaultFrame frame = (DefaultFrame) DefaultFrame.createFirstFrame();
        final FinalFrame finalFrame = (FinalFrame) frame.createFinalFrame()
            .throwBowlingBall(Pins.getInstanceOf(10))
            .throwBowlingBall(Pins.getInstanceOf(10))
            .throwBowlingBall(Pins.getInstanceOf(10));
        assertThat(finalFrame.toString()).isEqualTo("X|X|X");
    }
}