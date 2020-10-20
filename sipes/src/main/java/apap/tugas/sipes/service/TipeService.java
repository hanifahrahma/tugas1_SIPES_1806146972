package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TipeModel;

import java.util.List;
import java.util.Optional;

public interface TipeService {
    List<TipeModel> getlistTipe();
    Optional<TipeModel> getTipeModelbyId(Long id);
}
