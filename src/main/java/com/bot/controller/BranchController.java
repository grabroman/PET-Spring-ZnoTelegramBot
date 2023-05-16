package com.bot.controller;

import com.bot.model.dto.BranchDto;
import com.bot.model.dto.SpecialtyDto;
import com.bot.exception.SpecialtyNotFoundException;
import com.bot.exception.TypeBranchException;
import com.bot.model.BranchOfKnowledge;
import com.bot.service.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {
    private final Filter filter;
    private List<BranchDto> branchDto;
    private List<String> typeBranchList;

    public ResponseEntity<List<BranchDto>> findSpecialtiesByBranchId(@PathVariable String id){
        branchDto = filter.getSpecialitiesByBranchCode(id).stream()
                .map(BranchDto::new)
                .collect(Collectors.toList());
                  return new ResponseEntity<List<BranchDto>>(branchDto, HttpStatus.OK);
    }

    public ResponseEntity<SpecialtyDto> findSpecialtyByBranchIdAndSpecialtyId(@PathVariable String idBranch, @PathVariable String idSpecialty) {
        SpecialtyDto specialtyDTO = filter.getSpecialitiesByBranchCode(idBranch)
                .stream()
                .map(SpecialtyDto::new)
                .filter(specialty -> idSpecialty.equals(specialty.getCode()))
                .findAny()
                .orElseThrow(SpecialtyNotFoundException::new);
        return new ResponseEntity<SpecialtyDto>(specialtyDTO, HttpStatus.OK);
    }
    public ResponseEntity<List<BranchOfKnowledge>> getBranches(){
        return new ResponseEntity<List<BranchOfKnowledge>>(filter.getBranchesOfKnowledge(), HttpStatus.OK);
    }

    public ResponseEntity<List<String>> getBranchesDividedOnType(@PathVariable String type) {
        if (type.equalsIgnoreCase("hum")) {
            typeBranchList = filter.getBranchesOfKnowledgeByType("Гуманітарні").stream()
                    .map(BranchOfKnowledge::toString)
                    .collect(Collectors.toList());
        } else if (type.equalsIgnoreCase("tech")) {
            typeBranchList = filter.getBranchesOfKnowledgeByType("Технічні").stream()
                    .map(BranchOfKnowledge::toString)
                    .collect(Collectors.toList());
        } else {
            throw new TypeBranchException();
        }
        return new ResponseEntity<>(typeBranchList, HttpStatus.OK);
    }
}
