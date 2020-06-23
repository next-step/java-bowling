package bowling.pitch;

import bowling.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

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
}
