package bowling.domain.frame;

import bowling.domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GameFrames Test")
class FramesGroupTest {

    @Test
    @DisplayName("다음 턴 플레이어 확인")
    void nextTurnPlayer() {
        //given
        FramesGroup framesGroup = FramesGroup.of(Arrays.asList("AAA", "BBB", "CCC"));

        //when
        framesGroup.inputNumber(10);
        Player actualPlayer = framesGroup.nextPlayer();

        //then
        assertThat(actualPlayer).isEqualTo(Player.valueOf("BBB"));
    }

    @Test
    @DisplayName("전체 프레임 끝났다면 - isFinished() => 참")
    void framesAllEndThenFinished() {
        //given
        Map<Player, Frames> givenFramesGroup = new LinkedHashMap<Player, Frames>() {{
            put(Player.valueOf("AAA"), Frames.of(Collections.singletonList(FinalFrame.of(10, 10, 10))));
            put(Player.valueOf("BBB"), Frames.of(Collections.singletonList(FinalFrame.of(5, 1))));
        }};

        // when
        FramesGroup framesGroup = FramesGroup.of(givenFramesGroup);
        boolean actual = framesGroup.isFinished();

        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("종료 안된 프레임이 있다면 - isFinished() => 거짓")
    void hasNotEndFramesThenNotFinished() {
        //given
        Map<Player, Frames> givenFramesGroup = new LinkedHashMap<Player, Frames>() {{
            put(Player.valueOf("AAA"), Frames.of(Collections.singletonList(FinalFrame.of(10, 10))));
            put(Player.valueOf("BBB"), Frames.of(Collections.singletonList(FinalFrame.of(5, 1))));
        }};

        // when
        FramesGroup framesGroup = FramesGroup.of(givenFramesGroup);
        boolean actual = framesGroup.isFinished();

        //then
        assertThat(actual).isFalse();
    }
}