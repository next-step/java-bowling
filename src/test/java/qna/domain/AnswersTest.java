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

    @DisplayName("모두 삭제 상태로 변경")
    @Test
    public void changeDeletedAll() throws Exception {
        //given
        Answers answers = new Answers(Arrays.asList(answer1, answer2, answer3));

        //when
        answers.changeDeletedAll(UserTest.JAVAJIGI);

        //then
        assertTrue(answers.getAnswers().get(0).isDeleted());
    }
}
