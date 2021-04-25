package bowling;

import bowling.domain.Player;
import bowling.domain.exception.CannotBowlException;
import bowling.domain.exception.NameLengthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("son");
    }

    @Test
    @DisplayName("첫구 결과가 Strike일 때 종료 확인 테스트")
    void bowlToStrikeTest() {
        player.bowl(10,1);
        assertThat(player.isNthFrameFinished(1)).isTrue();
    }

    @Test
    @DisplayName("첫구 결과가 Continue일 때 종료 확인 테스트")
    void bowlToContinueTest() {
        player.bowl(5,1);
        assertThat(player.isNthFrameFinished(1)).isFalse();
    }

    @ParameterizedTest(name = "Player 이름 구성 예외 테스트")
    @ValueSource(strings = {"pobi", "to", "가나다"})
    void nameLengthExceptionTest(String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(NameLengthException.class)
                .hasMessage("이름은 영문 3글자로 이루어져야 합니다");
    }
}
