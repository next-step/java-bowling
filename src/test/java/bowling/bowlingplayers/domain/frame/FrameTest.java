package bowling.bowlingplayers.domain.frame;

import bowling.bowlingplayers.domain.pitching.Pitching;
import bowling.bowlingplayers.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

    @Test
    @DisplayName("Frame 생성")
    void create() {
        // given
        Pitching pitching = Pitching.of(9);
        // when
        Frame frame = new Frame(pitching);
        // then
        assertThat(frame).isEqualTo(new Frame(Pitching.of(9)));
    }

    @ParameterizedTest(name = "SecondPitching 추가 [{index}] {0}, {1}")
    @CsvSource({"1, 8"})
    void addSecondPitching(int firstPins, int secondPins) {
        // given
        Pitching firstPitching = Pitching.of(firstPins);
        Frame frame = new Frame(firstPitching);
        // when
        Pitching secondPitching = firstPitching.next(secondPins);
        frame.pitch(secondPitching);
        // then
        assertThat(frame).isEqualTo(new Frame(firstPitching, secondPitching));
    }

    @ParameterizedTest(name = "SecondPitching 추가 실패 : 합계 10 초과")
    @CsvSource({"10, 1", "9, 2"})
    void create_fail(int firstPins, int secondPins) {
        // given
        Pitching firstPitching = Pitching.of(firstPins);
        Pitching secondPitching = firstPitching.next(secondPins);
        Frame frame = new Frame(firstPitching);
        // when, then
        assertThatThrownBy(() -> new Frame(firstPitching, secondPitching))
                .isInstanceOf(CustomException.class);
        assertThatThrownBy(() -> frame.pitch(secondPitching))
                .isInstanceOf(CustomException.class);
    }

    @ParameterizedTest(name = "점수 계산 [{index}] {0}, {1}, {2} : {3}")
    @CsvSource({"10, 10, 10, 30", "3, 7, 10, 20", "2, 7, 10, 9"})
    void score(int firstPins, int secondPins, int thirdPins, int result) {
        // given
        Pitching pitching = Pitching.first(firstPins);
        Pitching nextPitching1 = pitching.next(secondPins);
        nextPitching1.next(thirdPins);
        Frame frame = new Frame(pitching);
        if (firstPins < 10) {
            frame.pitch(nextPitching1);
        }
        // when
        int score = frame.score();
        // then
        assertThat(score).isEqualTo(result);
    }

    @Test
    @DisplayName("strike 여부 확인")
    void strike() {
        // given
        Pitching pitching1 = Pitching.first(10);
        Frame frame1 = new Frame(pitching1);
        Pitching pitching2 = pitching1.next(9);
        Frame frame2 = new Frame(pitching2);
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
        Frame frame1 = new Frame(pitching1, pitching2);
        Frame frame2 = new Frame(pitching2, pitching3);
        // when
        boolean spare1 = frame1.spare();
        boolean spare2 = frame2.spare();
        // then
        assertThat(spare1).isTrue();
        assertThat(spare2).isFalse();
    }

    @ParameterizedTest(name = "해당 Frame 까지의 totalScore : All strike")
    @CsvSource({"10, 1, 30", "10, 10, 300"})
    void total_score_all_strike(int pins, int scoreFrame, int result) {
        // given
        Pitching pitching = Pitching.first(pins);
        Frame frame = new Frame(pitching);

        for (int i = 0; i < scoreFrame - 1; i++) {
            pitching = pitching.next(pins);
            frame = new Frame(pitching, frame);
        }
        pitching = pitching.next(pins);
        pitching.next(pins);
        // when
        int totalScore = frame.totalScore();
        // then
        assertThat(totalScore).isEqualTo(result);
    }

    @ParameterizedTest(name = "해당 Frame 까지의 totalScore : no strike")
    @CsvSource({"1, 8, 1, 9", "1, 8, 10, 90"})
    void total_score_no_strike(int pins1, int pins2, int scoreFrame, int result) {
        // given
        Pitching pitching = Pitching.first(pins1);
        Frame frame = new Frame(pitching);
        Pitching nextPitching = pitching.next(pins2);
        frame.pitch(nextPitching);

        for (int i = 0; i < scoreFrame - 1; i++) {
            nextPitching = nextPitching.next(pins1);
            frame = new Frame(nextPitching, frame);
            nextPitching = nextPitching.next(pins2);
            frame.pitch(nextPitching);
        }
        // when
        int totalScore = frame.totalScore();
        // then
        assertThat(totalScore).isEqualTo(result);
    }
}