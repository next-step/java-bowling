package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("준비 상태를 표현하는 상태 클래스 테스트")
class PreparationTest {

    @DisplayName("준비 상태는 초기화시 필요한 정보가 없다. 항상 동일한 상태이다.")
    @Test
    void init() {
        assertThat(Preparation.instance()).isInstanceOf(Preparation.class);
    }

}
