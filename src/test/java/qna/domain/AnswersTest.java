package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.Fixture.*;

@DisplayName("답변 그룹 테스트")
public class AnswersTest {
    private Answers answers;

    @Before
    public void setUp() throws Exception {
        Answer answer1 = new Answer(11L, JAVAJIGI, Q1, "Answers Contents1");
        Answer answer2 = new Answer(11L, SANJIGI, Q1, "Answers Contents1");

        answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);
    }

    @DisplayName("답변자가 모두 같지 않은경우 예외를 발생")
    @Test
    public void deleteException() {
        assertThatThrownBy(() -> answers.delete(JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("삭제를 성공할 경우 삭제 이력을 반환한다")
    @Test
    public void deleteHistory() {
        Answer answer1 = new Answer(11L, JAVAJIGI, Q1, "Answers Contents1");
        Answer answer2 = new Answer(11L, JAVAJIGI, Q1, "Answers Contents1");

        answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);

        assertThat(answers.delete(JAVAJIGI).size()).isEqualTo(answers.getAnswers().size());
    }
}
