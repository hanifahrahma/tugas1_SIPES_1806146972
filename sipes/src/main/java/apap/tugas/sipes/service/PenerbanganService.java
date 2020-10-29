package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;

import java.util.List;
import java.util.Optional;

public interface PenerbanganService {
    List<PenerbanganModel> getlistPenerbangan();
    List<PenerbanganModel> getlistPenerbanganNoPesawat();
    PenerbanganModel getPenerbanganbyid(Long id);
    boolean addPenerbangan(PenerbanganModel penerbanganModel);
    void deletePenerbangan(PenerbanganModel penerbanganModel);
    PenerbanganModel changePenerbangan(PenerbanganModel penerbanganModel);
    void changePesawatinPenerbangan(PesawatModel pesawatModel);
}
