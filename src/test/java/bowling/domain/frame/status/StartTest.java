package bowling.domain.frame.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StartTest {

    @Test
    @DisplayName("갓 시작된 프레임의 출력")
    void getDescription() {
        Start start = new Start();

        assertThat(start.getDescription()).isEqualTo("");
    }

    @Test
    @DisplayName("10점 기록 시 Strike 반환 확인")
    void strikeResponse() {
        Start start = new Start();

        Status reponse = start.record(10);

        assertThat(reponse).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("10점 기록 시 Strike 반환 확인")
    void onPitchResponse() {
        Start start = new Start();

        Status reponse = start.record(7);

        assertThat(reponse).isInstanceOf(OnSecondPitch.class);
    }
}
