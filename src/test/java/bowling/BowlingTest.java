package bowling;

import bowling.domain.Bowling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {
    private Bowling bowling;

    @BeforeEach
    public void setup() {
        bowling = new Bowling();
    }

    @Test
    @DisplayName("모든 핀이 쓰러지면 스트라이크(X)가 결과로 오고 그 다음 프레임으로 넘어간다")
    public void rollAndGetStrikeWhenAllPinsAreFallenFirstTime() {
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

    @Test
    @DisplayName("10프레임에 3번 투구 모두 스트라이크이면 X|X|X를 반환한다")
    public void rollThreeStrikeWhenLastFrame() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            result = bowling.roll(10);
        }

        assertThat(result.get(9)).isEqualTo("X|X|X");
    }

    @Test
    @DisplayName("10프레임에 스페어 후 보너스 투구를 하면 8|/|7를 반환한다")
    public void rollSpareWhnLastFrame() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            bowling.roll(10);
        }
        bowling.roll(8);
        bowling.roll(2);
        result = bowling.roll(7);

        assertThat(result.get(9)).isEqualTo("8|/|7");
    }

    @Test
    @DisplayName("10프레임에 미스가 난다면 6|2를 반환한다")
    public void dname() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            bowling.roll(10);
        }
        bowling.roll(6);
        result = bowling.roll(2);

        assertThat(result.get(9)).isEqualTo("6|2");
    }

    @Test
    @DisplayName("10프레임에서 모두 투구하면 게임을 종료한다")
    public void allBowlWhenLastFrameIsGameEnd() {
        for (int i = 0; i < 12; i++) {
            bowling.roll(10);
        }
        assertThat(bowling.isGameEnd()).isTrue();
    }
}
