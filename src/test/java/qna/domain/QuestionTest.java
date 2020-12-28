package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    private Question Q1;
    private Answer A1;
    private Answer A2;

    @BeforeEach
    public void setUp() {
        Q1 = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        A1 = new Answer(11L, UserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(12L, UserTest.SANJIGI, Q1, "Answers Contents2");
    }


    @Test
    public void delete_by_writer() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.deleteBy(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleteHistories.size()).isEqualTo(1);
        assertDeleteHistoryIsQ1(deleteHistories.get(0));
    }

    @Test
    public void delete_by_other_Exception() {
        assertThatThrownBy(() -> Q1.deleteBy(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThat(Q1.isDeleted()).isFalse();
    }

    @Test
    public void delete_with_answers_by_writer() throws CannotDeleteException {
        Q1.addAnswer(A1);
        List<DeleteHistory> deleteHistories = Q1.deleteBy(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isTrue();

        assertThat(deleteHistories).allSatisfy(deleteHistory ->
                assertThat(deleteHistory).satisfiesAnyOf(
                        this::assertDeleteHistoryIsA1,
                        this::assertDeleteHistoryIsQ1
                )
        );
    }

    @Test
    public void delete_with_answers_by_other_Exception() {
        Q1.addAnswer(A2);
        assertThatThrownBy(() -> Q1.deleteBy(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThat(Q1.isDeleted()).isFalse();
    }

    private void assertDeleteHistoryIsQ1(DeleteHistory deleteHistory) {
        assertThat(deleteHistory).isEqualToIgnoringGivenFields(
                DeleteHistory.ofQuestion(Q1.getId(), Q1.getWriter()),
                "createDate");
    }

    private void assertDeleteHistoryIsA1(DeleteHistory deleteHistory) {
        assertThat(deleteHistory).isEqualToIgnoringGivenFields(
                DeleteHistory.ofAnswer(A1.getId(), A1.getWriter()),
                "createDate");
    }
}
