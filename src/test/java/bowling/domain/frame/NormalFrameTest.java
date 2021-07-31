package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("일반 프레임 테스트")
class GeneralFrameTest {

    @DisplayName("일반 프레임은 초기화시 Preparation 상태값을 가진다")
    @Test
    void init() {
        assertThat(GeneralFrame.init()).isInstanceOf(GeneralFrame.class);
    }
}
