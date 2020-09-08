package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.*;

public class AnswersTest {

    @Test
    public void Answers_일급컬렉션의_삭제기능_테스트() throws CannotDeleteException {

        List<Answer> answerList = new ArrayList<>();
        answerList.add(A1);
        answerList.add(A3);

        Answers answers = Answers.create(answerList);

        DeleteHistories deleteHistories = answers.delete(UserTest.JAVAJIGI, DeleteHistories.create(new ArrayList()));

        assertThat(deleteHistories.getDeleteHistories()).hasSize(2);

    }

    @Test
    public void 다른사람의_댓글이_있을때_테스트() throws CannotDeleteException {

        List<Answer> answerList = new ArrayList<>();
        answerList.add(A1);
        answerList.add(A2);

        Answers answers = Answers.create(answerList);

        assertThatThrownBy(() -> {
            answers.delete(UserTest.JAVAJIGI,DeleteHistories.create(new ArrayList()));
        }).isInstanceOf(CannotDeleteException.class);
    }

}
