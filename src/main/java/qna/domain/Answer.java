package qna.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Answer extends AbstractEntity {
    @Embedded
    private AnswerBody answerBody = new AnswerBody();

    @Embedded
    private ArticleInfo articleInfo = new ArticleInfo();

    public Answer() {
    }

    public Answer(ArticleInfo articleInfo, AnswerBody answerBody) {
        this(null, articleInfo, answerBody);
    }

    public Answer(Long id, ArticleInfo articleInfo, AnswerBody answerBody) {
        super(id);
        this.answerBody = answerBody;
        this.articleInfo = articleInfo;
    }

    public boolean isDeleted() {
        return articleInfo.isDeleted();
    }

    public boolean isOwner(User writer) {
        return articleInfo.isOwner(writer);
    }

    public void toQuestion(Question question) {
        answerBody.toQuestion(question);
    }

    public DeleteHistory delete() {
        articleInfo.delete();
        return new DeleteHistory(
                ContentType.ANSWER, getId(), articleInfo.getWriter(), LocalDateTime.now()
        );
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerBody=" + answerBody +
                ", articleInfo=" + articleInfo +
                '}';
    }
}
