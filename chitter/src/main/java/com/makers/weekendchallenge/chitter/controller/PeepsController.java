package com.makers.weekendchallenge.chitter.controller;

import com.makers.weekendchallenge.chitter.model.Peep;
import com.makers.weekendchallenge.chitter.repository.PeepRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PeepsController {

    private PeepRepository peepRepository;

    public PeepsController(PeepRepository peepRepository) {
        this.peepRepository = peepRepository;
    }

    @GetMapping("/peeps")
    public List<Peep> getPeepsByAsc(){
        Peep newPeep = new Peep("This is a new peep!");
        peepRepository.save(newPeep);
//        return peepRepository.findAll();
        return peepRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/peeps/{id}")
    public Optional<Peep> retrievePeep(@PathVariable long id) {
        return peepRepository.findById(id);
    }

    @PostMapping("/peeps")
    public Peep writePeep(@Valid @RequestBody Peep peep) {
        return peepRepository.save(peep);
    }

    @DeleteMapping("/peeps/{id}")
    public void deletePeep(@PathVariable long id) {
        peepRepository.deleteById(id);
    }

//    @PatchMapping("/peeps/{id}")
//    public Peep changePeep(@PathVariable Long id, @Valid @RequestBody Peep newPeep) {
//
//        Optional<Peep> optionalPeep = peepRepository.findById(id);
//
//        if (optionalPeep.isPresent()) {
//            optionalPeep.get().setContent(newPeep.getContent());
//            return peepRepository.save(optionalPeep.get());
//        }
//
//        return null;
//    }

    @PatchMapping("/peeps/{id}")
    public Optional<Peep> changePeep(@PathVariable Long id, @Valid @RequestBody Peep newPeep) {
        return peepRepository.findById(id)
                .map(oldPeep -> {
                    oldPeep.setContent(newPeep.getContent());
                    return oldPeep;
                })
                .map(updatedPeep -> peepRepository.save(updatedPeep));
    }
}
