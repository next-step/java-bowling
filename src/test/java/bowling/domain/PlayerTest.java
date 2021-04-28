package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

public class PlayerTest {
    Player player;

    @BeforeEach
    void setUp() {
        player = new Player("KSB");
    }

    @Test
    @DisplayName("이름을 인자로 받아 플레이어를 생성한다.")
    public void create() throws Exception {
        Player player = new Player("KSB");
        assertThat(player).isEqualTo(new Player("KSB"));
    }

    @Test
    @DisplayName("인자로 받은 인덱스의 프레임이 완료됐을 경우, 참을 반환한다.")
    public void hasFinishedFrame() throws Exception {
        //given
        player.throwBowl("10");

        //when
        boolean isFirstFrameFinished = player.hasFinishedFrame(0);
        boolean isSecondFrameFinished = player.hasFinishedFrame(1);

        then(isFirstFrameFinished).isTrue();
        then(isSecondFrameFinished).isFalse();
    }
}
