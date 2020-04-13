package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PinTest {

    @DisplayName("쓰러 트린 핀에 올바른 값이 들어 왔는지 확인하고 아니라면 예외발생")
    @Test
    void validatePin() {
        assertAll(() -> assertThatIllegalArgumentException().isThrownBy(() -> {
                      new Pin(-1);
                  }),
                  () -> assertThatIllegalArgumentException().isThrownBy(() -> {
                      new Pin(11);
                  })
        );
    }
}
