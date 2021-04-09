package bowling.domain;

import bowling.domain.frame.FrameNumber;
import bowling.dto.FrameResults;
import bowling.dto.PlayResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    void add_pin_count() {
        String name = "tes";
        int pinCount = 5;
        Player player = new Player(name, 10);
        player.addPinCount(pinCount);

        PlayResult playResult = player.playResult();
        FrameResults frameResults = playResult.framesResult();
        List<Integer> pinCountsOfFirstFrame = frameResults.normalFrameResults().get(0).pinCounts();

        assertThat(playResult.playerName()).isEqualTo(name);
        assertThat(pinCountsOfFirstFrame).containsExactlyInAnyOrder(pinCount);
    }

    @Test
    void is_done_true() {
        int totalNumberOfFrame = 3;
        Player player = new Player("tes", totalNumberOfFrame);
        int totalNumberOfStrike = 5;
        int strikePinCount =10;

        for (int i = 0; i < totalNumberOfStrike; i++) {
            player.addPinCount(strikePinCount);
        }

        assertThat(player.isDone()).isTrue();
        assertThat(player.currentFrameNumber()).isEqualTo(new FrameNumber(totalNumberOfFrame));
    }

    @Test
    void is_done_false() {
        int totalNumberOfFrame = 3;
        Player player = new Player("tes", totalNumberOfFrame);
        int expectedNumberOfFrame = 2;
        int strikePinCount = 10;

        for (int i = 0; i < expectedNumberOfFrame - 1; i++) {
            player.addPinCount(strikePinCount);
        }

        assertThat(player.isDone()).isFalse();
        assertThat(player.currentFrameNumber()).isEqualTo(new FrameNumber(expectedNumberOfFrame));
    }
}
