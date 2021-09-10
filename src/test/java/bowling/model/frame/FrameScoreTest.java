package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("볼링 프레임 점수 테스트")
public class FrameScoreTest {

    @DisplayName("프레임의 첫 투구가 스트라이크면 두 번의 투구를 기다려야 한다.")
    @Test
    void strikeFrameScoreTest() {
        // given, when
        FrameScore initialStrikeFrameScore = FrameScore.ofInitialFallenPin(new FrameFallenPin(10));

        FrameScore frameScore = FrameScore.ofInitialFallenPin(new FrameFallenPin(5));
        FrameScore frameNextScore = frameScore.nextSecond(new FrameFallenPin(5));
        FrameScore strikeFrameScore = frameNextScore.nextFirst(new FrameFallenPin(10));

        // then
        assertSame(initialStrikeFrameScore.waitingPitchingCount(), 2);
        assertSame(strikeFrameScore.waitingPitchingCount(), 2);
    }

    @DisplayName("프레임의 투구가 스페어면 한 번의 투구를 기다려야 한다.")
    @Test
    void spareFrameScoreTest() {
        // given, when
        FrameFallenPin firstFallenPin = new FrameFallenPin(5);
        FrameScore frameFirstScore = FrameScore.ofInitialFallenPin(firstFallenPin);
        FrameScore spareFrameScore = frameFirstScore.nextSecond(firstFallenPin.second(5));

        // then
        assertSame(spareFrameScore.waitingPitchingCount(), 1);
    }

    @DisplayName("프레임의 투구가 스트라이가 아닌 첫번째면 한 번의 투구(2번째 투구)를 기다려야 한다.")
    @Test
    void frameFirstScoreTest() {
        // given, when
        FrameScore initialFrameScore = FrameScore.ofInitialFallenPin(new FrameFallenPin(5));

        // then
        assertSame(initialFrameScore.waitingPitchingCount(), 1);
    }
}
