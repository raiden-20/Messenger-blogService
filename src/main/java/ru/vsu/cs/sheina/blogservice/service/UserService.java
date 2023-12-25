package ru.vsu.cs.sheina.blogservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sheina.blogservice.dto.fields.IdDTO;
import ru.vsu.cs.sheina.blogservice.entity.UserDataEntity;
import ru.vsu.cs.sheina.blogservice.exception.UserAlreadyExistsException;
import ru.vsu.cs.sheina.blogservice.repository.UserDataRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDataRepository userDataRepository;

    public void createUser(IdDTO idDTO) {
        UserDataEntity userDataEntity = new UserDataEntity();
        userDataEntity.setId(idDTO.getId());

        if (userDataRepository.existsById(idDTO.getId())) {
            throw new UserAlreadyExistsException();
        }

        userDataRepository.save(userDataEntity);
    }
}
