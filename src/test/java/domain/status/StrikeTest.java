package domain.status;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {

    @Test
    public void 스트라이크_string_확인() {
        Status strike = Strike.getInstance();
        assertThat(strike.toString()).isEqualTo("X");
    }
}