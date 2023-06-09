package com.bot.view.handler;

import com.bot.model.Specialty;
import com.bot.model.Subject;
import com.bot.service.Filter;
import com.bot.util.EnumSetUtil;
import com.bot.util.SpecialityButtonRegister;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.EnumSet;
import java.util.Map;

@AllArgsConstructor
@Component
public class SpecializationHandler implements Handler {
    private Message message;
    private final SendMessage sendMessage;
    private final Filter filter;

    private void cleanRequests() {
        sendMessage.setReplyMarkup(null);
    }


    public SendMessage handle(Update update, EnumSet<Subject> enumSet) {
        message = update.getCallbackQuery().getMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        var branches = filter.getFiltered(enumSet);
        var branchesOfKnowledge = filter.getBranchesByName(branches);
        sendMessage.setReplyMarkup(SpecialityButtonRegister.getBranchOfKnowledgeKeyboard(branchesOfKnowledge,enumSet));
        sendMessage.setText("Галузі по вибраним предметам: ");
        return sendMessage;
    }

    public SendMessage handle(Update update) {
        cleanRequests();
        if(update.getCallbackQuery() != null) {
            sendMessage.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
            sendMessage.setReplyMarkup(SpecialityButtonRegister.getBranchTypeKeyboard());
        } else {
            sendMessage.setReplyMarkup(SpecialityButtonRegister.getBranchTypeKeyboard());
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        }
        sendMessage.setText("Тип галузі:");
        return sendMessage;
    }


    public SendMessage handleBranchType(Update update, Map<String, String> callback) {

        var branches = filter.getBranchesOfKnowledgeByType(callback.get("text"));
        sendMessage.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
        InlineKeyboardMarkup keyboard = SpecialityButtonRegister.getBranchOfKnowledgeKeyboard(branches,callback);
        sendMessage.setText(callback.get("text")+" галузі:");
        sendMessage.setReplyMarkup(keyboard);
        return sendMessage;
    }

    public SendMessage handleBranchOfKnowledge(Update update, Map<String, String> callback, SubjectHandler subjectHandler) {
        cleanRequests();

        if(isReturnButtonPressed(callback)){
            if(EnumSetUtil.isEnumSet(callback.get("previous"))){
                return subjectHandler.handleReturn(update);
            } else {
                return handle(update);
            }
        }

        var specialties = filter.getSpecialitiesByBranchCode(callback.get("text"));

        String branchOfKnowledge = filter.getBranchOfKnowledgeName(callback.get("text"));
        InlineKeyboardMarkup inlineKeyboardMarkup = SpecialityButtonRegister.getSpecialtyKeyboard(specialties, callback);
        sendMessage.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
        sendMessage.setText("Спеціальності по галузі "+branchOfKnowledge+" :");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage handleSpeciality(Update update, Map<String, String> callback) {
        cleanRequests();

        if(isReturnButtonPressed(callback)){
            if(EnumSetUtil.isEnumSet(callback.get("root"))){
                var enumSet = EnumSetUtil.decode(Integer.parseInt(callback.get("root")), Subject.class);
                return handleFiltered(update, enumSet);
            } else {
                callback.put("text", callback.get("root"));
                return handleBranchType(update,callback);
            }
        }

        String branchName = filter.getBranchOfKnowledgeName(callback.get("previous"));
        Specialty specialty = filter.getSpecialtyByName(callback.get("text"));
        sendMessage.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
        sendMessage.setText(SpecialityButtonRegister.getSubjectsText(specialty,branchName));
        sendMessage.setReplyMarkup(SpecialityButtonRegister.getSubjectsKeyboard(specialty));

        return sendMessage;
    }

    public SendMessage handleFiltered(Update update, EnumSet<Subject> enumSet) {
        message = update.getCallbackQuery().getMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        var branches = filter.getFiltered(enumSet);
        var branchesOfKnowledge = filter.getBranchesByName(branches);
        sendMessage.setReplyMarkup(SpecialityButtonRegister.getBranchOfKnowledgeKeyboard(branchesOfKnowledge,enumSet));
        sendMessage.setText("Галузі по вибраним предметам: ");
        return sendMessage;
    }

    protected static boolean isReturnButtonPressed(Map<String, String> callback){
        return callback.get("text").equalsIgnoreCase("Назад");
    }




}
