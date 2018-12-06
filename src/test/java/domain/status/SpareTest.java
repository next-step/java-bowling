package domain.status;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {

    @Test
    public void 스트라이크_string_확인() {
        Status spare = Spare.getInstance();
        assertThat(spare.toString()).isEqualTo("/");
    }
}