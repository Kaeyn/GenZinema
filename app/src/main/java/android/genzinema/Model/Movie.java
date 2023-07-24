package android.genzinema.Model;

public class Movie {
    Integer IdMV,IdStyle,IdType,IdThumbnails;
    String UrlTrailer, NameMovie,Actors,Authors,YearProduce,Detail;

    public Movie(Integer idMV, Integer idStyle, Integer idType, Integer idThumbnails, String urlTrailer, String nameMovie, String actors, String authors, String yearProduce, String detail) {
        IdMV = idMV;
        IdStyle = idStyle;
        IdType = idType;
        IdThumbnails = idThumbnails;
        UrlTrailer = urlTrailer;
        NameMovie = nameMovie;
        Actors = actors;
        Authors = authors;
        YearProduce = yearProduce;
        Detail = detail;
    }

    public Integer getIdMV() {
        return IdMV;
    }

    public void setIdMV(Integer idMV) {
        IdMV = idMV;
    }

    public Integer getIdStyle() {
        return IdStyle;
    }

    public void setIdStyle(Integer idStyle) {
        IdStyle = idStyle;
    }

    public Integer getIdType() {
        return IdType;
    }

    public void setIdType(Integer idType) {
        IdType = idType;
    }

    public Integer getIdThumbnails() {
        return IdThumbnails;
    }

    public void setIdThumbnails(Integer idThumbnails) {
        IdThumbnails = idThumbnails;
    }

    public String getUrlTrailer() {
        return UrlTrailer;
    }

    public void setUrlTrailer(String urlTrailer) {
        UrlTrailer = urlTrailer;
    }

    public String getNameMovie() {
        return NameMovie;
    }

    public void setNameMovie(String nameMovie) {
        NameMovie = nameMovie;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getAuthors() {
        return Authors;
    }

    public void setAuthors(String authors) {
        Authors = authors;
    }

    public String getYearProduce() {
        return YearProduce;
    }

    public void setYearProduce(String yearProduce) {
        YearProduce = yearProduce;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }
}
