package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {
    @Test
    @DisplayName("모든 핀이 쓰러지면 스트라이크(X)가 결과로 온다")
    public void rollAndGetStrikeWhenAllPinsAreFalledFirstTime() {
        Bowling bowling = new Bowling();
        List<String> result = bowling.roll(10);
        assertThat(result.get(0)).isEqualTo("X");
    }
}
