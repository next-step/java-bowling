package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("다른 사람이 쓴 답변이 있어 삭제 불가한 경우")
    public void 다른사람이_쓴_답변이_있어_삭제_불가한_경우() {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(11L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);

        assertThatThrownBy(() -> {
            answer.isMatchOwner(question.getWriter());
        }).isInstanceOf(CannotDeleteException.class);
    }
}
