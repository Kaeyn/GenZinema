package android.genzinema.Model;

import java.util.ArrayList;

public class Movie {
    Integer IdMV, IdGenre,IdType,IdThumbnails;
    String UrlTrailer, NameMovie,Actors,Authors,YearProduce,Detail,UrlVideo;

    public Movie() {
    }

    public Movie(Integer idMV, Integer idGenre, Integer idType, Integer idThumbnails, String urlTrailer, String nameMovie, String actors, String authors, String yearProduce, String detail,String urlVideo) {
        IdMV = idMV;
        IdGenre = idGenre;
        IdType = idType;
        IdThumbnails = idThumbnails;
        UrlTrailer = urlTrailer;
        NameMovie = nameMovie;
        Actors = actors;
        Authors = authors;
        YearProduce = yearProduce;
        Detail = detail;
        UrlVideo = UrlVideo;
    }

    public Integer getIdMV() {
        return IdMV;
    }

    public Movie(Integer idMV, Integer idThumbnails) {
        IdMV = idMV;
        IdThumbnails = idThumbnails;
    }

    public Movie(Integer idMV, Integer idThumbnails, String nameMovie) {
        IdMV = idMV;
        IdThumbnails = idThumbnails;
        NameMovie = nameMovie;
    }

    public void setIdMV(Integer idMV) {
        IdMV = idMV;
    }

    public Integer getIdGenre() {
        return IdGenre;
    }

    public void setIdGenre(Integer idGenre) {
        IdGenre = idGenre;
    }

    public Integer getIdType() {
        return IdType;
    }

    public void setIdType(Integer idType) {
        IdType = idType;
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

    public Integer getIdThumbnails() {
        return IdThumbnails;
    }

    public void setIdThumbnails(Integer idThumbnails) {
        this.IdThumbnails = idThumbnails;
    }
    public String getUrlVideo() {
        return UrlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        UrlVideo = urlVideo;
    }


    public static ArrayList<Movie> initData(int[] lstIdImg, int[] lstImgFilm){
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        for (int i = 0; i < lstIdImg.length; i++) {
            Movie movie = new Movie(lstIdImg[i], lstImgFilm[i]);
            movieArrayList.add(movie);
        }
        return movieArrayList;
    }

    public static ArrayList<Movie> initDataCollection(int[] lstIdImg, int[] lstImgFilm, String[] lstNameFilm){
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        for (int i = 0; i < lstIdImg.length; i++) {
            Movie movie = new Movie(lstIdImg[i], lstImgFilm[i], lstNameFilm[i]);
            movieArrayList.add(movie);
        }
        return movieArrayList;
    }
}
