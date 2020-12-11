package qna.domain;

import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;
import qna.domain.entity.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.fail;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete_성공() {
        try {
            DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);

            assertThat(A1.isDeleted()).isTrue();
            assertThat(deleteHistory).isNotNull();
        } catch (CannotDeleteException e) {
            fail("delete 과정에서 CannotDeleteException 발생.");
        }
    }

    @Test
    void delete_다른_사람이_쓴_답변(){
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> A2.delete(UserTest.JAVAJIGI))
                .withMessageMatching("답변을 삭제할 권한이 없습니다.");
    }
}
