package bowling.domain.turn;

import bowling.domain.score.TurnScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SecondTurnTest {
    @CsvSource({
            "10,0,false", // 첫번째 프레임과의 합계 점수가 10이어도 두번째 프레임의 Score가 Gutter 라면 스페어가 아니다.
            "0,10,true",
            "1,9,true",
            "9,1,true",
    })
    @ParameterizedTest
    @DisplayName("첫번째 프레임과의 합계 점수가 10일 경우 spare")
    void isSpareTest(int firstFrameScore, int secondFrameScore, boolean correct) {
        assertThat(
                new FirstTurn(TurnScore.of(firstFrameScore))
                        .secondTurn(TurnScore.of(secondFrameScore))
                        .isSpare()
        ).isEqualTo(correct);
    }
}
