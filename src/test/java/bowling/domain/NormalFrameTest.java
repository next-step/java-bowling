package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @Test
    @DisplayName("일반적인 상황의 2회 입력 테스트")
    void testTwoTime() {
        NormalFrame frame = new NormalFrame();
        DownedPin firstTry = DownedPin.fromNumber(3);

        frame.record(firstTry);
        assertThat(frame.isEnd()).isFalse();

        frame.record(firstTry.fromSubordinateTry(DownedPin.fromNumber(6)));
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("첫 투구에 10개가 입력되었을 때 프레임 종료조건 테스트")
    void endedWhenStrike() {
        NormalFrame frame = new NormalFrame();
        DownedPin firstTry = DownedPin.fromNumber(10);

        frame.record(firstTry);

        assertThat(frame.isEnd()).isTrue();
    }

    private static Stream<Arguments> makeMissConditions() {
        return Stream.of(
                Arguments.of(DownedPin.fromNumber(3), DownedPin.fromNumber(4)),
                Arguments.of(DownedPin.fromNumber(9), DownedPin.fromNumber(0))
        );
    }

    @Test
    @DisplayName("스트라이크 컨디션 추가")
    void hasStrikeStatus() {
        NormalFrame frame = new NormalFrame();
        DownedPin firstTry = DownedPin.fromNumber(10);

        frame.record(firstTry);

        assertThat(frame.getFrameStatus()).isEqualTo(FrameStatus.STRIKE);
    }

    @Test
    @DisplayName("스페어 컨디션 추가")
    void hasSpareStatus() {
        NormalFrame frame = new NormalFrame();
        DownedPin firstTry = DownedPin.fromNumber(3);
        DownedPin secondTry = firstTry.fromSubordinateTry(DownedPin.fromNumber(7));

        frame.record(firstTry);
        frame.record(secondTry);

        assertThat(frame.getFrameStatus()).isEqualTo(FrameStatus.SPARE);
    }

    @Test
    @DisplayName("1개 입력시의 MISS 처리")
    void hasMissStatusWithOne() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.record(DownedPin.fromNumber(4));

        assertThat(normalFrame.getFrameStatus()).isEqualTo(FrameStatus.MISS);
    }

    @ParameterizedTest
    @MethodSource("makeMissConditions")
    @DisplayName("2개 입력시의 MISS 처리")
    void hasMissStatus(DownedPin previousPitch, DownedPin currentPitch) {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.record(previousPitch);
        normalFrame.record(currentPitch);

        assertThat(normalFrame.getFrameStatus()).isEqualTo(FrameStatus.MISS);
    }
}
