package objects;

public class Station implements Comparable<Station>{
    private int id,uic;
    private double geo_lat,geo_lng;
    private String code,name_short,name_medium,name_long,slug,country,type;

    public Station(int id, int uic, double geo_lat, double geo_lng, String code, String name_short, String name_medium, String name_long, String slug, String country, String type) {
        this.id = id;
        this.uic = uic;
        this.geo_lat = geo_lat;
        this.geo_lng = geo_lng;
        this.code = code;
        this.name_short = name_short;
        this.name_medium = name_medium;
        this.name_long = name_long;
        this.slug = slug;
        this.country = country;
        this.type = type;
    }

    public String getCode() {
        return code;
    }



    public String getName_short() {
        return name_short;
    }


    public String getCountry() {
        return country;
    }


    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", uic=" + uic +
                ", geo_lat=" + geo_lat +
                ", geo_lng=" + geo_lng +
                ", code=" + code +
                ", name_short=" + name_short +
                ", name_medium=" + name_medium +
                ", name_long=" + name_long +
                ", slug=" + slug +
                ", country=" + country +
                ", type=" + type +
                '}';
    }
    @Override
    public int compareTo(Station other) {
        return this.name_short.compareTo(other.getName_short());
    }
}
