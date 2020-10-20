package apap.tugas.sipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="penerbangan", uniqueConstraints=@UniqueConstraint(columnNames={"nomorPenerbangan"}))
public class PenerbanganModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPesawat", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PesawatModel pesawat;

    @NotNull
    @Size(max = 255)
    @Column(name = "kodeBandaraAsal", nullable = false)
    private String kodeBandaraAsal;

    @NotNull
    @Size(max = 255)
    @Column(name = "kodeBandaraTujuan", nullable = false)
    private String kodeBandaraTujuan;

    @NotNull
//    @Temporal(TemporalType.TIMESTAMP)
    @Temporal(TemporalType.DATE)
    @Column(name = "waktuBerangkat", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date waktuBerangkat;

    @NotNull
    @Size(max = 255)
    @Column(name = "nomorPenerbangan", nullable = false)
    private String nomorPenerbangan;

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

    public String getKodeBandaraAsal() {
        return kodeBandaraAsal;
    }

    public void setKodeBandaraAsal(String kodeBandaraAsal) {
        this.kodeBandaraAsal = kodeBandaraAsal;
    }

    public String getKodeBandaraTujuan() {
        return kodeBandaraTujuan;
    }

    public void setKodeBandaraTujuan(String kodeBandaraTujuan) {
        this.kodeBandaraTujuan = kodeBandaraTujuan;
    }

    public Date getWaktuBerangkat() {
        return waktuBerangkat;
    }

    public void setWaktuBerangkat(Date waktuBerangkat) {
        this.waktuBerangkat = waktuBerangkat;
    }

    public String getNomorPenerbangan() {
        return nomorPenerbangan;
    }

    public void setNomorPenerbangan(String nomorPenerbangan) {
        this.nomorPenerbangan = nomorPenerbangan;
    }
}

