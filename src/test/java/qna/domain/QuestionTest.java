package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    private List<DeleteHistory> predict = Arrays.asList(new DeleteHistory(Q1, UserTest.JAVAJIGI));
    private List<DeleteHistory> predict2 = new ArrayList<>(Arrays.asList(new DeleteHistory(Q2, UserTest.SANJIGI)));

    @Test
    void 작성자가아니면_예외() {
        assertThatThrownBy(() -> {
            Q1.validOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문글삭제() {
        List<DeleteHistory> result = Q1.delete();
        assertThat(result).containsAll(predict);
    }

    @Test
    void 질문글과답변까지함께삭제() {
        Q2.addAnswer(AnswerTest.A1);
        Q2.addAnswer(AnswerTest.A2);
        predict2.addAll(AnswersTest.AS1DELETE);

        List<DeleteHistory> result = Q2.delete();
        assertThat(result).containsAll(predict2);
    }
}
