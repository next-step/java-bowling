package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("질문자이면 답변을 삭제할 수 있다")
    void deleteAnswerSoftly() throws CannotDeleteException {
        //when
        DeleteHistory deleteHistory = A1.deleteAnswerSoftly(UserTest.JAVAJIGI);

        //then
        assertAll(
                () -> assertThat(deleteHistory).isInstanceOf(DeleteHistory.class),
                () -> assertThat(A1.isDeleted()).isTrue()
        );
    }

    @Test
    @DisplayName("질문자가 아니면 예외를 발생 시킨다")
    void throwCannotDeleteException() {
        assertThatThrownBy(() -> A1.deleteAnswerSoftly(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

}
