package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PitchingTest {

    @Test
    @DisplayName("트라이 객체 생성")
    void create() {
        Pitching currentPitching = new Pitching(0);
        assertThat(currentPitching).isEqualTo(new Pitching(0));
    }

    @Test
    @DisplayName("카운트 증가하고 증가한 try 객체 반환")
    void plusCount() {
        Pitching currentPitching = new Pitching(0);
        assertThat(currentPitching.plusCount()).isEqualTo(new Pitching(1));
    }

    @Test
    @DisplayName("마지막 카운트인지 확인")
    void isLastTry() {
        Pitching currentPitching = new Pitching(1);
        assertThat(currentPitching.isFirstPitching()).isTrue();
    }


}