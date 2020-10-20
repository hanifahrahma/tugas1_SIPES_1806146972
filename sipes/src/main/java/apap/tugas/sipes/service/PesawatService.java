package apap.tugas.sipes.service;


import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TipeModel;

import java.util.List;
import java.util.Optional;

public interface PesawatService {
    List<PesawatModel> getlistPesawat();
    List<PesawatModel> getlistPesawatTua();
    void addPesawat(PesawatModel pesawatModel);
    String getNameTipe(PesawatModel pesawatModel);
    Optional<PesawatModel> getPesawatbyid(Long id);
}
