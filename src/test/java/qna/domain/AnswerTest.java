package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {

    public Question question;

    @BeforeEach
    void setUp() {
        question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    void 작성자가_아닐시_예외처리() {
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> answer.delete(UserTest.SANJIGI))
            .withMessage("답글을 삭제할 권한이 없습니다.");
    }

    @Test
    void 삭제성공() throws CannotDeleteException {
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        answer.delete(UserTest.JAVAJIGI);
        assertThat(answer.isDeleted()).isTrue();
    }
}
