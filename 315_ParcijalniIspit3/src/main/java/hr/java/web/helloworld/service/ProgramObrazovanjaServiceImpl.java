package hr.java.web.helloworld.service;

import hr.java.web.helloworld.domain.ProgramObrazovanja;
import hr.java.web.helloworld.dto.ProgramObrazovanjaDTO;
import hr.java.web.helloworld.repository.ProgramObrazovanjaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProgramObrazovanjaServiceImpl implements ProgramObrazovanjaService {

    private final ProgramObrazovanjaRepository programRepository;

    @Override
    public List<ProgramObrazovanja> findAll() {
        return programRepository.findAll();
    }

    @Override
    public Optional<ProgramObrazovanja> findById(Integer id) {
        return programRepository.findById(id);
    }

    @Override
    public ProgramObrazovanja save(ProgramObrazovanjaDTO programDTO) {
        ProgramObrazovanja program = new ProgramObrazovanja();
        program.setNaziv(programDTO.getNaziv());
        program.setCsvet(programDTO.getCsvet());
        return programRepository.save(program);
    }

    @Override
    public void deleteById(Integer id) {
        programRepository.deleteById(id);
    }
}