package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void deleteSuccessfully() {
        Answer answer = answerBy(UserTest.JAVAJIGI);
        DeleteHistory deleteHistory = answer.deleteBy(UserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistory).isNotNull();
    }

    @Test
    public void deleteByAnotherUser() {
        Answer answer = answerBy(UserTest.JAVAJIGI);
        assertThatThrownBy(() -> answer.deleteBy(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    public static Answer answerBy(User user) {
        return new Answer(user, QuestionTest.Q1, "Answer");
    }
}
