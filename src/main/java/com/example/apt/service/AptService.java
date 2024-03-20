package com.example.apt.service;

import com.example.apt.domain.Apt;
import com.example.apt.entity.AptEntity;
import com.example.apt.repository.MybatisAptRepository;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

public class AptService {
    private final MybatisAptRepository aptRepository;

    public AptService(MybatisAptRepository aptRepository){
        this.aptRepository=aptRepository;
    }

    public List<Apt.Simple> findApts() {
        List<Apt.Simple> list = new ArrayList<>();
        for(AptEntity aptEntity : aptRepository.findAll()){
            Apt.Simple apt = new Apt.Simple();
            apt.setId(aptEntity.getId());
            apt.setName(aptEntity.getName());
            apt.setAddress(aptEntity.getAddress());
            apt.setYprice(aptEntity.getYprice());
            apt.setDprice(aptEntity.getDprice());
            list.add(apt);
        }
        return list;
    }

    /**
     * 조건에 맞는 도서 목록 조회
     */
    public List<Apt.Simple> findCondApts(Apt.Create aptForm) {
        AptEntity aptEntity = new AptEntity();
        aptEntity.setName(aptForm.getName());
        aptEntity.setAddress(aptForm.getAddress());
        if (aptForm.getYmin() != null) {
            aptEntity.setYmin(aptForm.getYmin());
        }
        else {
            aptForm.setYmin(0);
            aptEntity.setYmin(aptForm.getYmin());
        }
        if (aptForm.getDmin() != null) {
            aptEntity.setDmin(aptForm.getDmin());
        }
        else {
            aptForm.setDmin(0);
            aptEntity.setDmin(aptForm.getDmin());
        }
        if (aptForm.getYmax() != null) {
            aptEntity.setYmax(aptForm.getYmax());
        }
        else {
            aptForm.setYmax(999999999);
            aptEntity.setYmax(aptForm.getYmax());
        }
        if (aptForm.getDmax() != null) {
            aptEntity.setDmax(aptForm.getDmax());
        }
        else {
            aptForm.setDmax(999999999);
            aptEntity.setDmax(aptForm.getDmax());
        }

        List<Apt.Simple> list = new ArrayList<>();
        for(AptEntity aptEntity2 : aptRepository.findCond(aptEntity)) {
            Apt.Simple apt2 = new Apt.Simple();
            apt2.setId(aptEntity2.getId());
            apt2.setName(aptEntity2.getName());
            apt2.setAddress(aptEntity2.getAddress());
            apt2.setYprice(aptEntity2.getYprice());
            apt2.setDprice(aptEntity2.getDprice());
            apt2.setYmin(aptEntity2.getYmin());
            apt2.setYmax(aptEntity2.getYmax());
            apt2.setDmin(aptEntity2.getDmin());
            apt2.setDmax(aptEntity2.getDmax());
            list.add(apt2);
        }
        return list;
    }

    /**
     * 도서추가
     */
    public Long addApt(Apt.Create aptForm) {
        AptEntity aptEntity = new AptEntity();
        aptEntity.setName(aptForm.getName());
        aptEntity.setAddress(aptForm.getAddress());
        aptEntity.setYprice(aptForm.getYprice());
        aptEntity.setDprice(aptForm.getDprice());
        aptRepository.save(aptEntity);
        return aptEntity.getId();
    }

    public void updateAptService(Long aptId, Apt.Update updateForm) {
        AptEntity aptEntity = aptRepository.findById(aptId).orElseThrow(
                IllegalArgumentException::new
        );
        aptEntity.setName(updateForm.getName());
        aptEntity.setAddress(updateForm.getAddress());
        aptEntity.setYprice(updateForm.getYprice());
        aptEntity.setDprice(updateForm.getDprice());
        aptRepository.update(aptEntity);
    }

    public AptEntity getAptById(Long aptId) {
        return aptRepository.findById(aptId).orElseThrow(
                IllegalArgumentException::new
        );
    }

    public void deleteApt(Long aptId) {
        AptEntity aptEntity = aptRepository.findById(aptId).orElseThrow(
                IllegalArgumentException::new
        );

        aptRepository.delete(aptEntity);
    }

}
