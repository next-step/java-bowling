package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static qna.domain.QuestionTest.Q1;

public class AnswersTest {
    @Test
    void answers_생성() {
        assertThatNoException().isThrownBy(() -> new Answers(List.of(AnswerTest.A1)));
    }

    @Test
    void 다른_사람이_쓴_답변을_삭제할_경우() {
        Answers answers = new Answers(List.of(
                new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1"),
                new Answer(UserTest.SANJIGI, Q1, "Answers Contents2")
        ));
        assertThatThrownBy(() -> answers.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 본인이_쓴_답변을_삭제하는_경우() throws CannotDeleteException {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents2");
        Answers answers = new Answers(answer1, answer2);
        assertThat(answers.delete(UserTest.JAVAJIGI).getHistories()).contains(
                new DeleteHistory(ContentType.ANSWER, answer1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer2.getId(), UserTest.JAVAJIGI, LocalDateTime.now())
        );
    }
}
