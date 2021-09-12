package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.exception.CanNotGetNextTurnException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("FramesGroupTest Test")
class FramesGroupTest {

    Map<Player, Frames> givenFramesGroup;

    @BeforeEach
    void setUp() {
        givenFramesGroup = new LinkedHashMap<Player, Frames>() {{
            put(Player.valueOf("AAA"), Frames.of(Collections.singletonList(FinalFrame.of(10, 10, 10))));
        }};
    }

    @Test
    @DisplayName("다음 턴 플레이어 확인")
    void nextTurnPlayer() {
        //given
        givenFramesGroup.put(Player.valueOf("BBB"), Frames.of(Collections.singletonList(FinalFrame.of(10))));
        FramesGroup framesGroup = FramesGroup.of(givenFramesGroup);

        //when
        framesGroup.inputNumber(10);
        String actualPlayer = framesGroup.getNextPlayerName();

        //then
        assertThat(actualPlayer).isEqualTo("BBB");
    }

    @Test
    @DisplayName("전체 프레임 끝났다면 - isFinished() => 참")
    void framesAllEndThenFinished() {
        //given
        givenFramesGroup.put(Player.valueOf("BBB"), Frames.of(Collections.singletonList(FinalFrame.of(5, 1))));
        givenFramesGroup.put(Player.valueOf("CCC"), Frames.of(Collections.singletonList(FinalFrame.of(0, 0))));

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
        givenFramesGroup.put(Player.valueOf("BBB"), Frames.of(Collections.singletonList(FinalFrame.of(10))));

        // when
        FramesGroup framesGroup = FramesGroup.of(givenFramesGroup);
        boolean actual = framesGroup.isFinished();

        //then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("모든 게임이 끝난 후 값을 입력할 경우 Exception")
    void exception() {
        // given
        // when
        FramesGroup framesGroup = FramesGroup.of(givenFramesGroup);

        // then
        assertThatThrownBy(() ->
                framesGroup.inputNumber(0)
        ).isInstanceOf(CanNotGetNextTurnException.class);
    }
}