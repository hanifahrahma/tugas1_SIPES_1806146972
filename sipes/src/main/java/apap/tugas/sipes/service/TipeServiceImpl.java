package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.repository.TipeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipeServiceImpl implements TipeService{

    @Autowired
    TipeDb tipeDb;

    @Override
    public Optional<TipeModel> getTipeModelbyId(Long id) {
        return tipeDb.findById(id);
    }

    @Override
    public List<TipeModel> getlistTipe() {
        return tipeDb.findAll();
    }
}
