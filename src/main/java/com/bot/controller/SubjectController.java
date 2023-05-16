package com.bot.controller;

import com.bot.model.dto.SubjectDto;
import com.bot.model.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    @GetMapping
    public List<SubjectDto> findSubjects(){
        return Stream.of(Subject.values())
                .filter(x -> x != Subject.FOREIGN)
                .map(SubjectDto::new)
                .collect(Collectors.toList());
    }

}
