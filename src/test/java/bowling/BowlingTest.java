package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {
    @Test
    @DisplayName("모든 핀이 쓰러지면 스트라이크(X)가 결과로 오고 그 다음 프레임으로 넘어간다")
    public void rollAndGetStrikeWhenAllPinsAreFallenFirstTime() {
        Bowling bowling = new Bowling();
        List<String> result = bowling.roll(10);
        assertThat(result.get(0)).isEqualTo("X");

        result = bowling.roll(7);
        assertThat(result.get(1)).isEqualTo("7");

        result = bowling.roll(3);
        assertThat(result.get(1)).isEqualTo("7|/");
    }

    @Test
    @DisplayName("현재까지 진행된 프레임의 결과를 모두 가져온다")
    void createResultByFallenPinsCounts() {
        Bowling bowling = new Bowling();
        bowling.roll(10);
        bowling.roll(7);
        bowling.roll(3);
        bowling.roll(1);
        bowling.roll(8);
        bowling.roll(6);
        bowling.roll(0);

        List<String> result = bowling.createResult();

        assertThat(result.get(0)).isEqualTo("X");
        assertThat(result.get(1)).isEqualTo("7|/");
        assertThat(result.get(2)).isEqualTo("1|8");
        assertThat(result.get(3)).isEqualTo("6|-");
    }
}
