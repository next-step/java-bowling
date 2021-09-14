package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreStringTest {

    @Test
    @DisplayName("출력용 점수 문자열 테스트 - 스페어")
    void getOutputStringTest() {
        ScoreString scoreString = new ScoreString();

        String firstString = scoreString.getOutputString();

        scoreString = scoreString.append(new Score(1));
        String secondString = scoreString.getOutputString();

        scoreString = scoreString.append(new Score(9), FrameResult.SPARE);
        String thirdString = scoreString.getOutputString();

        assertThat(firstString).isEqualTo("");

        assertThat(secondString).isEqualTo("1");

        assertThat(thirdString).isEqualTo("1|/");
    }

    @Test
    @DisplayName("출력용 점수 문자열 테스트 - 스트라이크")
    void getOutputStringTest2() {
        ScoreString scoreString = new ScoreString();

        scoreString = scoreString.append(new Score(10), FrameResult.STRIKE);
        assertThat(scoreString.getOutputString()).isEqualTo("X");
    }

    @Test
    @DisplayName("출력용 점수 문자열 테스트 - 거터, 일반")
    void getOutputStringTest3() {
        ScoreString scoreString = new ScoreString();

        scoreString = scoreString.append(new Score(0), FrameResult.MISS);
        String firstString = scoreString.getOutputString();

        scoreString = scoreString.append(new Score(6), FrameResult.MISS);
        String secondString = scoreString.getOutputString();

        assertThat(firstString).isEqualTo("-");

        assertThat(secondString).isEqualTo("-|6");
    }
}
