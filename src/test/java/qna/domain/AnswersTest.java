package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("NonAsciiCharacters")
public class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void Answers를_만들_수_있다() {
        //given
        //when
        Answers answers = Answers.of(A1, A2);
        //then
        assertThat(answers).isEqualTo(Answers.of(A1, A2));
    }

    @Test
    public void Answer를_삭제할_수_있다() {
        //given
        Answer answer = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents3");
        //when
        Answer deleted = answer.delete();
        //then
        assertTrue(deleted.isDeleted());
    }

    @Test
    public void Answers를_삭제할_수_있다() throws CannotDeleteException {
        //given
        Answer answer1 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents");
        Answer answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents");

        //when
        Answers.of(answer1, answer2).deleteAll(UserTest.SANJIGI);
        //then
        assertAll(
                () -> assertTrue(answer1.isDeleted()),
                () -> assertTrue(answer2.isDeleted())
        );
    }

    @Test
    public void 작성자가_다른_Answer_존재_시_삭제를_시도하면_익셉션이_발생한다() {
        //given
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents");
        Answer answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents");
        //when
        //then
        assertThatThrownBy(() -> Answers.of(answer1, answer2).deleteAll(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
