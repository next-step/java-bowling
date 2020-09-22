package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.common.ExceptionMessage;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(12L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    public static Answer ofUser(Long id, User answer_user) {
       return new Answer(id, answer_user, QuestionTest.Q1, "답글 : ^^");
    }

    @DisplayName("답글 작성자와 loginUser가 같을 경우 delete 성공")
    @Test
    void delete_answer_성공() throws CannotDeleteException {
        Answer answer = ofUser(11L, UserTest.JAVAJIGI);
        new Answer(answer).deleteAnswer(UserTest.JAVAJIGI);
    }

    @DisplayName("답글 작성자와 loginUser가 다를 경우 delete 실패")
    @Test
    void delete_답변자가_다를_경우() {
        Answer answer = ofUser(11L, UserTest.JAVAJIGI);
        assertThatThrownBy(() -> new Answer(answer).delete(UserTest.SANJIGI))
                .withFailMessage(ExceptionMessage.NOT_HAVE_PERMISSION_ANSWER);
    }
}
