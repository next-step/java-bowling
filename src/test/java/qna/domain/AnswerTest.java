package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {

    public Answer a1;
    public Answer a2;

    @BeforeEach
    void init() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        a1 = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        a2 = new Answer(UserTest.SANJIGI, question, "Answers Contents2");
    }


    @Test
    @DisplayName("삭제가 정상적으로 되는지 확인한다.")
    void deleteTest() throws CannotDeleteException {
        assertThat(a1.delete(UserTest.JAVAJIGI)).isEqualTo(
            new DeleteHistory(ContentType.ANSWER, a1.getId(), a1.getWriter(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("삭제시 로그인 사용자와, 답변의 답변자가 다른경우 예외가 발생한다.")
    void deleteExceptionTest() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> a1.delete(UserTest.SANJIGI));
    }
}
