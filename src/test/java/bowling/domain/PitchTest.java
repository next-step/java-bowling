package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PitchTest {
    @DisplayName("쓰러진 핀을 인자로 받아 인스턴스가 정상적으로 생성된다.")
    @Test
    void create() {
        Pitch pitch = new Pitch(8);
        assertThat(pitch).isEqualTo(new Pitch(8));
    }
}
