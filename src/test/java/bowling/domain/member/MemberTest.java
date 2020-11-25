package bowling.domain.member;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("참여자 테스트")
public class MemberTest {
    Member member = Member.of("ABC");

    @DisplayName("참여자 생성")
    @Test
    public void createMember() {

        assertThat(member.getName()).isEqualTo("ABC");
        assertThat(member.isFinished()).isEqualTo(false);
    }

    @DisplayName("현재 프레임")
    @Test
    public void currentFrame() {
        assertThat(member.getCurrentFrameNumber()).isEqualTo(1);
    }

    @DisplayName("투구")
    @Test
    public void throwBall() {
        member.throwBall(10);

        assertThat(member.getCurrentFrameNumber()).isEqualTo(2);
        assertThat(member.getFrames().get(0).getScores().get(0)).isEqualTo(Score.strike());
    }
}