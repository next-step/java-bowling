package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("볼링 프레임 점수 테스트")
public class FrameScoreTest {

    @DisplayName("프레임의 첫 투구가 스트라이크면 두 번의 투구를 기다려야 한다.")
    @Test
    void strikeFrameScoreTest() {
        // given, when
        FrameScore initialStrikeFrameScore = FrameScore.initial(new FrameFallenPin(10));

        FrameFallenPin firstFallenPin = new FrameFallenPin(5);
        FrameScore frameScore = FrameScore.initial(new FrameFallenPin(5));
        FrameScore frameNextScore = frameScore.nextSecond(firstFallenPin.second(5));
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
        FrameScore initialFrameScore = FrameScore.initial(firstFallenPin);
        FrameScore spareFrameScore = initialFrameScore.nextSecond(firstFallenPin.second(5));

        // then
        assertSame(spareFrameScore.waitingPitchingCount(), 1);
    }

    @DisplayName("프레임의 투구가 첫 투구이고 스트라이크가 아니면 아 한 번의 투구(2번째 투구)를 기다려야 한다.")
    @Test
    void frameFirstScoreTest() {
        // given, when
        FrameScore initialFrameScore = FrameScore.initial(new FrameFallenPin(5));

        // then
        assertSame(initialFrameScore.waitingPitchingCount(), 1);
    }

    @DisplayName("스트라이크와 스페어가 아니라면 다음 프레임 점수는 이전 프레임 점수에 현재 쓰러뜨린 볼링 핀의 합이다.")
    @Test
    void nextFrameScoreTest() {
        // given, when
        FrameFallenPin firstFallenPin = new FrameFallenPin(5);
        FrameScore initialFrameScore = FrameScore.initial(firstFallenPin);
        FrameScore secondFrameScore = initialFrameScore.nextSecond(firstFallenPin.second(4));

        FrameFallenPin thirdFallenPin = new FrameFallenPin(4);
        FrameScore thirdFrameScore = secondFrameScore.nextFirst(thirdFallenPin);
        FrameScore fourthFrameScore = thirdFrameScore.nextSecond(thirdFallenPin.second(3));

        // then
        assertEquals(initialFrameScore.score(), Score.of(5));
        assertEquals(secondFrameScore.score(), Score.of(9));
        assertEquals(thirdFrameScore.score(), Score.of(13));
        assertEquals(fourthFrameScore.score(), Score.of(16));
    }
}
