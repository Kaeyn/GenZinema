package android.genzinema.Model;

public class FavoriteMovie {
    Integer IdFMV,IdMovie;
    String EmailUser;

    public Integer getIdFMV() {
        return IdFMV;
    }

    public void setIdFMV(Integer idFMV) {
        IdFMV = idFMV;
    }

    public Integer getIdMovie() {
        return IdMovie;
    }

    public void setIdMovie(Integer idMovie) {
        IdMovie = idMovie;
    }

    public String getEmailUser() {
        return EmailUser;
    }

    public void setEmailUser(String emailUser) {
        EmailUser = emailUser;
    }

    public FavoriteMovie() {
    }

    public FavoriteMovie(Integer idFMV, Integer idMovie, String emailUser) {
        IdFMV = idFMV;
        IdMovie = idMovie;
        EmailUser = emailUser;
    }
}
