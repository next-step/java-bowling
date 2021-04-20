package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleteHistories).usingElementComparatorIgnoringFields("createDate")
            .isEqualTo(Collections.singletonList(
                DeleteHistory.of(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now())));
    }

    @Test
    void delete_내가_쓴_답변() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(A1);

        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).usingElementComparatorIgnoringFields("createDate")
            .isEqualTo(Arrays.asList(
                DeleteHistory.of(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()),
                DeleteHistory.of(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now())));
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_내가_쓴_글에_다른_사람이_답변() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(A2);

        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
