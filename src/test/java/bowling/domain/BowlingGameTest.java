package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class BowlingGameTest {

    @DisplayName("볼링 게임이 10개의 프레임으로 구성되어 있지 않으면, 예외가 발생해야 한다.")
    @Test
    void create_givenNot10Frames() {
        List<BowlingGameFrame> frames = new ArrayList<>();
        IntStream.range(0, 9)
                .forEach(i -> frames.add(new NormalBowlingGameFrame()));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BowlingGame(frames))
                .withMessage("프레임은 10개로 구성되어 있어야 합니다.");
    }

    @DisplayName("진행 중인 프레임 이후에 종료된 프레임이 있으면, 예외가 발생해야 한다.")
    @Test
    void create_givenOnGoingFrameAndEndedFrame() {
        List<BowlingGameFrame> frames = new ArrayList<>();
        IntStream.range(0, 8)
                .forEach(i -> frames.add(new NormalBowlingGameFrame()));
        frames.add(new NormalBowlingGameFrame(List.of(1, 2)));
        frames.add(new FinalBowlingGameFrame());

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BowlingGame(frames))
                .withMessage("진행중인 프레임 다음에는 종료된 프레임이 있을 수 없습니다.");
    }

    @DisplayName("종료된 프레임인 경우, 종료 여부 확인시 true를 반환해야 한다.")
    @Test
    void isEnded() {
        BowlingGame game = provideEndedGame();

        assertThat(game.isEnded()).isTrue();
    }

    @DisplayName("진행 중인 프레임을 반환해야 한다.")
    @Test
    void getCurrentFrame() {
        List<BowlingGameFrame> frames = new ArrayList<>();
        frames.add(new NormalBowlingGameFrame(List.of(1, 2)));
        frames.add(new NormalBowlingGameFrame(List.of(1)));
        IntStream.range(0, 7)
                .forEach(i -> frames.add(new NormalBowlingGameFrame()));
        frames.add(new FinalBowlingGameFrame());

        BowlingGame game = new BowlingGame(frames);

        assertThat(game.getCurrentFrame()).isEqualTo(new NormalBowlingGameFrame(List.of(1)));
    }

    @DisplayName("종료된 상태라면, 예외가 발생해야 한다.")
    @Test
    void getCurrent_givenEndedGame() {
        BowlingGame game = provideEndedGame();

        assertThatIllegalStateException()
                .isThrownBy(game::getCurrentFrame)
                .withMessage("이미 종료된 게임입니다.");
    }

    private BowlingGame provideEndedGame() {
        List<BowlingGameFrame> frames = new ArrayList<>();
        IntStream.range(0, 9)
                .forEach(i -> frames.add(new NormalBowlingGameFrame(List.of(10))));
        frames.add(new FinalBowlingGameFrame(List.of(1, 2)));
        return new BowlingGame(frames);
    }

    @DisplayName("투구를 하면, 투구 결과가 반영되어야 한다.")
    @Test
    void hit() {
        List<BowlingGameFrame> frames = new ArrayList<>();
        frames.add(new NormalBowlingGameFrame(List.of(10)));
        IntStream.range(0, 8)
                .forEach(i -> frames.add(new NormalBowlingGameFrame()));
        frames.add(new FinalBowlingGameFrame());

        BowlingGame game = new BowlingGame(frames);
        game.hit(1);
        game.hit(2);
        game.hit(10);
        game.hit(3);

        assertThat(game).isEqualTo(getExpectedOfHitTest());
    }

    private BowlingGame getExpectedOfHitTest() {
        List<BowlingGameFrame> frames = new ArrayList<>();
        frames.add(new NormalBowlingGameFrame(List.of(10)));
        frames.add(new NormalBowlingGameFrame(List.of(1, 2)));
        frames.add(new NormalBowlingGameFrame(List.of(10)));
        frames.add(new NormalBowlingGameFrame(List.of(3)));
        IntStream.range(0, 5)
                .forEach(i -> frames.add(new NormalBowlingGameFrame()));
        frames.add(new FinalBowlingGameFrame());
        return new BowlingGame(frames);
    }

}
