package qna.domain;

import net.bytebuddy.build.ToStringPlugin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static qna.domain.AnswerTest.*;
import static qna.domain.UserTest.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    @Test
    void checkOwner() {
        assertThatThrownBy(
                () -> Q1.checkOwner(SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete() {
        Q1.deleted();
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void checkAnswersOwner() {
        Q1.addAnswer(A2);
        assertThatThrownBy(
                () -> Q1.checkAnswersOwner(JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void deleteAnswers() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        Q1.addAnswer(A2);
        Q1.deleteAnswers(deleteHistories);
        assertThat(A2.isDeleted()).isTrue();
    }
}
