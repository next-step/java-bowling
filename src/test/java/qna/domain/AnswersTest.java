package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.UnAuthorizedException;

import java.util.ArrayList;
import java.util.Arrays;

class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    @Test
    void 자신이_작성한_게시물만_삭제() throws CannotDeleteException {
        Answers answers = new Answers(new ArrayList<>(Arrays.asList(A1, A3)));
        Assertions.assertThat(answers.delete(UserTest.JAVAJIGI))
                .hasSize(2);
    }

    @Test
    void 자신이_작성하지_않은_게시물을_함께_삭제() {
        Answers answers = new Answers(new ArrayList<>(Arrays.asList(A1, A2)));
        Assertions.assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}