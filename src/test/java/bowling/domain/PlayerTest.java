package bowling.domain;

import bowling.domain.exception.FinishGameException;
import bowling.domain.exception.NameLengthExceededException;
import bowling.domain.frames.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("생성")
    void create() {
        assertEquals(new Player("pjs"), new Player("pjs"));
    }

    @Test
    @DisplayName("생성 - 이름 5글자일시 에러")
    void create_name_exception() {
        assertThrows(NameLengthExceededException.class, () -> new Player("kimdonggyu"));
    }

    @Test
    @DisplayName("생성 - 이름 확인")
    void create_name() {
        Player player = new Player("pjs");
        assertEquals(player.getName(), Name.of("pjs"));
    }

    @Test
    @DisplayName("생성 - finish false")
    void create_finish_false() {
        Player player = new Player("pjs");
        assertFalse(player.isFinish());
    }

    @Test
    @DisplayName("생성-  finish index 는 0")
    void create_finish_index() {
        Player player = new Player("pjs");
        assertEquals(player.round(), 0);
    }

    @Test
    @DisplayName("투구 - 숫자가 아닐경우 에러")
    void roll_text_exception() {
        Player player = new Player("pjs");
        assertThrows(NumberFormatException.class, () -> player.roll("asdasd"));
    }

    @Test
    @DisplayName("투구 - 숫자 범위 초과할 경우 에러")
    void roll_number_exception() {
        Player player = new Player("pjs");
        assertThrows(IllegalArgumentException.class, () -> player.roll("11"));
    }

    @Test
    @DisplayName("마지막 프레임 - 스페어 -> 스트라이크")
    void finish_spare_to_strike() {
        // given
        Player player = new Player(Name.of("pjs"), new Frames());
        normalFrameRoll(player);

        // when
        player.roll("1");
        player.roll("9");
        player.roll("10");

        // then
        assertTrue(player.isFinish());
    }

    @Test
    @DisplayName("마지막 프레임 - 스페어 -> miss")
    void finish_spare_to_miss() {
        // given
        Player player = new Player(Name.of("pjs"), new Frames());
        normalFrameRoll(player);

        // when
        player.roll("1");
        player.roll("9");
        player.roll("0");

        // then
        assertTrue(player.isFinish());
    }

    @Test
    @DisplayName("마지막 프레임 - 스트라이크 -> 스트라이크-> miss")
    void finish_two_strike_to_miss() {
        // given
        Player player = new Player(Name.of("pjs"), new Frames());
        normalFrameRoll(player);

        // when
        player.roll("10");
        player.roll("10");
        player.roll("0");

        // then
        assertTrue(player.isFinish());
    }

    @Test
    @DisplayName("마지막 프레임 - 스트라이크 -> 스트라이크 -> 스트라이크")
    void finish_three_strike() {
        // given
        Player player = new Player(Name.of("pjs"), new Frames());
        normalFrameRoll(player);

        // when
        player.roll("10");
        player.roll("10");
        player.roll("10");

        // then
        assertTrue(player.isFinish());
    }

    @Test
    @DisplayName("프레임이 끝났을때 굴리면 에러")
    void finish_roll_exception() {
        Player player = new Player(Name.of("pjs"), new Frames());
        normalFrameRoll(player);

        player.roll("10");
        player.roll("10");
        player.roll("10");

        // when & then
        assertThrows(FinishGameException.class, () -> player.roll("5"));
    }

    private void normalFrameRoll(final Player player) {
        IntStream.range(0, 9)
                .mapToObj(i -> "10")
                .forEach(player::roll);
    }
}
