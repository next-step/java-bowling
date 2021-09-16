package bowling.domain.score;

import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PureScoresTest {
    @DisplayName("점수를 의미하는 리스트 데이터와 계산이 진행된 인덱스가 주어지면, pureScoreData 객체를 생성한다")
    @Test
    void createTest() {
        assertThat(PureScores.from(Arrays.asList(1, 2))).isInstanceOf(PureScores.class);
    }

    @SuppressWarnings("ConstantConditions")
    @DisplayName("정적 팩토리 메서드 from의 첫번째 인자인 점수를 의미하는 리스트 데이터가 null이면 예외를 던진다")
    @Test
    void exceptionTest1() {
        List<Integer> dummyList = null;
        assertThatThrownBy(() -> PureScores.from(dummyList))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("쓰러뜨린 핀의 개수(점수)데이터 리스트가 주어지면, 누적데이터 리스트를 얻는다")
    @Test
    void initFrameAsScoreTest() {
        List<Integer> cumulativeScores = new ArrayList<>();
        Frames frames = Frames.from(Arrays.asList(1, 2, 3, 4, 5, 4));
        PureScores.from(frames, cumulativeScores);
        assertThat(cumulativeScores).containsExactly(3, 10, 19);
    }
}
