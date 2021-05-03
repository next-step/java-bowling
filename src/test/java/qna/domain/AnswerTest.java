package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    void delete_실패_다른사람이_쓴_답변이_있음() {
        Answers answers = new Answers(Arrays.asList(A1, A2));

        assertThatThrownBy(() -> {
            answers.isHasAnswersIsNotOwner(A1.getWriter());
        }).isInstanceOf(CannotDeleteException.class);
    }

}
