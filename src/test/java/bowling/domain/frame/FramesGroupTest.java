package bowling.domain.frame;

import bowling.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GameFrames Test")
class FramesGroupTest {

    @Test
    void generate() {
        //given
        FramesGroup framesGroup = FramesGroup.of(Arrays.asList("AAA", "BBB"));
        framesGroup.inputNumber(10);

        //when
        Player actualPlayer = framesGroup.nextPlayer();

        //then
        assertThat(actualPlayer).isEqualTo(Player.valueOf("BBB"));
    }

}