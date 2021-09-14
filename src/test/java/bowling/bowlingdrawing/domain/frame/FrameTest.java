package bowling.bowlingdrawing.domain.frame;

import bowling.bowlingdrawing.domain.pitching.Pitching;
import bowling.bowlingdrawing.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class FrameTest {

    @Test
    @DisplayName("Frame 생성")
    void create() {
        // given
        Pitching pitching = Pitching.of(9);
        // when
        Frame frame = Frame.of(pitching);
        // then
        assertThat(frame).isEqualTo(Frame.of(Pitching.of(9)));
    }

    @ParameterizedTest(name = "SecondPitching 추가 [{index}] {0}, {1}")
    @CsvSource({"1, 8"})
    void addSecondPitching(int firstPins, int secondPins) {
        // given
        Pitching firstPitching = Pitching.of(firstPins);
        Frame frame = Frame.of(firstPitching);
        // when
        Pitching secondPitching = firstPitching.next(secondPins);
        frame.secondPitching(secondPitching);
        // then
        assertThat(frame).isEqualTo(Frame.of(firstPitching, secondPitching));
    }

    @ParameterizedTest(name = "SecondPitching 추가 실패 : 합계 10 초과")
    @CsvSource({"10, 1", "9, 2"})
    void create_fail(int firstPins, int secondPins) {
        // given
        Pitching firstPitching = Pitching.of(firstPins);
        Pitching secondPitching = firstPitching.next(secondPins);
        // when, then
        assertThatThrownBy(() -> Frame.of(firstPitching, secondPitching))
                .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("점수 계산")
    void score() {
        // given
        Pitching pitching = Pitching.first(10);
        Pitching nextPitching1 = pitching.next(10);
        Pitching nextPitching2 = nextPitching1.next(9);
        Frame frame = Frame.of(pitching);
        // when
        Integer score = frame.score();
        // then
        assertThat(score).isEqualTo(29);
    }

    @Test
    @DisplayName("strike 여부 확인")
    void strike() {
        // given
        Pitching pitching1 = Pitching.first(10);
        Frame frame1 = Frame.of(pitching1);
        Pitching pitching2 = pitching1.next(9);
        Frame frame2 = Frame.of(pitching2);
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
        Frame frame1 = Frame.of(pitching1, pitching2);
        Frame frame2 = Frame.of(pitching2, pitching3);
        // when
        boolean spare1 = frame1.spare();
        boolean spare2 = frame2.spare();
        // then
        assertThat(spare1).isTrue();
        assertThat(spare2).isFalse();
    }
}