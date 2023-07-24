package android.genzinema.Model;

public class Style {
    Integer idStyle;
    String nameStyle;

    public Style(){

    }

    public Style(Integer idStyle, String nameStyle) {
        this.idStyle = idStyle;
        this.nameStyle = nameStyle;
    }

    public Integer getIdStyle() {
        return idStyle;
    }

    public void setIdStyle(Integer idStyle) {
        this.idStyle = idStyle;
    }

    public String getNameStyle() {
        return nameStyle;
    }

    public void setNameStyle(String nameStyle) {
        this.nameStyle = nameStyle;
    }
}
