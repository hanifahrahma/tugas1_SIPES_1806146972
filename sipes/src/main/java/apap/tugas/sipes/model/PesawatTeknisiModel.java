package apap.tugas.sipes.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="pesawat_teknisi")
public class PesawatTeknisiModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPesawat")
    PesawatModel pesawat;

    @ManyToOne
    @JoinColumn(name = "idTeknisi")
    TeknisiModel teknisi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PesawatModel getPesawat() {
        return pesawat;
    }

    public void setPesawat(PesawatModel pesawat) {
        this.pesawat = pesawat;
    }

    public TeknisiModel getTeknisi() {
        return teknisi;
    }

    public void setTeknisi(TeknisiModel teknisi) {
        this.teknisi = teknisi;
    }
}
