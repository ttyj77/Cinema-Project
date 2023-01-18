package Model;

public class FilmDto {

    private String title;//영화 제목
    private int id; //영화 번호,
    private String rating; // 영화 등급
    private String summary; //영화 줄거리

    public FilmDto() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public FilmDto(FilmDto origin) {
        title = origin.title;
        rating = origin.rating;
        id = origin.id;
        summary = origin.summary;
    }

    public FilmDto(int id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (o instanceof FilmDto) {
            FilmDto b = (FilmDto) o;
            return id == b.id;
        }
        return false;
    }


}
