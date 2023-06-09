package com.bot.service.database;

import com.bot.service.repository.RequestRepository;
import com.bot.model.entity.Request;
import com.bot.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestService {
    private final RequestRepository requestRepo;
    private final UserService userRepo;

    @Transactional(readOnly = true)
    public List<Request> list() {
        return requestRepo.findAll();
    }

    @Transactional
    public Request save(long chatID, long subjectSet) {
        User user = userRepo.save(chatID);
        Request request = new Request();
        request.setUser(user);
        request.setDatetime(Timestamp.from(Instant.now()));
        request.setSetSubjects(subjectSet);
        request = requestRepo.save(request);
        return request;
    }

    @Transactional
    public Request save(User x, long subjectSet) {
        return save(x.getId(), subjectSet);
    }
}