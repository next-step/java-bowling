package bowling;

import bowling.domain.Player;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어 이름이 3글자 아니면 에러발생 테스트")
    void notThreeLetters() {
        assertThatThrownBy(() -> new Player("qwer"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어 이름이 영문이 아니면 에러발생 테스트")
    void notEnglishLetters() {
        assertThatThrownBy(() -> new Player("황포비"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어 이름이 비어있으면 에러발생 테스트")
    void emptyLetters() {
        assertThatThrownBy(() -> new Player(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
