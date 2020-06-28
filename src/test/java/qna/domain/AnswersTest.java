package qna.domain;

import org.hibernate.sql.Delete;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @Test
    void validate() {
        List<Answer> mock = Arrays.asList(AnswerTest.A1);
        Answers answers = new Answers(mock);
        assertThatThrownBy(() -> answers.validate(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete() {
        List<Answer> mock = Arrays.asList(AnswerTest.A1);
        Answers answers = new Answers(mock);
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories = answers.delete(deleteHistories);

        DeleteHistory deleteHistory = new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI, LocalDateTime.now());

        assertThat(deleteHistories).contains(deleteHistory);
    }
}
