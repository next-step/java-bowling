package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
    public void Answer를_삭제할_수_있다(){
        //given
        Answer answer = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents3");
        //when
        Answer deleted = answer.delete();
        //then
        assertTrue(deleted.isDeleted());
    }

    @Test
    public void Answers를_삭제할_수_있다() {
        //given
        //when
        Answers.of(A1, A2).deleteAll();
        //then
        assertAll(
                () -> assertTrue(A1.isDeleted()),
                () -> assertTrue(A2.isDeleted())
        );
    }
}
