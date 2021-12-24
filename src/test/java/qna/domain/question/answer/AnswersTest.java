package qna.domain.question.answer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static qna.domain.question.answer.AnswerTest.A1;
import static qna.domain.user.UserTest.JAVAJIGI;

class AnswersTest {

    @Test
    @DisplayName("Answers 객체를 생성한다")
    void shouldCreate() {
        Answers answers = answers();
        assertThat(answers).isEqualTo(answers());
    }

    @Test
    @DisplayName("답변의 모든 답변자가 로그인 한 유저와 일치하면 true를 반환한다")
    void shouldReturnTrueWhenAllWriterEqualToLoginUser() {
        Answers answers = answers();
        assertThat(answers.isOwner(JAVAJIGI)).isTrue();
    }

    private Answers answers() {
        return Answers.from(Arrays.asList(A1));
    }
}