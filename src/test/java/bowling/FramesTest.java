package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.factory.BowlingHitScoresFactory;
import bowling.domain.factory.HitScoresFactory;
import bowling.domain.scores.GeneralHitScores;
import bowling.domain.scores.HitScores;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FramesTest {

    private static final int STRIKE = 10;
    private static final int[] END_OF_NINE_ROUND =
        new int[]{STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE};

    private final HitScoresFactory hitScoresFactory = new BowlingHitScoresFactory();

    @Test
    void createTest() {
        List<Frame> emptyFrames = new ArrayList<>();
        assertThatCode(
            () -> new Frames(emptyFrames)).doesNotThrowAnyException();
    }


    @Test
    @DisplayName("마지막 프레임을 반환한다.")
    void getLastFrameTest() {
        List<Frame> mockFrames = IntStream.range(0, 2)
            .boxed()
            .map(round -> new Frame(new GeneralHitScores(1, 2)))
            .collect(Collectors.toList());

        Frames frames = new Frames(mockFrames);

        assertThat(frames.getLastFrame()).isEqualTo(new Frame(new GeneralHitScores(1, 2)));
    }

    @Test
    @DisplayName("경기 최초에 프레임을 반환할 경우, 비어있는 프레임이 반환된다.")
    void getLastFrameLastTest() {
        assertThat(new Frames().getLastFrame()).isEqualTo(new Frame(hitScoresFactory.create(0)));
    }

    @Test
    @DisplayName("Strike 수행시, 다음 프레임에 기록되어야 한다.")
    void addGeneralFrameStrikeTest() {
        Frames frames = new Frames(toFrame(STRIKE, STRIKE, STRIKE, STRIKE));

        assertThat(frames.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Strike가 아닌경우, 다음 투구는 동일 프레임에 기록되어야 한다.")
    void addGeneralFrameTest() {
        Frames frames = new Frames(toFrame(3));
        Frames updateFrames = frames.update(3);
        assertThat(updateFrames.size()).isEqualTo(1);

        Frames addFrame = mockFrameAddHitCounts(updateFrames, 3);
        assertThat(addFrame.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("마지막 프레임 수행시, 다음 프레임에 기록되어야 하며, STRIKE 또는 SPARE이 있는경우, 한번더 투구할 수 있다.")
    void addFinalFrameTest() {
        Frames finalStartFrames = new Frames(toFrame(END_OF_NINE_ROUND));

        assertThat(finalStartFrames.size()).isEqualTo(9);

        Frames addFrame = mockFrameAddHitCounts(finalStartFrames, 3);// SPARE
        assertThat(addFrame.size()).isEqualTo(10);

        Frames updateFrames = addFrame.update(7);

        Frames finalAddFrame = updateFrames.update(STRIKE);
        assertThat(finalAddFrame.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("마지막 프레임 수행시, 다음 프레임에 기록되어야 하며, STRIKE 또는 SPARE이 없는 경우, 투구는 최대 2회이다.")
    void addFinalFrameWithExceptionTest() {
        Frames finalStartFrames = new Frames(hitScoresFactory, END_OF_NINE_ROUND);
        assertThat(finalStartFrames.size()).isEqualTo(9);

        Frames addFrame = mockFrameAddHitCounts(finalStartFrames, 3);// NON_SPARE
        Frames updateFrame = addFrame.update(3);

        assertThat(updateFrame.size()).isEqualTo(10);

        assertThatIllegalArgumentException().isThrownBy(() -> updateFrame.update(3));
    }


    private Frames mockFrameAddHitCounts(Frames frames, int... hitCounts) {

        Frames tempFrames = frames;

        int round = frames.size();

        for (int hitCount : hitCounts) {
            HitScores hitScores = hitScoresFactory.create(round++, hitCount);

            if (frames.isFrameClosed()) {
                tempFrames = frames.add(hitScores);
            }
        }

        return tempFrames;
    }

    private List<Frame> toFrame(int... numbers) {
        return IntStream.range(0, numbers.length)
            .boxed()
            .map(round -> new Frame(hitScoresFactory.create(round, numbers[round])))
            .collect(Collectors.toList());
    }
}
