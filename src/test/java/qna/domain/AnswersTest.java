package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.QuestionTest.Q1;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class AnswersTest {
    private Answers answers;

    @DisplayName("Answer가 같지 않은경우 CannotDeleteException이 발생한다")
    @Test
    public void throw_exception() {
        //arrange
        Answer answer1 = new Answer(1L, JAVAJIGI, Q1, "Answers Contents1");
        Answer answer2 = new Answer(2L, SANJIGI, Q1, "Answers Contents2");
        answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);

        //act, assert
        assertThatThrownBy(() -> answers.delete(JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("delete성공시 DeleteHistory 리스트를 반환한다")
    @Test
    public void should_return_delete_history_when_success_delete() {
        //arrange
        Answer answer1 = new Answer(1L, JAVAJIGI, Q1, "Answers Contents1");
        Answer answer2 = new Answer(1L, JAVAJIGI, Q1, "Answers Contents2");
        answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);

        //act, assert
        assertThat(answers.delete(JAVAJIGI).size()).isEqualTo(answers.answers().size());
    }
}
