package apap.tugas.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="teknisi")
public class TeknisiModel  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "nomorTelepon", nullable = false)
    private Long nomorTelepon;

    @OneToMany(mappedBy = "teknisi")
    Set<PesawatTeknisiModel> listteknisipesawat;

    public Long getIdTeknisi() {
        return id;
    }

    public void setIdTeknisi(Long idTeknisi) {
        this.id = idTeknisi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(Long nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }
}

