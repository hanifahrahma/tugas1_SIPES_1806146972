package apap.tugas.sipes.service;


import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.model.TipeModel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PesawatService {
    List<PesawatModel> getlistPesawat();
    List<PenerbanganModel> getlistPenerbanganPesawat(PesawatModel pesawatModel);
    List<PesawatModel> getlistPesawatTua(int year);
    void addPesawat(PesawatModel pesawatModel);
    String getNameTipe(PesawatModel pesawatModel);
    PesawatModel getPesawatbyid(Long id);
    String getnoSeriPesawat(PesawatModel pesawatModel);
    void deletePesawat(PesawatModel pesawatModel);
    PesawatModel changePesawat(PesawatModel pesawatModel);
}
