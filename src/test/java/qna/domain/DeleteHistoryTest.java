package qna.domain;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {
    public static DeleteHistory H1 = new DeleteHistory(ContentType.QUESTION, 100L, UserTest.JAVAJIGI, LocalDateTime.now());
    public static DeleteHistory H2 = new DeleteHistory(ContentType.ANSWER, 200L, UserTest.SANJIGI, LocalDateTime.now());

    @Test
    public void createFromQuestion() {
        final DeleteHistory deleteHistory = DeleteHistory.from(QuestionTest.Q1);
        assertThat(QuestionTest.Q1.getId()).isEqualTo(deleteHistory.contentId());
        assertThat(QuestionTest.Q1.getWriter()).isEqualTo(deleteHistory.deletedBy());
    }

    @Test
    public void createFromAnswer() {
        final DeleteHistory deleteHistory = DeleteHistory.from(AnswerTest.A1);
        assertThat(AnswerTest.A1.getWriter()).isEqualTo(deleteHistory.deletedBy());
    }

    @Test
    public void contentId() {
        assertThat(100L).isEqualTo(H1.contentId());
    }

    @Test
    public void deletedBy() {
        assertThat(UserTest.JAVAJIGI).isEqualTo(H1.deletedBy());
    }
}
