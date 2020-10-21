package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.model.TeknisiModel;

import java.util.List;

public interface PesawatTeknisiService {
    List<PesawatTeknisiModel> getListTeknisibyIdPesawat(PesawatModel pesawatModel);
    void addPesawatTeknisi(PesawatTeknisiModel pesawatTeknisiModel);
}
