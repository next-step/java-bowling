package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete_success() {
        //given
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);
        List<DeleteHistory> deleteHistories = Arrays.asList(
            new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now()));

        //when&then
        assertThat(Q1.delete()).isEqualTo(deleteHistories);
    }

    @Test
    void validate() {
        //given
        Q2.addAnswer(AnswerTest.A2);

        //when
        assertThatThrownBy(() -> Q2.validate(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
