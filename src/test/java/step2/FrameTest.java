package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step2.domain.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {

    @DisplayName("프레임 투구 테스트")
    @Test
    void pitches() {
        NormalFrame frame = new NormalFrame(1);
        frame.pitches(4);
        assertThat(frame.getScore()).isEqualTo(frame.getCurrentScore());

    }

    @DisplayName("프레임 투구 예외 테스트")
    @Test
    void pitchesWithException() {
        NormalFrame frame = new NormalFrame(1);
        assertThatThrownBy(()->{
            frame.pitches(9);
            frame.pitches(9);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NormalFrame.ERROR_INVALID_PITCHES);
    }

    @DisplayName("프레임 결과 출력 테스트")
    @ParameterizedTest
    @MethodSource("provideFrameAndString")
    void toStringFrame(Frame frame, String result) {
        System.out.println(frame.getResultString());
        assertThat(frame.getResultString()).isEqualTo(result);
    }

    private static Stream<Arguments> provideFrameAndString() {
        Frame strikeFrame = new NormalFrame(1);
        strikeFrame.pitches(10);

        Frame spareFrame = new NormalFrame(2);
        spareFrame.pitches(7);
        spareFrame.pitches(3);

        Frame missFrame = new NormalFrame(3);
        missFrame.pitches(6);
        missFrame.pitches(3);

        Frame prevGutterFrame = new NormalFrame(4);
        prevGutterFrame.pitches(0);
        prevGutterFrame.pitches(5);

        Frame afterGutterFrame = new NormalFrame(4);
        afterGutterFrame.pitches(5);
        afterGutterFrame.pitches(0);

        return Stream.of(
                Arguments.of(strikeFrame, "X"),
                Arguments.of(spareFrame, "7|/"),
                Arguments.of(missFrame, "6|3"),
                Arguments.of(prevGutterFrame, "-|5"),
                Arguments.of(afterGutterFrame, "5|-")
        );
    }

    @DisplayName("프레임 출력결과 테스트")
    @ParameterizedTest
    @MethodSource("provideNormalFrame")
    void normalFrame(Frame frame, String result) {
        assertThat(frame.getResultString()).isEqualTo(result);
    }

    private static Stream<Arguments> provideNormalFrame() {
        NormalFrame frame = new NormalFrame(0);
        frame.pitches(0);

        NormalFrame frame2 = new NormalFrame(1);
        frame2.pitches(9);
        frame2.pitches(1);

        NormalFrame frame3 = new NormalFrame(1);
        frame3.pitches(0);
        frame3.pitches(10);

        NormalFrame frame4 = new NormalFrame(1);
        frame4.pitches(5);
        frame4.pitches(4);

        NormalFrame frame5 = new NormalFrame(1);
        frame5.pitches(5);
        frame5.pitches(0);

        return Stream.of(
                Arguments.of(frame, "-"),
                Arguments.of(frame2, "9|/"),
                Arguments.of(frame3, "-|/"),
                Arguments.of(frame4, "5|4"),
                Arguments.of(frame5, "5|-")
        );
    }

    @DisplayName("마지막 프레임 출력결과 테스트")
    @ParameterizedTest
    @MethodSource("provideFinalFrame")
    void finalFrame(Frame frame, String result) {
        assertThat(frame.getResultString()).isEqualTo(result);
    }

    private static Stream<Arguments> provideFinalFrame() {
        FinalFrame frame1 = new FinalFrame(10);
        FinalFrame frame2 = new FinalFrame(10);
        FinalFrame frame3 = new FinalFrame(10);

        frame1.pitches(10);
        frame1.pitches(0);
        frame1.pitches(10);

        frame2.pitches(0);
        frame2.pitches(10);
        frame2.pitches(10);

        frame3.pitches(10);
        frame3.pitches(10);
        frame3.pitches(2);

        return Stream.of(
                Arguments.of(frame1, "X|-|/"),
                Arguments.of(frame2, "-|/|X"),
                Arguments.of(frame3, "X|X|2")
                );
    }

}
