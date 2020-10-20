package apap.tugas.sipes.service;


import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Period;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService {

    @Autowired
    PesawatDb pesawatDb;

    @Override
    public List<PesawatModel> getlistPesawat() {
        return pesawatDb.findAll();
    }

    @Override
    public void addPesawat(PesawatModel pesawatModel) {
        pesawatDb.save(pesawatModel);
    }

    @Override
    public String getNameTipe(PesawatModel pesawatModel) {
        String tipeModel = pesawatModel.getTipe().getNama();
        return tipeModel;
    }

    @Override
    public Optional<PesawatModel> getPesawatbyid(Long id) {
        return pesawatDb.findById(id);
    }

    @Override
    public List<PesawatModel> getlistPesawatTua(Date date) {
        List<PesawatModel> listpesawat = pesawatDb.findAll();
        List<PesawatModel> listpesawatTua = new ArrayList<>();
        for(PesawatModel pesawat:listpesawat){
//            long year = Period.between(pesawat.getTanggalDibuat(), date).getYears();
            long difference_In_Time = date.getTime() - pesawat.getTanggalDibuat().getTime();
            long year= TimeUnit.MILLISECONDS.toDays(difference_In_Time) / 3651;
            if (year > 10 ){
                listpesawatTua.add(pesawat);
            }

        }
        return listpesawatTua;
    }
}
