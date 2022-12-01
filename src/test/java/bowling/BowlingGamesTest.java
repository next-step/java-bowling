package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class BowlingGamesTest {

    @Test
    void 생성() {
        List<UserName> userNames = List.of(new UserName("aaa"), new UserName("bbb"), new UserName("ccc"));
        BowlingGames bowlingGames = BowlingGames.start(userNames);

        assertAll(
            () -> assertThat(bowlingGames.isFinished()).isFalse(),
            () -> assertThat(bowlingGames.getValues().size()).isEqualTo(3),
            () -> assertThat(bowlingGames.currentTurnOfUser().getUserName().getName()).isEqualTo("aaa")
        );
    }
}
