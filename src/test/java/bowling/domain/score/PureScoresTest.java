package bowling.domain.score;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import bowling.domain.frame.Frames;
import bowling.domain.score.PureScores;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PureScoresTest {
    @DisplayName("점수를 의미하는 리스트 데이터와 계산이 진행된 인덱스가 주어지면, pureScoreData 객체를 생성한다")
    @Test
    void createTest() {
        assertThat(PureScores.from(Arrays.asList(1, 2), 0)).isInstanceOf(PureScores.class);
    }

    @DisplayName("정적 팩토리 메서드 from의 첫번째 인자인 점수를 의미하는 리스트 데이터가 null이면 예외를 던진다")
    @Test
    void exceptionTest1() {
        assertThatThrownBy(() -> PureScores.from(null, 0))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정적 팩토리 메서드 from의 두번째 인자인 계산이 진행된 인덱스가 투구횟수 범위(0 ~ 21)을 벋어나면 예외를 던진다")
    @Test
    void exceptionTest2() {
        assertThatThrownBy(() -> PureScores.from(Arrays.asList(1, 2), -1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("쓰러뜨린 핀의 개수(점수)데이터 리스트가 주어지면, 누적데이터 리스트를 얻는다")
    @Test
    void initFrameAsScoreTest() {
        Frames frames = Frames.from(Arrays.asList(1, 2, 3, 4, 5, 4));
        List<Integer> cumulativeScores = new ArrayList<>();
        PureScores pureScores = PureScores.from(new ArrayList<>(), 0);

        for (int i = 0; i < frames.frames().size(); i++) {
            pureScores.initFrameAsScore(frames, i, cumulativeScores);
        }
        assertThat(cumulativeScores).containsExactly(3, 10, 19);
    }
}
