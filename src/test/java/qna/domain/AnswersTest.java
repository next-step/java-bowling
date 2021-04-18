package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    private Answer a1;
    private Answer a2;
    private Answer a3;

    private Answers javajigiAnswers;
    private Answers shuffledAnswers;

    @BeforeEach
    void setUp() {
        a1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        a2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        a3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

        javajigiAnswers = Answers.of(a1, a3);
        shuffledAnswers = Answers.of(a1, a2);
    }

    @Test
    @DisplayName("모든 답변의 작성자가 로그인한 사람과 같다면 삭제한다.")
    void deleteAnswers() {
        List<DeleteHistory> expected = Arrays.asList(
            DeleteHistory.of(a1),
            DeleteHistory.of(a3)
        );

        assertThat(javajigiAnswers.beDeletedBy2(UserTest.JAVAJIGI)).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    @DisplayName("일부 답변의 작성자가 로그인한 사람과 다르면 삭제할 수 없다.")
    void cannotDeleteAnswersIfOtherUserOwnSomeAnswers() {
        assertThatThrownBy(() -> shuffledAnswers.beDeletedBy2(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

}
