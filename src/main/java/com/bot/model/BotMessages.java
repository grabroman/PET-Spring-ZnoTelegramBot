package com.bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class which contains default messages for Bot interface.
 * Value read from <strong>bot-msg.xml</strong>
 *
 * @author Max Kutsepalov
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BotMessages {

    @JsonProperty("start")
    private String start;
    @JsonProperty("rules")
    private String rules;
    @JsonProperty("contacts")
    private String contacts;
    @JsonProperty("show-menu")
    private String showMenu;
    @JsonProperty("all-subjects")
    private String allSubjects;
    @JsonProperty("not-enough")
    private String notEnoughAlert;
    @JsonProperty("too-many")
    private String tooManyAlert;
    @JsonProperty("find-all")
    private String findAll;
    @JsonProperty("remove-all")
    private String removeAll;
    @JsonProperty("error")
    private String error;
    @JsonProperty("tick")
    private String tickMark;
    @JsonProperty("find")
    private String findMark;
    @JsonProperty("out-of-limit")
    private String outOfLimitMark;
    @JsonProperty("separator")
    private String separator;
    @JsonProperty("search")
    private String searchData;
    @JsonProperty("delete")
    private String deleteData;
    @JsonProperty("menu")
    private String menuData;
    @JsonProperty("branch-type")
    private String branchType;
    @JsonProperty("branch")
    private String branch;
    @JsonProperty("specialty")
    private String specialty;
    @JsonProperty("choose-subject")
    private String chooseSubjectMenu;
    @JsonProperty("show-specialties")
    private String showSpecialtiesMenu;
    @JsonProperty("rules-menu")
    private String rulesMenu;
    @JsonProperty("contacts-menu")
    private String contactsMenu;
    @JsonProperty("undefined-message")
    private String undefinedMassage;
}
