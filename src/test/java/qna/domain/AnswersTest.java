package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.QuestionTest.Q1;
import static qna.domain.QuestionTest.Q2;
import static qna.domain.UserTest.JAVAJIGI;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswersTest {
    @Test
    @DisplayName("답변들 중에서 접속 유저와 답변 작성 유저가 다른 경우, 예외처리를 한다.")
    void exceptionLoginUserDifferentAnswersUser() {
        // given
        User loginUser = JAVAJIGI;
        List<Answer> values = Arrays.asList(A1, A2);
        Answers answers = new Answers(values);

        // when & then
        assertThatThrownBy(() -> answers.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변들 중에서 접속 유저와 답변 유저가 모두 같은 경우, 전체 삭제가 된다.")
    void deleteAllLoginUserSameAnswersUser() {
        // given
        User loginUser = JAVAJIGI;
        Answer answer1 = new Answer(JAVAJIGI, Q1, "Answers Contents1");
        Answer answer2 = new Answer(JAVAJIGI, Q2, "Answers Contents2");
        List<Answer> values = Arrays.asList(answer1, answer2);
        Answers answers = new Answers(values);

        // when
        answers.delete(loginUser);

        // then
        assertAll(
                () -> assertThat(answer1.isDeleted()).isTrue(),
                () -> assertThat(answer1.isDeleted()).isTrue()
        );
    }
}
