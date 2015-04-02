package cn.sccl.ilife.android.sample.picturelist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DouBanBook {
    @SerializedName("rating")
    private Rating rating;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("author")
    private String[] author;
    @SerializedName("pubdate")
    private String pubdate;
    @SerializedName("tags")
    private List<Tag> tags;
    @SerializedName("origin_title")
    private String originTitle;
    @SerializedName("image")
    private String image;
    @SerializedName("images")
    private Image images;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("title")
    private String title;
    @SerializedName("author_intro")
    private String authorIntro;
    @SerializedName("summary")
    private String summary;
    @SerializedName("price")
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getOriginTitle() {
        return originTitle;
    }

    public void setOriginTitle(String originTitle) {
        this.originTitle = originTitle;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorIntro() {
        return authorIntro;
    }

    public void setAuthorIntro(String authorIntro) {
        this.authorIntro = authorIntro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
