package tcc.fatec.com.br.guiacomercialtcc.dto;

public class GeoLocalizacaoDTO {

    private String latitude;
    private String longitude;

    public GeoLocalizacaoDTO(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean isValid() {
        return getLatitude() != null && getLongitude() != null;
    }

}
