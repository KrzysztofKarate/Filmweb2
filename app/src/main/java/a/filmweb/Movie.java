package a.filmweb;

/**
 * Created by Aleksandra on 2017-06-06.
 */

public class Movie {
    private String tytul;
    private String gatunek;
    private String glownyAktor;

    public Movie(){}

    public Movie(String tytul, String gatunek, String glownyAktor){
        this.tytul = tytul;
        this.glownyAktor= glownyAktor;
        this.gatunek = gatunek;
    }
    public String getTytul() {return tytul;}
    public String getGlownyAktor(){return glownyAktor;}
    public String getGatunek(){return gatunek;}


}
