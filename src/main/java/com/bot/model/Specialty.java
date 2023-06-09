package com.bot.model;

import java.util.HashSet;
import java.util.Set;

public class Specialty {
    private String name;
    private String code;
    private String linkSpec;
    private String linkUniv;
    private Subject first;
    private Set<Subject> second;
    private Set<Subject> third;

    public Specialty() {
        second = new HashSet<>();
        third = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getFirst() {
        return first;
    }

    public void setFirst(Subject first) {
        this.first = first;
    }

    public Set<Subject> getSecond() {
        return second;
    }

    public void setSecond(Set<Subject> second) {
        this.second = second;
    }

    public Set<Subject> getThird() {
        return third;
    }

    public void setThird(Set<Subject> third) {
        this.third = third;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean hasLinkSpec() {
        return linkSpec != null;
    }

    public String getLinkSpec() {
        return linkSpec;
    }

    public void setLinkSpec(String linkSpec) {
        this.linkSpec = linkSpec;
    }

    public boolean hasLinkUniv() {
        return linkUniv != null;
    }

    public String getLinkUniv() {
        return linkUniv;
    }

    public void setLinkUniv(String linkUniv) {
        this.linkUniv = linkUniv;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "name='" + code + "-" + name + '\'' +
                ", first='" + first + '\'' +
                ", second=" + second +
                ", third=" + third +
                ", linkToSpec='" + linkSpec + '\'' +
                ", linkToSpec='" + linkUniv + '\'' +
                '}';
    }
}

