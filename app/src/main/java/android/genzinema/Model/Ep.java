package android.genzinema.Model;

import java.util.ArrayList;

public class Ep {
    Integer idImgEp;
    String NameEp, TimeEp, DetailEp;

    public Ep(Integer idImgEp, String nameEp, String timeEp, String detailEp) {
        this.idImgEp = idImgEp;
        NameEp = nameEp;
        TimeEp = timeEp;
        DetailEp = detailEp;
    }

    public Integer getIdImgEp() {
        return idImgEp;
    }

    public void setIdImgEp(Integer idImgEp) {
        this.idImgEp = idImgEp;
    }

    public String getNameEp() {
        return NameEp;
    }

    public void setNameEp(String nameEp) {
        NameEp = nameEp;
    }

    public String getTimeEp() {
        return TimeEp;
    }

    public void setTimeEp(String timeEp) {
        TimeEp = timeEp;
    }

    public String getDetailEp() {
        return DetailEp;
    }

    public void setDetailEp(String detailEp) {
        DetailEp = detailEp;
    }
}
