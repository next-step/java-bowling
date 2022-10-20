package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.UnAuthorizedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("자신의 쓴 답글이면 삭제할 수 있다.")
    @Test
    void delete() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answer.delete(UserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("답글이 자신의 답글이 아니라면 삭제할 수 없다.")
    @Test
    void deleteException() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        assertThatThrownBy(() -> answer.delete(UserTest.SANJIGI))
                .isInstanceOf(UnAuthorizedException.class);
    }
}
