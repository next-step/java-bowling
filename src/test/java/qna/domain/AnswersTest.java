package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

public class AnswersTest {
    private Answer answer1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private Answer answer2 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private Answers answers = new Answers(Arrays.asList(answer1, answer2));

    @Test
    public void 삭제여부_권한체크_테스트_성공() throws CannotDeleteException {
        answers.validateOwnerCheck(UserTest.JAVAJIGI);
    }

    @Test
    public void 삭제여부_권한체크_테스트_실패() throws CannotDeleteException {
        Assertions.assertThatThrownBy(
                () -> answers.validateOwnerCheck(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
