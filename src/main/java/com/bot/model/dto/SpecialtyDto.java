package com.bot.model.dto;

import com.bot.model.Specialty;

public class SpecialtyDto {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public SpecialtyDto(Specialty specialty) {
        this.name = specialty.getName();
        this.code = specialty.getCode();
    }
}
