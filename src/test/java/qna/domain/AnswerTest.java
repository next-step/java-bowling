package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete_답변_중_다른_사람이_쓴_글() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "AnswerTest");
        assertThatThrownBy(() -> {
            answer.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_성공_답변자_같음() throws CannotDeleteException {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "AnswerTest");
        answer.delete(UserTest.JAVAJIGI);
        assertThat(answer.isDeleted()).isTrue();
    }

}
