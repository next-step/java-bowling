package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PitchResultTest {

    private static final int BOWLING_PIN_COUNT = 10;

    @DisplayName("PitchResult 생성 테스트")
    @Test
    void pitchResultConstructorTest(){
        // when
        PitchResult pitchResult = PitchResult.from(BOWLING_PIN_COUNT);

        // then
        assertThat(pitchResult).isEqualTo(PitchResult.from(BOWLING_PIN_COUNT));
    }

    @DisplayName("총 볼링핀 갯수를 넘는 값을 투구 결과로 주입 시 Exception Test")
    @Test
    void makePitchResultsWithOverMaxBowlingPinCount(){

        assertThatIllegalArgumentException().isThrownBy(() -> {

            PitchResult.from(BOWLING_PIN_COUNT+1);

        }).withMessageContaining("투구 결과는 총 볼링핀의 갯수를 넘을 수 없습니다.");
    }
}
