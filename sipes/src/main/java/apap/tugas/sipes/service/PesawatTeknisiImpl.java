package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.repository.PesawatTeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PesawatTeknisiImpl implements PesawatTeknisiService {
    @Autowired
    PesawatTeknisiDb pesawatTeknisiDb;

    @Override
    public List<PesawatTeknisiModel> getListTeknisibyIdPesawat(PesawatModel pesawatModel) {
        List<PesawatTeknisiModel> listteknisi = pesawatTeknisiDb.findPesawatTeknisiModelByPesawat(pesawatModel);
        return listteknisi;
    }

    @Override
    public void addPesawatTeknisi(PesawatTeknisiModel pesawatTeknisiModel) {
        pesawatTeknisiDb.save(pesawatTeknisiModel);
    }
}
