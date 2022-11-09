package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class AnswersTest {

    public static Answers answers;
    public static final Answer A1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    @Test
    @DisplayName("전체 답변 삭제")
    void delete_answers() throws CannotDeleteException {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(A1);
        answerList.add(A3);
        answers = new Answers(answerList);

        answers.deleteAnswers(JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
        assertThat(A3.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 삭제 시 질문자와 답변자가 다르면 답변 삭제 불가")
    void cannot_delete_answers() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(A1);
        answerList.add(A2);
        answers = new Answers(answerList);

        assertThatThrownBy(() -> answers.deleteAnswers(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                        .hasMessageContaining("답변자 본인만 답변을 삭제할 수 있습니다");
    }
}
