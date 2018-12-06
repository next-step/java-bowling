package domain.status;

import domain.Pin;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MissTest {
    @Test
    public void Hit_string_확인() {
        Status hit = new Miss(Pin.firstPin(3));
        assertThat(hit.toString()).isEqualTo("7");
    }

}