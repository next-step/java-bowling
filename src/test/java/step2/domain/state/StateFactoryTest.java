package step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StateFactoryTest {

    @Test
    @DisplayName("준비 상태 객체 생성")
    void ready() {
        assertThat(StateFactory.ready()).isInstanceOf(State.class);
    }

}