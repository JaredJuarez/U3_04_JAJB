package mx.edu.utez.u3_04.service;

import mx.edu.utez.u3_04.model.Cede;
import mx.edu.utez.u3_04.repository.CedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CedeService {
    @Autowired
    private CedeRepository cedeRepository;

    public List<Cede> findAll() {
        return cedeRepository.findAll();
    }
    public Optional<Cede> findById(Long id) {
        return cedeRepository.findById(id);
    }
    public Cede save(Cede cede) {
        return cedeRepository.save(cede);
    }
    public void delete(Long id) {
        cedeRepository.deleteById(id);
    }
}