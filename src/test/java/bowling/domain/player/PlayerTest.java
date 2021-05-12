package bowling.domain.player;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.exception.NameNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @DisplayName("Player 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        String stringName = "kwj";
        Name name = new Name(stringName);

        // when
        Player firstPlayer = new Player(stringName);
        Player secondPlayer = new Player(name);

        // then
        assertAll(
                () -> assertThat(firstPlayer).isNotNull(),
                () -> assertThat(secondPlayer).isNotNull()
        );

    }

    @DisplayName("Player 인스턴스 생성할 때 null 입력시 예외처리 테스트")
    @Test
    void 검증() {
        // given
        Name name = null;

        // when
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(NameNullPointerException.class)
                .hasMessage("Name 인스턴스가 null 입니다.");
    }

    @DisplayName("Player 인스턴스가 종료 여부를 반환하는 기능 테스트")
    @Test
    void 종료_여부() {
        // given
        Name name = new Name("kwj");

        // when
        Player player = new Player(name);
        boolean actual = player.isFinish();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("Player 인스턴스가 게임을 시작하는 기능 테스트")
    @Test
    void 기능_투구() {
        // given
        Name name = new Name("kwj");

        // when
        Player player = new Player(name);
        for (int i = 1; i <= 12; i++) {
            player.bowl(Pins.full());
        }

        // then
        assertThat(player.isFinish()).isTrue();
    }

    @DisplayName("Player 인스턴스가 현재 투구 순서(라운드)를 반환하는 기능 테스트")
    @Test
    void 반환_현재_순서() {
        // given
        Name name = new Name("kwj");

        // when
        Player player = new Player(name);

        // then
        assertThat(player.sequence()).isEqualTo(1);
    }

    @DisplayName("Player 인스턴스가 이름을 반환하는 기능 테스트")
    @Test
    void 반환_이름() {
        // given
        String expected = "kwj";
        Name name = new Name(expected);

        // when
        Player player = new Player(name);

        // then
        assertThat(player.name()).isEqualTo(expected);
    }

    @DisplayName("Player 인스턴스가 자신의 점수 목록을 반환하는 기능 테스트")
    @Test
    void 반환_점수_목록() {
        // given
        String expected = "kwj";
        Name name = new Name(expected);

        // when
        Player player = new Player(name);

        // then
        assertThat(player.name()).isEqualTo(expected);
    }

    @DisplayName("Frames 인스턴스가 프레임별 점수를 리스트를 반환하는지 테스트")
    @Test
    void 반환_scores() {
        // given
        Player firstPlayer = new Player("one");
        Player secondPlayer = new Player("two");
        Player thirdPlayer = new Player("thr");

        // when
        while (!firstPlayer.isFinish()) {
            firstPlayer.bowl(Pins.valueOf(1));
        }
        while (!secondPlayer.isFinish()) {
            secondPlayer.bowl(Pins.valueOf(5));
        }
        while (!thirdPlayer.isFinish()) {
            thirdPlayer.bowl(Pins.valueOf(10));
        }

        // when
        assertAll(
                () -> assertThat(firstPlayer.scores().stream().mapToInt(Score::score).sum()).isEqualTo(20),
                () -> assertThat(secondPlayer.scores().stream().mapToInt(Score::score).sum()).isEqualTo(150),
                () -> assertThat(thirdPlayer.scores().stream().mapToInt(Score::score).sum()).isEqualTo(300)
        );

    }
}