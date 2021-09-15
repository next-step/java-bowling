package bowling.bowlingdrawing.domain.frame;

import bowling.bowlingdrawing.domain.pitching.Pitching;
import bowling.bowlingdrawing.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class NormalFrameTest {

    @Test
    @DisplayName("Frame 생성")
    void create() {
        // given
        Pitching pitching = Pitching.of(9);
        // when
        Frame frame = new NormalFrame(pitching);
        // then
        assertThat(frame).isEqualTo(new NormalFrame(Pitching.of(9)));
    }

    @ParameterizedTest(name = "SecondPitching 추가 [{index}] {0}, {1}")
    @CsvSource({"1, 8"})
    void addSecondPitching(int firstPins, int secondPins) {
        // given
        Pitching firstPitching = Pitching.of(firstPins);
        Frame frame = new NormalFrame(firstPitching);
        // when
        Pitching secondPitching = firstPitching.next(secondPins);
        frame.secondPitching(secondPitching);
        // then
        assertThat(frame).isEqualTo(new NormalFrame(firstPitching, secondPitching));
    }

    @ParameterizedTest(name = "SecondPitching 추가 실패 : 합계 10 초과")
    @CsvSource({"10, 1", "9, 2"})
    void create_fail(int firstPins, int secondPins) {
        // given
        Pitching firstPitching = Pitching.of(firstPins);
        Pitching secondPitching = firstPitching.next(secondPins);
        Frame frame = new NormalFrame(firstPitching);
        // when, then
        assertThatThrownBy(() -> new NormalFrame(firstPitching, secondPitching))
                .isInstanceOf(CustomException.class);
        assertThatThrownBy(() -> frame.secondPitching(secondPitching))
                .isInstanceOf(CustomException.class);
    }

    @ParameterizedTest(name = "점수 계산 [{index}] {0}, {1}, {2} : {3}")
    @CsvSource({"10, 10, 10, 30", "3, 7, 10, 20", "2, 7, 10, 9"})
    void score(int firstPins, int secondPins, int thirdPins, int result) {
        // given
        Pitching pitching = Pitching.first(firstPins);
        Pitching nextPitching1 = pitching.next(secondPins);
        Pitching nextPitching2 = nextPitching1.next(thirdPins);
        Frame frame = new NormalFrame(pitching);
        if (firstPins < 10) {
            frame.secondPitching(nextPitching1);
        }
        // when
        Integer score = frame.score();
        // then
        assertThat(score).isEqualTo(result);
    }

    @Test
    @DisplayName("strike 여부 확인")
    void strike() {
        // given
        Pitching pitching1 = Pitching.first(10);
        Frame frame1 = new NormalFrame(pitching1);
        Pitching pitching2 = pitching1.next(9);
        Frame frame2 = new NormalFrame(pitching2);
        // when
        boolean strike1 = frame1.strike();
        boolean strike2 = frame2.strike();
        // then
        assertThat(strike1).isTrue();
        assertThat(strike2).isFalse();
    }

    @Test
    @DisplayName("spare 여부 확인")
    void spare() {
        // given
        Pitching pitching1 = Pitching.first(9);
        Pitching pitching2 = pitching1.next(1);
        Pitching pitching3 = pitching2.next(8);
        Frame frame1 = new NormalFrame(pitching1, pitching2);
        Frame frame2 = new NormalFrame(pitching2, pitching3);
        // when
        boolean spare1 = frame1.spare();
        boolean spare2 = frame2.spare();
        // then
        assertThat(spare1).isTrue();
        assertThat(spare2).isFalse();
    }
}