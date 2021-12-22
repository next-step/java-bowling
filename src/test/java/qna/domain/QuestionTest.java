package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 삭제 성공")
    @Test
    void delete() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("본인이 작성한 질문이 아닌 경우 삭제할 수 없음 예외 처리한다.")
    @Test
    void exception() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q2.delete(UserTest.JAVAJIGI))
                .withMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문자와 답변자가 같은 경우 삭제 가능하다.")
    @Test
    void 질문자와_답변자가_같은_경우() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 답변자가 다른 경우 삭제할 수 없음 예외 처리한다.")
    @Test
    void 질문자와_답변자가_다른_경우() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        question.addAnswer(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
