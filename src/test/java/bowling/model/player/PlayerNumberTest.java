package bowling.model.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("볼링 게임 플레이어 번호 테스트")
public class PlayerNumberTest {

    @DisplayName("플레이어 번호가 1 미만이면 예외가 발생한다.")
    @Test
    void outOfRangePlayerNumberTest() {
        // given, when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PlayerNumber(0))
                .withMessage("플레이어 번호는 최소 1 이상이어야 합니다.");
    }

    @DisplayName("플레이어 번호가 1 이상이면 정상적으로 생성된다.")
    @Test
    void createPlayerNumberTest() {
        // given, when, then
        assertEquals(new PlayerNumber(1).value(), 1);
    }

    @DisplayName("플레이어 번호 값이 같으면 동등한 객체이다.")
    @Test
    void equalPlayerNumberTest() {
        // given, when, then
        assertEquals(new PlayerNumber(1), new PlayerNumber(1));
    }
}
