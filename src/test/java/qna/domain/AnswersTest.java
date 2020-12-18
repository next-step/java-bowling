package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    @Test
    @DisplayName("Answers 객체 생성")
    void fromTest() {
        Answers answers = Answers.from(Arrays.asList(A1,A3));
        assertThat(answers.getSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("답변들 중 다른 사람의 답변일 경우 예외 처리")
    void exceptAnotherUserAnswers() {
        Answers answers = Answers.from(Arrays.asList(A1, A2));
        assertThrows(CannotDeleteException.class,
                () -> answers.checkOwners(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("삭제 상태로 만들기")
    void setDeleteStatus() {
        Answers answers = Answers.from(Arrays.asList(A1, A3));
        DeleteHistories deleteHistories = new DeleteHistories();

        Assertions.assertThat(answers.setDeleteStatus(deleteHistories).getSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("삭제")
    void delete() throws CannotDeleteException {
        Answers answers = Answers.from(Arrays.asList(A1, A3));
        DeleteHistories deleteHistories = new DeleteHistories();
        User user = UserTest.JAVAJIGI;

        Assertions.assertThat(answers.delete(user, deleteHistories).getSize()).isEqualTo(2);
    }
}