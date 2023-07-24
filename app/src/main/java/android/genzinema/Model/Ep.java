package android.genzinema.Model;

import java.util.ArrayList;

public class Ep {
    Integer idImgEp;
    String NameEp, DetailEp, UrlEp;

    public Ep(){

    }

    public Ep(Integer idImgEp, String nameEp, String timeEp, String detailEp, String UrlEp) {
        this.idImgEp = idImgEp;
        NameEp = nameEp;
        DetailEp = detailEp;
        UrlEp = UrlEp;
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

    public Ep(Integer idImgEp, String nameEp, String detailEp) {
        this.idImgEp = idImgEp;
        NameEp = nameEp;
        DetailEp = detailEp;
    }

    public static ArrayList<Ep> initData(int [] lstImg, String[]lstName, String[]lstDetail)
    {
        ArrayList<Ep> arrayListEp = new ArrayList<Ep>();
        for(int i=0; i<lstImg.length;i++)
        {
            Ep ep = new Ep(lstImg[i],lstName[i],lstDetail[i]);
            arrayListEp.add(ep);
        }
        return arrayListEp;
    }
}
