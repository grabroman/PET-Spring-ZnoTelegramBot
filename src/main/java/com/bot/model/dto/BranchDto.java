package com.bot.model.dto;

import com.bot.model.Specialty;


public class BranchDto {
    private String nameAndCode;

    public String getNameAndCode() {
        return nameAndCode;
    }

    public BranchDto(Specialty specialty) {
        this.nameAndCode = specialty.getCode()+"-"+specialty.getName();
    }
}
