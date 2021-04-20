package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    @Test
    void delete() throws CannotDeleteException {
        Answers answers = Answers.of();
        answers.add(A1);
        answers.add(A3);

        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistories).usingRecursiveComparison().ignoringFields("createDate")
            .isEqualTo(Arrays.asList(
                DeleteHistory.of(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()),
                DeleteHistory.of(ContentType.ANSWER, A3.getId(), A3.getWriter(), LocalDateTime.now())));
    }

    @Test
    void delete_다른_사람이_쓴_답변_존재() {
        Answers answers = Answers.of();
        answers.add(A1);
        answers.add(A2);

        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
