package bowling.domain.pitch;

import bowling.domain.exception.FinalPitchTryException;
import bowling.domain.score.PitchScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalPitchesTest {

    @DisplayName("이전 투구가 Strike라면, Pitch.next가 아닌 Pitch.initiate를 통해 객체를 생성하기 때문에 연속 스트라이크 가능")
    @Test
    public void throwBall_스트라이크_2번() {
        FinalPitches finalPitches = new FinalPitches();
        finalPitches.throwBall(PitchScore.valueOf(10));

        assertThatCode(() -> {
            finalPitches.throwBall(PitchScore.valueOf(10));
        }).doesNotThrowAnyException();
    }

    @DisplayName("최대 3연속 스트라이크가 가능함")
    @Test
    public void throwBall_스트라이크_3번() {
        FinalPitches finalPitches = new FinalPitches();
        finalPitches.throwBall(PitchScore.valueOf(10));
        finalPitches.throwBall(PitchScore.valueOf(10));

        assertThatCode(() -> {
            finalPitches.throwBall(PitchScore.valueOf(10));
        }).doesNotThrowAnyException();
    }

    @DisplayName("이전 투구가 Spare라면 Pitch.next가 아닌 Pitch.initiate를 통해 객체를 생성하기 때문에 보너스 기회를 가짐")
    @Test
    public void throwBall_스페어() {
        FinalPitches finalPitches = new FinalPitches();
        finalPitches.throwBall(PitchScore.valueOf(3));
        finalPitches.throwBall(PitchScore.valueOf(7));

        assertThatCode(() -> {
            finalPitches.throwBall(PitchScore.valueOf(5));
        }).doesNotThrowAnyException();
    }

    @DisplayName("추가 투구 시도시 예외 발생 : 스페어나 스트라이크가 없을 때")
    @ParameterizedTest
    @CsvSource({"0, 0", "3, 3", "4, 0", "0, 4", "1, 5"})
    public void throwBall_예외_스페어_스트라이크_없음(int firstScore, int secondScore) {
        FinalPitches finalPitches = new FinalPitches();
        finalPitches.throwBall(PitchScore.valueOf(firstScore));
        finalPitches.throwBall(PitchScore.valueOf(secondScore));

        assertThatThrownBy(() -> {
            finalPitches.throwBall(PitchScore.valueOf(5));
        }).isInstanceOf(FinalPitchTryException.class);
    }

    @DisplayName("추가 투구 시도시 예외 발생 : 3번을 초과하여 투구 시도하는 경우")
    @Test
    public void throwBall_예외_4번투구() {
        FinalPitches finalPitches = new FinalPitches();
        for (int i = 0; i < 3; i++) {
            finalPitches.throwBall(PitchScore.valueOf(10));
        }
        assertThatThrownBy(() -> {
            finalPitches.throwBall(PitchScore.valueOf(10));
        }).isInstanceOf(FinalPitchTryException.class);
    }
}
