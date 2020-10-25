package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.repository.PenerbanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService {
    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public List<PenerbanganModel> getlistPenerbangan() {
        return penerbanganDb.findAll();
    }



    @Override
    public List<PenerbanganModel> getlistPenerbanganNoPesawat() {
        List<PenerbanganModel> penerbanganModelList = penerbanganDb.findAll();
        List<PenerbanganModel> listpenerbangan = new ArrayList<>();
        for(PenerbanganModel penerbanganModel: penerbanganModelList){
            if(penerbanganModel.getPesawat() == null){
                listpenerbangan.add(penerbanganModel);
            }
        }
        return listpenerbangan;
    }

    @Override
    public PenerbanganModel getPenerbanganbyid(Long id) {
        return penerbanganDb.findById(id).get();
    }

    @Override
    public boolean addPenerbangan(PenerbanganModel penerbanganModel) {
        PenerbanganModel penerbanganModel1 = penerbanganDb.findByNomorPenerbangan(penerbanganModel.getNomorPenerbangan()).get();
        if(penerbanganModel1 == null) {
            penerbanganDb.save(penerbanganModel);
            return true;
        }
        return false;
    }

    @Override
    public void deletePenerbangan(PenerbanganModel penerbanganModel) {
        penerbanganDb.delete(penerbanganModel);
    }

    @Override
    public PenerbanganModel changePenerbangan(PenerbanganModel penerbanganModel) {
        PenerbanganModel targetPenerbangan = penerbanganDb.findById(penerbanganModel.getId()).get();
        try{
            targetPenerbangan.setKodeBandaraAsal(penerbanganModel.getKodeBandaraAsal());
            targetPenerbangan.setKodeBandaraTujuan(penerbanganModel.getKodeBandaraTujuan());
            targetPenerbangan.setNomorPenerbangan(penerbanganModel.getNomorPenerbangan());
            targetPenerbangan.setWaktuBerangkat(penerbanganModel.getWaktuBerangkat());
            penerbanganDb.save(targetPenerbangan);
            return targetPenerbangan;
        } catch (NullPointerException nullException){
            return null;
        }
    }


}
