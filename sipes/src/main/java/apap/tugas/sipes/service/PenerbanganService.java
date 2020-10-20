package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;

import java.util.List;
import java.util.Optional;

public interface PenerbanganService {
    List<PenerbanganModel> getlistPenerbangan();
    PenerbanganModel getPenerbanganbyid(Long id);
    void addPenerbangan(PenerbanganModel penerbanganModel);
    void deletePenerbangan(PenerbanganModel penerbanganModel);
    PenerbanganModel changePenerbangan(PenerbanganModel penerbanganModel);
}
