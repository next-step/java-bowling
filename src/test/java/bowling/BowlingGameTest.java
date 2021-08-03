package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링 게임을 표현하기 위한 클래스 테스트")
class BowlingGameTest {

    @DisplayName("볼링 게임은 참가자 이름을 가지고 초기화 한다")
    @Test
    void init() {
        List<String> names = Arrays.asList("NOK", "CHA", "KKH");
        assertThat(BowlingGame.init(names)).isInstanceOf(BowlingGame.class);
    }

}
