package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class LaneTest {

    @Test
    @DisplayName("2명의 플레이어를 다루는 Bowling 을 만들었을 경우")
    void create() {
        Lane lane = Lane.from(asList(PlayerName.from("LDH"), PlayerName.from("TAK")));

        assertAll(
                () -> assertThat(lane.currentPlayer()).isEqualTo("LDH"),
                () -> assertThat(lane.bowlingGames()).hasSize(2),
                () -> assertThat(lane.isEnd()).isFalse()
        );
    }

}