package android.genzinema.Model;

public class Episode {
    Integer idEp,idImgEp,idMV;
    String NameEp, DetailEp, UrlEp;

    public Integer getIdEp() {
        return idEp;
    }

    public void setIdEp(Integer idEp) {
        this.idEp = idEp;
    }

    public Integer getIdImgEp() {
        return idImgEp;
    }

    public void setIdImgEp(Integer idImgEp) {
        this.idImgEp = idImgEp;
    }

    public Integer getIdMV() {
        return idMV;
    }

    public void setIdMV(Integer idMV) {
        this.idMV = idMV;
    }

    public String getNameEp() {
        return NameEp;
    }

    public void setNameEp(String nameEp) {
        NameEp = nameEp;
    }

    public String getDetailEp() {
        return DetailEp;
    }

    public void setDetailEp(String detailEp) {
        DetailEp = detailEp;
    }

    public String getUrlEp() {
        return UrlEp;
    }

    public void setUrlEp(String urlEp) {
        UrlEp = urlEp;
    }

    public Episode(){

    }

    public Episode(Integer idEp, Integer idImgEp, Integer idMV, String nameEp, String detailEp, String urlEp) {
        this.idEp = idEp;
        this.idImgEp = idImgEp;
        this.idMV = idMV;
        NameEp = nameEp;
        DetailEp = detailEp;
        UrlEp = urlEp;
    }

}
