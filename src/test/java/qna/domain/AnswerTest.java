package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 작성자와 로그인 사용자가 일치하면, 답변을 삭제할 수 있다.")
    @Test
    void deletable() throws Exception {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents1");
        answer.delete(UserTest.JAVAJIGI);
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("답변 작성자와 로그인 사용자가 일치하지 않으면, 답변 삭제 시 CannotDeleteException 예외가 발생한다.")
    @Test
    void notDeletable() {
        Answer answer = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "contents2");
        assertThatThrownBy(() -> answer.delete(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
