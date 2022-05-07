package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class AnswerTest {
    public static final Answer A1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete_다른_사람답변_삭제시_예외() {
        Answer answer = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        assertThatThrownBy(() -> answer.delete(SANJIGI, new DeleteHistories(), now()))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_정상_동작() throws CannotDeleteException {
        Answer answer = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        DeleteHistories deleteHistories = new DeleteHistories();

        assertThat(answer.isDeleted()).isFalse();
        assertThat(deleteHistories.contains(new DeleteHistory(ContentType.ANSWER, A1.getId(), JAVAJIGI, now())))
                .isFalse();
        answer.delete(JAVAJIGI, deleteHistories, now());
        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistories.contains(new DeleteHistory(ContentType.ANSWER, A1.getId(), JAVAJIGI, now())))
                .isTrue();
    }
}
