package smktelkom_mlg.macitourapp.model;

/**
 * Created by MirzaUY on 3/23/2018.
 */

public class Comment {
    private String username;
    private String review;
    private String rate;
    private String date;

    public Comment() {
    }

    public Comment(String username, String review, String rate, String date) {
        this.username = username;
        this.review = review;
        this.rate = rate;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
