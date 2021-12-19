package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {

    @Test
    void 작성자가_아닐시_예외처리() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> question.delete(UserTest.SANJIGI))
            .withMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 작성자가_아닌_답변_있을시_예외처리() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(UserTest.SANJIGI, question, "Answers Contents2"));

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> question.delete(UserTest.JAVAJIGI))
            .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 삭제성공시_deleted_변경_확인() throws CannotDeleteException {
        Question question = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
        // given
        question.addAnswer(new Answer(UserTest.SANJIGI, question, "Answers Contents2"));

        // when
        question.delete(UserTest.SANJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
    }

}
