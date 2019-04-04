package com.graphqltest.demo.Object;
import javax.persistence.*;

@Entity
public class Hahmo {

    @Id
    @Column(name="hahmo_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="hahmo_nimi", nullable = false)
    private String nimi;

    @Column(name="hashmo_ika", nullable = false)
    private String ika;
    @Column(name="hashmo_koko", nullable = false)
    private String koko;
    //for the hibernate
    public Hahmo(){}

    public static final class Builder {
        private Long id;
        private String nimi="Tomi";
        private String ika="10";
        private String koko="20";

        public Builder() { }

        public Builder setId(Long val) {
            id = val;
            return this;
        }

        public Builder setNimi(String val) {
            nimi = val;
            return this;
        }

        public Builder setIka(String val) {
            ika = val;
            return this;
        }

        public Builder setKoko(String val) {
            koko = val;
            return this;
        }

        public Hahmo build() {
            return new Hahmo(this);
        }
    }
    private Hahmo(Builder builder) {
        id = builder.id;
        nimi = builder.nimi;
        ika = builder.ika;
        koko = builder.koko;
    }

    public Long getId() { return id; }

    public String getNimi() { return nimi; }

    public String getIka() { return ika; }

    public String getKoko() { return koko; }

    public void setId(Long id) { this.id = id; }

    public void setNimi(String nimi) { this.nimi = nimi; }

    public void setIka(String ika) { this.ika = ika; }

    public void setKoko(String koko) { this.koko = koko; }
}
