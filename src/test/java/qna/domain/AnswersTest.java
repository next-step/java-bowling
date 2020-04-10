package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AnswersTest {

    private Answer answer1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private Answer answer2 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
    private Answer answer3 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    @DisplayName("생성자")
    @Test
    public void constructor() throws Exception {
        Answers answers = new Answers(Arrays.asList(new Answer(), new Answer()));
    }

    @DisplayName("삭제 가능한지 체크")
    @Test
    public void validateDeletedAble_success() throws Exception {
        //given
        Answers answers = new Answers(Arrays.asList(answer1, answer2, answer3));

        //when
        answers.validateDeletedAble(UserTest.JAVAJIGI);
    }

    @DisplayName("삭제 불가능 할 경우 exception")
    @Test
    public void validateDeletedAble_fail() throws Exception {
        //given
        Answers answers = new Answers(Arrays.asList(answer1, answer2, answer3));

        //when
        Assertions.assertThatThrownBy(
                () -> answers.validateDeletedAble(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("모두 삭제 상태로 변경")
    @Test
    public void changeDeletedAll() throws Exception {
        //given
        Answers answers = new Answers(Arrays.asList(answer1, answer2, answer3));

        //when
        answers.changeDeletedAll();

        //then
        assertTrue(answers.getAnswers().get(0).isDeleted());
    }
}
