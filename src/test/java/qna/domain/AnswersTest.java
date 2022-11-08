package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {
    @Test
    void create() {
        assertThatNoException().isThrownBy(() -> new Answers(List.of(AnswerTest.A1)));
    }

    @Test
    void create2() {
        Answers answers = new Answers(List.of(
                AnswerTest.A1,
                AnswerTest.A2
        ));
        assertThatThrownBy(() -> answers.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void create3() throws CannotDeleteException {
        Answers answers = new Answers(List.of(
                AnswerTest.A1,
                AnswerTest.A1
        ));
        assertThat(answers.delete(UserTest.JAVAJIGI)).contains(
                new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), UserTest.JAVAJIGI, LocalDateTime.now())
        );
    }
}
