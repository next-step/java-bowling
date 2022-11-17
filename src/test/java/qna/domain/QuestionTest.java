package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Answer Q1A1;
    private Answer Q1A2;

    @BeforeEach
    void beforeEach() {
        Q1A1 = new Answer(UserTest.JAVAJIGI, Q1, "contentsQ1A1");
        Q1A2 = new Answer(UserTest.JAVAJIGI, Q1, "contentsQ1A2");
        Q1.addAnswer(Q1A1);
        Q1.addAnswer(Q1A2);
    }

    @Test
    void 질문의_작성자가_특정_유정와_일치하는지_확인() {
        assertThat(Q1.isOwner(UserTest.JAVAJIGI)).isTrue();
        assertThat(Q1.isOwner(UserTest.SANJIGI)).isFalse();

        assertThat(Q2.isOwner(UserTest.SANJIGI)).isTrue();
        assertThat(Q2.isOwner(UserTest.JAVAJIGI)).isFalse();
    }

    @Test
    void 질문에_달린_모든_답변의_작성자가_질문글_작성자와_일치하는지_확인() {
        assertThat(Q1.hasOnlyWritersAnswers()).isTrue();

        Q2.addAnswer(new Answer(UserTest.JAVAJIGI, Q2, "contentsQ2A1"));
        Q2.addAnswer(new Answer(UserTest.SANJIGI, Q2, "contentsQ2A2"));
        assertThat(Q2.hasOnlyWritersAnswers()).isFalse();
    }

    @Test
    void 삭제시_질문과_답변의_삭제_상태가_true로_변경() {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.getAnswers().forEach(answer -> assertThat(answer.isDeleted()).isFalse());

        Q1.delete();

        assertThat(Q1.isDeleted()).isTrue();
        Q1.getAnswers().forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    @Test
    void 삭제_히스토리에는_질문과_모든_답변_히스토리가_포함() {
        assertThat(Q1.deleteHistories()).containsAll(List.of(
                new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, Q1A1.getId(), Q1A1.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, Q1A2.getId(), Q1A2.getWriter(), LocalDateTime.now())
        ));
    }
}
