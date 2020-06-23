package bowling.domain.pitch;

import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalPitchesTest {

    @DisplayName("이전 투구가 Strike라면, pitch.next가 아닌 Pitch.initiate를 통해 객체를 생성하기 때문에 연속 스트라이크 가능")
    @Test
    public void throwBall_스트라이크_2번() {
        FinalPitches finalPitches = new FinalPitches();
        finalPitches.throwBall(Score.valueOf(10));

        assertThatCode(() -> {
            finalPitches.throwBall(Score.valueOf(10));
        }).doesNotThrowAnyException();
    }

    @DisplayName("최대 3연속 스트라이크가 가능함")
    @Test
    public void throwBall_스트라이크_3번() {
        FinalPitches finalPitches = new FinalPitches();
        finalPitches.throwBall(Score.valueOf(10));
        finalPitches.throwBall(Score.valueOf(10));

        assertThatCode(() -> {
            finalPitches.throwBall(Score.valueOf(10));
        }).doesNotThrowAnyException();
    }

    @DisplayName("이전 투구가 Spare라면 pitch.next가 아닌 Pitch.initiate를 통해 객체를 생성하기 때문에 보너스 기회를 가짐")
    @Test
    public void throwBall_스페어() {
        FinalPitches finalPitches = new FinalPitches();
        finalPitches.throwBall(Score.valueOf(3));
        finalPitches.throwBall(Score.valueOf(7));

        assertThatCode(() -> {
            finalPitches.throwBall(Score.valueOf(5));
        }).doesNotThrowAnyException();
    }

    @DisplayName("추가 투구 시도시 예외 발생 : 스페어나 스트라이크가 없을 때")
    @ParameterizedTest
    @CsvSource({"0, 0", "3, 3", "4, 0", "0, 4", "1, 5"})
    public void throwBall_예외(int firstScore, int secondScore) {
        FinalPitches finalPitches = new FinalPitches();
        finalPitches.throwBall(Score.valueOf(firstScore));
        finalPitches.throwBall(Score.valueOf(secondScore));

        assertThatThrownBy(() -> {
            finalPitches.throwBall(Score.valueOf(5));
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_FINAL_PITCH_TRY);
    }

    @DisplayName("추가 투구 시도시 예외 발생 : 3번을 초과하여 투구 시도하는 경우")
    @Test
    public void throwBall_예외_4번투구() {
        FinalPitches finalPitches = new FinalPitches();
        for (int i = 0; i < 3; i++) {
            finalPitches.throwBall(Score.valueOf(10));
        }
        assertThatThrownBy(() -> {
            finalPitches.throwBall(Score.valueOf(10));
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_FINAL_PITCH_TRY);
    }
}
