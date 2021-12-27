package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void delete_실패_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> Q1.deleteByUser(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공() {
        assertThat(Q1.isDeleted()).isFalse();
        List<DeleteHistory> expectedDeleteHistories = Q1.deleteByUser(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertEquals(actualDeleteHistory(Q1), expectedDeleteHistories);
    }

    private List<DeleteHistory> actualDeleteHistory(Question question) {
        List<DeleteHistory> deleteHistories = Arrays.asList(
            new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now())
        );

        question.getAnswers().stream()
            .map(answer ->
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
            .forEach(deleteHistories::add);

        return deleteHistories;
    }
}
