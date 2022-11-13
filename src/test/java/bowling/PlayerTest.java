package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.Player;
import bowling.dto.PlayerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    @DisplayName("영문자가 아닌 이름")
    void test1() {
        // given
        String name = "123";
        // when
        // then
        assertThatThrownBy(() -> {
            new Player(name);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름이 세글자가 아님")
    void test2() {
        // given
        String name = "a";
        // when
        // then
        assertThatThrownBy(() -> {
            new Player(name);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("처음 시작하면 1번 프레임부터")
    void test3() {
        Player player = new Player("aaa");
        assertThat(player.frameOrderToThrow()).isEqualTo(1);
    }

    @Test
    @DisplayName("10개를 쓰러트리면 2번 프레임으로 넘어간다")
    void test4() {
        Player player = new Player("aaa");
        player.throwBall(10);
        assertThat(player.frameOrderToThrow()).isEqualTo(2);
    }

    @Test
    @DisplayName("다 쓰러트리면 다음 순서는 -1")
    void test5() {
        Player player = new Player("abc");
        player.throwBall(10);
        player.throwBall(10);
        player.throwBall(10);
        player.throwBall(10);
        player.throwBall(10);
        player.throwBall(10);
        player.throwBall(10);
        player.throwBall(10);
        player.throwBall(10);
        player.throwBall(5);
        player.throwBall(3);
        assertThat(player.frameOrderToThrow()).isEqualTo(-1);
    }

    @Test
    @DisplayName("처음 시작하면 모든 찬스가 비어있음")
    void test6() {
        Player player = new Player("abc");
        PlayerDto status = player.status();
        for (Frame frame : status.frame()) {
            assertThat(frame.chances().chances()).isEmpty();
        }
    }

}
