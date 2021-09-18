package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변을 작성한 유저는 답변을 삭제할수있다.")
    void delete_answer_by_writer() {
        assertThatCode(() -> A1.delete(UserTest.JAVAJIGI))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("답변을 작성한 유저가 아닌 경우는 답변을 삭제할수없다.")
    void cannot_delete_answer_by_other_user() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
