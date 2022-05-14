package qna.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Question extends AbstractEntity {
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    private final Answers answers = new Answers();

    private boolean deleted = false;

    public Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Question(long id, String title, String contents, User user) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.writer = user;
    }

    public Question(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.writer = user;
    }

    public boolean isWrittenBy(User user) {
        return this.writer == user;
    }

    public boolean hasAnswerNotWrittenBy(User user) {
        return answers.hasAnswerNotWrittenBy(user);
    }

    public DeleteHistory delete(User user, LocalDateTime at) {
        this.deleted = true;
        return new DeleteHistory(ContentType.QUESTION, id, user, at);
    }

    public DeleteHistories deleteAnswers(User user, LocalDateTime at) {
        return answers.deleteAllAnswerWrittenBy(user, at);
    }

    public Question addAnswer(Answer answer) {
        answers.add(answer);
        return this;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

//    public static class Builder extends AbstractEntity {
//        private Long id;
//        private String title;
//        private String contents;
//        private User writer;
//        private Answers answers;
//        private boolean deleted;
//
//        public Builder id(Long id) {
//            this.id = id;
//            return this;
//        }
//
//        public Builder title(String title) {
//            this.title = title;
//            return this;
//        }
//
//        public Builder contents(String contents) {
//            this.contents = contents;
//            return this;
//        }
//
//        public Builder writer(User writer) {
//            this.writer = writer;
//            return this;
//        }
//
//        public Builder answers(Answers answers) {
//            this.answers = answers;
//            return this;
//        }
//
//        public Builder deleted(boolean deleted) {
//            this.deleted = deleted;
//            return this;
//        }
//
//        public Question build() {
//            Question question = new Question();
//            if (this.id != null) {
//                question.id = this.id;
//            }
//            if (this.title != null) {
//                question.title = this.title;
//            }
//            if (this.contents != null) {
//                question.contents = this.contents;
//            }
//            if (this.contents != null) {
//                question.contents = this.contents;
//            }
//            if (this.writer != null) {
//                question.writer = this.writer;
//            }
//            if (this.answers != null) {
//                question.answers = this.answers;
//            }
//            if (this.answers != null) {
//                question.answers.addAll(this.answers);
//            }
//
//        }
//    }
}
