package com.example.movielister.Data.Manager;

import com.example.movielister.Data.Repository.CommentRepository;
import com.example.movielister.Data.Repository.MovieRepository;
import com.example.movielister.Data.Repository.UserRepository;
import com.example.movielister.Model.Comment;
import com.example.movielister.Model.Dto.CommentDetail;
import com.example.movielister.Model.User;
import com.example.movielister.util.FXAlert;
import javafx.collections.ObservableList;

import java.util.Optional;

public class CommentManager {
    private final CommentRepository commentRepository;
    private final UserManager userManager = new UserManager(new UserRepository());
    private final MovieManager movieManager = new MovieManager(new MovieRepository());

    public CommentManager(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public ObservableList<Comment> getAllComment() {
        return commentRepository.getAll();
    }

    public void addComment(Comment comment) {
        if (!commentControl(comment)) {
            commentRepository.insert(comment);
            FXAlert.showInfo("Yorum eklendi.");
        } else {
            FXAlert.showWarning("Yorum kayıt edilemedi! Sınır aşılmış olabilir.");
        }
    }

    public void deleteComment(Comment comment) {
        boolean okay = FXAlert.showConfirmed(comment.getTitle() + " silenecek onaylıyor musunuz?");
        if (okay) {
            commentRepository.delete(comment);
            FXAlert.showInfo("Yorum silindi!");
        }
    }

    public void updateComment(Comment comment) {
        if (!commentControl(comment)) {
            commentRepository.update(comment);

        } else {
            FXAlert.showWarning("Yorum güncellenemedi!");
        }
    }

    public void deleteAllComment(User user) {
        Optional<String> answer = FXAlert.input().withText("Tüm yorumlar silenecek emin misiniz?", "Şifrenizi girin:").showAndWaitString();
        if (answer.isPresent() && answer.get().equals(user.getPass())) {
            commentRepository.deleteAll();
        }
    }

    public Comment getCommentById(int id) {
        Comment Comment = commentRepository.getById(id);
        if (Comment != null) {
            return Comment;
        }
        FXAlert.showWarning("Verilen id'ye sahip yorum bulunamadı!");
        return null;
    }

    public ObservableList<CommentDetail> getDetailsByMovieID(int id) {
        ObservableList<CommentDetail> commentDetails = commentRepository.getDetailsByMovieID(id);
        if (commentDetails != null) {
            return commentDetails;
        }
        FXAlert.showWarning("Yorumlar getirelemedi!");
        return null;
    }

    public ObservableList<Comment> getByMovieID(int id) {
        return commentRepository.getByMovieId(id);
    }

    public boolean commentControl(Comment comment) {
        return comment.getCommentDate() == null || comment.getUserComment() == null || comment.getTitle() == null
                || movieManager.getMovieById(comment.getMovieID()) == null
                || userManager.getUserById(comment.getUserID()) == null
                || comment.getTitle().length() >= 100 || comment.getUserComment().length() >= 1000;
    }

}
