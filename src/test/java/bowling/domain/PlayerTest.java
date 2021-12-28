package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.frame.BowlingProgressException;
import bowling.domain.progress.Closed;
import bowling.domain.progress.GeneralProgress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private static final Pin GUTTER = Pin.of(0);
    private static final Pin NORMAL = Pin.of(3);
    private static final Pin SPARE = Pin.of(7);
    private static final Pin STRIKE = Pin.of(10);

    private Player player;

    @BeforeEach
    void init() {
        player = new Player("PJS");
    }

    @Test
    @DisplayName("플레이어의 이름은, 3글자를 입력해야한다.")
    void createValidTest() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Player("TT"));
    }


    @Test
    @DisplayName("플레이어가 STRIKE를 수행하면, 해당 프레임은 닫힌다.")
    void playStrikeClosedTest() {
        assertThat(player.play(0, STRIKE).getProgress()).isInstanceOf(Closed.class);

        assertThatExceptionOfType(BowlingProgressException.class).isThrownBy(
            () -> player.play(0, NORMAL));
    }

    @Test
    @DisplayName("플레이어가 STRIKE가 아니라면, 프레임은 2번 투구할 수 있다.")
    void playStrikeNotClosedTest() {
        assertThat(player.play(0, GUTTER).getProgress()).isInstanceOf(GeneralProgress.class);
        assertThat(player.play(0, NORMAL).getProgress()).isInstanceOf(Closed.class);

        assertThatExceptionOfType(BowlingProgressException.class).isThrownBy(
            () -> player.play(0, NORMAL));
    }

    @Test
    @DisplayName("플레이어의 마지막 프레임에서 Strike를 수행하면, 해당 프레임은 총 3회 진행할 수 있다.")
    void playLastFrameStrikeTest() {
        assertThat(player.play(9, STRIKE).getProgress()).isInstanceOf(GeneralProgress.class);
        assertThat(player.play(9, NORMAL).getProgress()).isInstanceOf(GeneralProgress.class);
        assertThat(player.play(9, STRIKE).getProgress()).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("플레이어의 마지막 프레임에서 Spare을 수행하면, 해당 프레임은 총 3회 진행할 수 있다.")
    void playLastFrameSpareTest() {
        assertThat(player.play(9, NORMAL).getProgress()).isInstanceOf(GeneralProgress.class);
        assertThat(player.play(9, SPARE).getProgress()).isInstanceOf(GeneralProgress.class);
        assertThat(player.play(9, STRIKE).getProgress()).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("플레이어가 STRIKE가 아니라면, 프레임은 2번 투구할 수 있다.")
    void playLastFrameStrikeClosedTest() {
        assertThat(player.play(9, GUTTER).getProgress()).isInstanceOf(GeneralProgress.class);
        assertThat(player.play(9, NORMAL).getProgress()).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("플레이어의 play 회차는 0 - 9 (1~10 Round) 만 허용한다.")
    void playRoundValidTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> player.play(-1, NORMAL));
        assertThatIllegalArgumentException().isThrownBy(() -> player.play(10, NORMAL));
    }
}