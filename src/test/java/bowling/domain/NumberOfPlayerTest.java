package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("플레이어 수 객체 테스트")
public class NumberOfPlayerTest {

    @DisplayName("생성 테스트")
    @Test
    public void create() {
        assertThat(NumberOfPlayer.of(3).getNumberOfPlayer()).isEqualTo(3);
    }

    @DisplayName("생성 유효성 테스트: 0 이하 입력")
    @Test
    public void create_valid() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> NumberOfPlayer.of(-1));
    }

}
