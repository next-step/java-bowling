package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    void setUp() {
        this.answer = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "answer1");
    }

    @DisplayName("작성자가 작성한 답변을 삭제하면, 삭제 내역을 반환해야 한다.")
    @Test
    void deleteBy() {
        assertThat(answer.deleteBy(UserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @DisplayName("작성자가 작성하지 않은 답변을 삭제하면, 예외가 발생해야 한다.")
    @Test
    void deleteBy_whenNotOwned() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answer.deleteBy(UserTest.SANJIGI));
    }

}
