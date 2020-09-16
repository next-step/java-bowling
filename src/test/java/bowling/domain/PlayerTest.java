package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("플레이어 테스트")
public class PlayerTest {

    @DisplayName("생성 테스트")
    @Test
    public void create() {
        assertThat(new Player("ljy")).isEqualTo(new Player("ljy"));
    }

    @DisplayName("이름 유효성 테스트")
    @Test
    public void validate_name() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Player("e200uk"));
    }

}
