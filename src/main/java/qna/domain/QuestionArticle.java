package qna.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Embeddable
class QuestionArticle {
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    public QuestionArticle() {
    }

    QuestionArticle(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    void writeBy(User writer) {
        this.writer = writer;
    }

    boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    User getWriter() {
        return writer;
    }
}
