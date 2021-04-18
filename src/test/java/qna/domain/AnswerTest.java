package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    public static Answer javajigiAnswer() {
        return new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    public static Answer sanjigiAnswer() {
        return new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    @DisplayName("직접 작성한 답변은 삭제할 수 있다.")
    void deleteAnswer() {
        Answer answer = javajigiAnswer();

        assertThat(answer.beDeletedBy(UserTest.JAVAJIGI)).isEqualTo(DeleteHistory.of(answer));
    }

    @Test
    @DisplayName("직접 작성한 답변이 아니면 삭제할 수 없다.")
    void cannotDeleteAnswerIfItIsNotOwned() {
        Answer answer = sanjigiAnswer();

        assertThatThrownBy(() -> answer.beDeletedBy(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

}
