package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LaneTest {

    @Test
    @DisplayName("currentPlayerName 테스트")
    public void currentPlayerName() {
        // given
        PlayerCount playerCount = new PlayerCount(2);
        Players players = new Players(Arrays.asList("abc", "def"));
        Lane lane = new Lane(playerCount);
        String expectedPlayerName = "abc";

        // when
        lane.addPitch(new Pitch(1));
        lane.addPitch(new Pitch(9));
        lane.addPitch(new Pitch(10));
        String currentPlayerName = lane.currentPlayerName(players);
        boolean end = lane.isAllEnd();

        // then
        assertThat(currentPlayerName).isEqualTo(expectedPlayerName);
        assertThat(end).isEqualTo(false);
    }

    @Test
    @DisplayName("allFrames,isAllEnd 테스트")
    public void allFrames() {
        // given
        List<Frames> frames = Arrays.asList(allGutter(), perfectGame());
        Lane lane = new Lane(frames);
        int expectedSize = 2;

        // when
        List<Frames> allFrames = lane.allFrames();
        boolean end = lane.isAllEnd();

        // then
        assertThat(allFrames.size()).isEqualTo(expectedSize);
        assertThat(end).isEqualTo(true);
    }

    private Frames allGutter() {
        Frames frames = new Frames();
        IntStream.range(0, 20)
                .mapToObj(i -> new Pitch(0))
                .forEach(frames::addPitch);
        return frames;
    }

    private Frames perfectGame() {
        Frames frames = new Frames();
        IntStream.range(0, 12)
                .mapToObj(i -> new Pitch(10))
                .forEach(frames::addPitch);
        return frames;
    }

}