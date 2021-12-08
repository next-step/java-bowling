package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    
    @DisplayName("다른 사람이 쓴 답변을 삭제하려면 예외를 던진다.")
    @Test
    void delete_byUnauthorized_throwsException() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("답변을 삭제할 권한이 없습니다.");
    }

    @DisplayName("삭제후 상태: deleted = true")
    @Test
    void delete_statusIsDeleted() {
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("DeleteHistory를 반환한다")
    @Test
    void delete_returnDeleteHistory() {
        DeleteHistory deleteHistory = A1.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI));
    }




}
