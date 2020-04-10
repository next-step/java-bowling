package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

class AnswersTest {

    private Answer answer1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private Answer answer2 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private Answer answer3 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    @DisplayName("생성자")
    @Test
    public void constructor() throws Exception {
        Answers answers = new Answers(Arrays.asList(new Answer(), new Answer()));
    }

    @Test
    public void validateDeletedAble_success() throws Exception {
        //given
        System.out.println(answer1);
        Answers answers = new Answers(Arrays.asList(answer1, answer2, answer3));

        //when
        answers.validateDeletedAble(UserTest.JAVAJIGI);
    }

    @Test
    public void validateDeletedAble_fail() throws Exception {
        //given
        System.out.println(answer1);
        Answers answers = new Answers(Arrays.asList(answer1, answer2, answer3));

        //when
        Assertions.assertThatThrownBy(
                () -> answers.validateDeletedAble(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
