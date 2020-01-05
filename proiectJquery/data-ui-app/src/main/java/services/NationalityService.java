package services;

import model.Nationality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import repository.NationalityRepository;

import java.util.Collections;
import java.util.List;

@Service

public class NationalityService {
    @Autowired
   private NationalityRepository nationalityRepository;
    public List<Nationality> getNationalities(){
        return (List<Nationality>) nationalityRepository.findAll();
    }
    public Nationality getNationalityById(Integer Id){
        return (Nationality) nationalityRepository.findAllById(Collections.singleton(Id));
}
public void addNationality(Nationality nationality){
        nationalityRepository.save(nationality);
}
    public void updateNationality(Nationality nationality){
        nationalityRepository.save(nationality);
    }
    public void deleteNationality(Integer Id){
        nationalityRepository.deleteById(Id);
    }
}
