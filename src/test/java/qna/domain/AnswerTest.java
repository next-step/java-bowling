package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    private Question Q1;
    private Answer A1;

    @BeforeEach
    public void setUp() {
        Q1 = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        A1 = new Answer(11L, UserTest.JAVAJIGI, Q1, "Answers Contents1");
        Q1.addAnswer(A1);
    }

    @Test
    public void deleted_by_writer() throws CannotDeleteException {
        DeleteHistory deleteHistory = A1.deleteBy(UserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
        assertDeleteHistoryIsA1(deleteHistory);
    }

    @Test
    public void delete_by_other_Exception() {
        assertThatThrownBy(() -> A1.deleteBy(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_cascaded_from_question() throws CannotDeleteException {
        List<DeleteHistory> deleteHistoryList = Q1.deleteBy(UserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistoryList).allSatisfy(deleteHistory ->
                assertThat(deleteHistory).satisfiesAnyOf(
                        this::assertDeleteHistoryIsA1,
                        this::assertDeleteHistoryIsQ1
                )
        );
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
