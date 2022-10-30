package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AnswersTest {

    @DisplayName("작성자가 모두 작성한 답변일 때, 답변 목록을 삭제하면, 삭제 이력을 반환해야 한다.")
    @Test
    void deleteBy() {
        Answers answers = new Answers();
        answers.add(new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "answer1"));
        answers.add(new Answer(2L, UserTest.JAVAJIGI, QuestionTest.Q1, "answer2"));

        assertThat(answers.deleteBy(UserTest.JAVAJIGI))
                .containsExactly(
                        new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI, LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, 2L, UserTest.JAVAJIGI, LocalDateTime.now())
                );
    }

    @DisplayName("작성자가 작성하지 않은 답변을 하나라도 포함하고 있을 때, 답변 목록을 삭제하면, 예외가 발생해야 한다.")
    @Test
    void deleteBy_whenContainsNotOwned() {
        Answers answers = new Answers();
        answers.add(new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "answer1"));
        answers.add(new Answer(2L, UserTest.SANJIGI, QuestionTest.Q1, "answer2"));

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answers.deleteBy(UserTest.JAVAJIGI));

    }

}
