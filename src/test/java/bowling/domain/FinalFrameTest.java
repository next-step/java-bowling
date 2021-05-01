package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class FinalFrameTest {

    private FinalFrame finalFrame;

    @BeforeEach
    public void setUp() {
        NormalFrame normalFrame = NormalFrame.first();

        for (int i = 0; i < 8; i++) {
            normalFrame.pitch(new Pitch(10));
            normalFrame = (NormalFrame) normalFrame.next();
        }

        normalFrame.pitch(new Pitch(10));
        finalFrame = (FinalFrame)normalFrame.next();
    }

    @Test
    @DisplayName("마지막 NormalFrame의 다음 프레임은 FinalFrame")
    void next_lastNormalFrame() {
        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        for (int i = 0; i < 8; i++) {
            normalFrame.pitch(new Pitch(10));
            normalFrame = (NormalFrame) normalFrame.next();
        }

        normalFrame.pitch(new Pitch(10));
        Frame finalFrame = normalFrame.next();

        // then
        assertThat(finalFrame).isInstanceOf(FinalFrame.class);
    }

    @Test
    @DisplayName("마지막 프레임 - 스트라이크일 경우 보너스 투구 가능")
    void bonusPitch_byStrike() {
        // given when
        finalFrame.pitch(new Pitch(10));
        finalFrame.pitch(new Pitch(5));

        // then
        assertThat(finalFrame.isFinished()).isFalse();
    }

    @Test
    @DisplayName("마지막 프레임 - 스페어일 경우 보너스 투구 가능")
    void bonusPitch_bySpare() {
        // given when
        finalFrame.pitch(new Pitch(2));
        finalFrame.pitch(new Pitch(8));
        finalFrame.pitch(new Pitch(7));

        // then
        assertThat(finalFrame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임 - 미스일 경우 보너스 투구 불가")
    void bonusPitch_byMiss() {
        // given when
        finalFrame.pitch(new Pitch(2));
        finalFrame.pitch(new Pitch(7));

        // then
        assertThat(finalFrame.isFinished()).isTrue();
        assertThatIllegalStateException()
                .isThrownBy(() -> finalFrame.pitch(new Pitch(8)))
                .withMessageMatching("종료된 프레임입니다.");
    }

    @Test
    @DisplayName("스트라이크 점수판 출력")
    void scoreBoards_strike() {
        // given when
        finalFrame.pitch(new Pitch(10));

        // then
        assertThat(finalFrame.getScoreBoards().size()).isEqualTo(1);
        assertThat(finalFrame.getScoreBoards()).isEqualTo(Collections.singletonList("X"));
    }

    @Test
    @DisplayName("스페어 점수판 출력")
    void scoreBoards_spare() {
        // given when
        finalFrame.pitch(new Pitch(7));
        finalFrame.pitch(new Pitch(3));

        // then
        assertThat(finalFrame.getScoreBoards().size()).isEqualTo(2);
        assertThat(finalFrame.getScoreBoards()).isEqualTo(Arrays.asList("7", "/"));
    }

    @Test
    @DisplayName("미스 점수판 출력")
    void scoreBoards_miss() {
        // given when
        finalFrame.pitch(new Pitch(7));
        finalFrame.pitch(new Pitch(2));

        // then
        assertThat(finalFrame.getScoreBoards().size()).isEqualTo(2);
        assertThat(finalFrame.getScoreBoards()).isEqualTo(Arrays.asList("7", "2"));
    }

    @ParameterizedTest
    @CsvSource(value = {"7,0,7,-", "0,7,-,7", "0,0,-,-"})
    @DisplayName("거터 점수판 출력")
    void scoreBoards_gutter(int pitchCount1, int pitchCount2, String score1, String score2) {
        // given when
        finalFrame.pitch(new Pitch(pitchCount1));
        finalFrame.pitch(new Pitch(pitchCount2));

        // then
        assertThat(finalFrame.getScoreBoards().size()).isEqualTo(2);
        assertThat(finalFrame.getScoreBoards()).isEqualTo(Arrays.asList(score1, score2));
    }

    @ParameterizedTest
    @CsvSource(value = {"10,X", "9,9", "0,-"})
    @DisplayName("점수판출력 - 스트라이크 and 보너스 투구")
    void scoreBoard_bonusPitch_strike(int bonusPitchCount, String bonusScore) {
        // given when
        finalFrame.pitch(new Pitch(10));
        finalFrame.pitch(new Pitch(bonusPitchCount));

        // then
        assertThat(finalFrame.getScoreBoards().size()).isEqualTo(2);
        assertThat(finalFrame.getScoreBoards()).isEqualTo(Arrays.asList("X", bonusScore));
    }

    @Test
    @DisplayName("마지막프레임 점수계산 - 한 번의 투구")
    void score_onePitch() {
        // given when
        finalFrame.pitch(new Pitch(7));

        // then
        assertThat(finalFrame.score()).isEqualTo(7);
    }

    @Test
    @DisplayName("마지막프레임 점수계산 - 두 번의 투구")
    void score_twoPitch() {
        // given when
        finalFrame.pitch(new Pitch(7));
        finalFrame.pitch(new Pitch(1));

        // then
        assertThat(finalFrame.score()).isEqualTo(8);
    }

    @Test
    @DisplayName("점수계산 - 스페어 처리")
    void score_spare() {
        // given when
        finalFrame.pitch(new Pitch(7));
        finalFrame.pitch(new Pitch(3));

        // then
        assertThat(finalFrame.score()).isEqualTo(10);
    }

    @Test
    @DisplayName("점수계산 - 스페어 처리와 보너스 투구")
    void score_spareAndBonusPitch() {
        // given when
        finalFrame.pitch(new Pitch(7));
        finalFrame.pitch(new Pitch(3));
        finalFrame.pitch(new Pitch(2));

        // then
        assertThat(finalFrame.score()).isEqualTo(12);
    }

    @Test
    @DisplayName("점수계산 - 스페어 처리와 스트라이크")
    void score_spareAndStrike() {
        // given when
        finalFrame.pitch(new Pitch(7));
        finalFrame.pitch(new Pitch(3));
        finalFrame.pitch(new Pitch(10));

        // then
        assertThat(finalFrame.score()).isEqualTo(20);
    }

    @Test
    @DisplayName("점수계산 - 더블 스트라이크")
    void score_doubleStrike() {
        // given when
        finalFrame.pitch(new Pitch(10));
        finalFrame.pitch(new Pitch(10));

        // then
        assertThat(finalFrame.score()).isEqualTo(20);
    }

    @Test
    @DisplayName("점수계산 - 트리플 스트라이크")
    void score_tripleStrike() {
        // given when
        finalFrame.pitch(new Pitch(10));
        finalFrame.pitch(new Pitch(10));
        finalFrame.pitch(new Pitch(10));

        // then
        assertThat(finalFrame.score()).isEqualTo(30);
    }

}