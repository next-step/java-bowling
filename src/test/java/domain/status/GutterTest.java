package domain.status;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GutterTest {

    @Test
    public void 거터_string_확인() {
        Status gutter = Gutter.getInstance();
        assertThat(gutter.toString()).isEqualTo("-");
    }
}