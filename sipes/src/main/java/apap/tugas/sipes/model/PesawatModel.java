package apap.tugas.sipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="pesawat", uniqueConstraints=@UniqueConstraint(columnNames={"nomorSeri"}))
public class PesawatModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "maskapai", nullable = false)
    private String maskapai;

    @NotNull
    @Size(max = 255)
    @Column(name = "nomorSeri", nullable = false)
    private String nomorSeri;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempatDibuat", nullable = false)
    private String tempatDibuat;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "tanggalDibuat", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalDibuat;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenisPesawat", nullable = false)
    private String jenisPesawat;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idTipe", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TipeModel tipe;

    @OneToMany(mappedBy = "pesawat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenerbanganModel> listpenerbangan;

    @OneToMany(mappedBy = "pesawat")
    Set<PesawatTeknisiModel> listpesawatteknisi;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public String getNomorSeri() {
        return nomorSeri;
    }

    public void setNomorSeri(String nomorSeri) {
        this.nomorSeri = nomorSeri;
    }

    public String getTempatDibuat() {
        return tempatDibuat;
    }

    public void setTempatDibuat(String tempatDibuat) {
        this.tempatDibuat = tempatDibuat;
    }

    public Date getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(Date tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

    public String getJenisPesawat() {
        return jenisPesawat;
    }

    public void setJenisPesawat(String jenisPesawat) {
        this.jenisPesawat = jenisPesawat;
    }

    public TipeModel getTipe() {
        return tipe;
    }

    public void setTipe(TipeModel tipe) {
        this.tipe = tipe;
    }

    public List<PenerbanganModel> getListpenerbangan() {
        return listpenerbangan;
    }

    public void setListpenerbangan(List<PenerbanganModel> listpenerbangan) {
        this.listpenerbangan = listpenerbangan;
    }


    public Set<PesawatTeknisiModel> getListpesawatteknisi() {
        return listpesawatteknisi;
    }

    public void setListpesawatteknisi(Set<PesawatTeknisiModel> listpesawatteknisi) {
        this.listpesawatteknisi = listpesawatteknisi;
    }
}


