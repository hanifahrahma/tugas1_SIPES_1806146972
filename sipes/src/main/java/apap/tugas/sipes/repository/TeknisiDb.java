package apap.tugas.sipes.repository;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeknisiDb extends JpaRepository<TeknisiModel, Long> {
    Optional<TeknisiModel> findById(Long id);
}