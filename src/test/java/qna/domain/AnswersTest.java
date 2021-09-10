package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

@SuppressWarnings("NonAsciiCharacters")
public class AnswersTest {
    public static final Answer A1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void Answers를_만들_수_있다() {
        //given
        //when
        Answers answers = Answers.of(A1, A2);
        //then
        assertThat(answers).isEqualTo(Answers.of(A1, A2));
    }

    @Test
    public void Answer를_삭제할_수_있다() throws CannotDeleteException {
        //given
        Answer answer = new Answer(SANJIGI, QuestionTest.Q2, "Answers Contents3");
        //when
        DeleteHistory deleteHistory = answer.delete(SANJIGI);
        //then
        assertAll(
                () -> assertTrue(answer.isDeleted()),
                () -> assertEquals(new DeleteHistory(ContentType.ANSWER, answer.getId(), SANJIGI), deleteHistory)
        );
    }

    @Test
    public void Answers를_삭제할_수_있다() throws CannotDeleteException {
        //given
        Answer answer1 = new Answer(SANJIGI, QuestionTest.Q1, "Answers Contents");
        Answer answer2 = new Answer(SANJIGI, QuestionTest.Q2, "Answers Contents");

        //when
        Answers.of(answer1, answer2).deleteAll(SANJIGI);
        //then
        assertAll(
                () -> assertTrue(answer1.isDeleted()),
                () -> assertTrue(answer2.isDeleted())
        );
    }

    @Test
    public void 작성자가_다른_Answer_존재_시_삭제를_시도하면_익셉션이_발생한다() {
        //given
        Answer answer1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents");
        Answer answer2 = new Answer(SANJIGI, QuestionTest.Q2, "Answers Contents");
        //when
        //then
        assertThatThrownBy(() -> Answers.of(answer1, answer2).deleteAll(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    public void Answers로_부터_DeleteHistory_리스트를_만들_수_있다() throws CannotDeleteException {
        //given
        Answer answer1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents");
        Answer answer2 = new Answer(JAVAJIGI, QuestionTest.Q2, "Answers Contents");
        Answers answers = Answers.of(answer1, answer2);
        //when
        List<DeleteHistory> deleteHistories = answers.deleteAll(JAVAJIGI);
        //then
        assertThat(deleteHistories).containsExactly(
                new DeleteHistory(ContentType.ANSWER, answer1.getId(), JAVAJIGI),
                new DeleteHistory(ContentType.ANSWER, answer2.getId(), JAVAJIGI)
        );
    }
}
