package apap.tugas.sipes.repository;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.model.TeknisiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PesawatTeknisiDb extends JpaRepository<PesawatTeknisiModel, Long> {
    List<PesawatTeknisiModel> findPesawatTeknisiModelByPesawat(PesawatModel pesawatModel);

}
