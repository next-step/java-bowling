package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("마지막 상태에서 여러 상태를 담기 위한 복합 상태")
class ComplexStateTest {

    @DisplayName("복합 상태를 초기화 하는데는 아무런 정보가 필요없다")
    @Test
    void init() {
        assertThat(ComplexState.init()).isInstanceOf(ComplexState.class);
    }

}
