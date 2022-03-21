package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(12L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void delete_성공() throws Exception {
        Answer answer = ofUser(11L, UserTest.JAVAJIGI);
        answer.delete(UserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    public void delete_다른_사람_답변() {
        Answer answer = ofUser(11L, UserTest.JAVAJIGI);
        assertThatThrownBy(() -> {
            answer.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    public static Answer ofUser(long id, User user) {
        return new Answer(id, user, QuestionTest.Q1, "Answers Contents");
    }
}
