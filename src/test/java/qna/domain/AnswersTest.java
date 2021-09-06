package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static qna.domain.AnswerTest.*;

public class AnswersTest {
    Answers answers;

    @BeforeEach
    void init() {
        answers = new Answers(Arrays.asList(A1, A3));
    }

    @Test
    @DisplayName("답변들 삭제")
    void deleteAnswers() throws CannotDeleteException {
        answers.deleteAnswers(UserTest.JAVAJIGI);
        assertAll(
                () -> Assertions.assertThat(A1.isDeleted()).isTrue(),
                () -> Assertions.assertThat(A3.isDeleted()).isTrue());
    }

    @Test
    @DisplayName("답변 다른 작성자 삭제")
    void deleteAnswersAnotherUser() {
        answers = new Answers(Arrays.asList(A1, A2));
        assertThatThrownBy(() -> {
            answers.deleteAnswers(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 히스토리 생성")
    void createDeleteHistories() {
        List<DeleteHistory> deleteHistories = answers.createDeleteHistories();
        assertThat(deleteHistories.contains(
                new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()))
                && deleteHistories.contains(
                new DeleteHistory(ContentType.ANSWER, A3.getId(), A3.getWriter(), LocalDateTime.now())))
                .isTrue();
    }
}
