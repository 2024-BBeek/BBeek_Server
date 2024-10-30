package com.bbeek.server.domain.auth.service.implementation;

import com.bbeek.server.domain.allergy.domain.Allergy;
import com.bbeek.server.domain.allergy.domain.repository.AllergyRepository;
import com.bbeek.server.domain.auth.presentation.dto.request.JoinUserRequest;
import com.bbeek.server.domain.halal.domain.Halal;
import com.bbeek.server.domain.halal.domain.repository.HalalRepository;
import com.bbeek.server.domain.user.domain.User;
import com.bbeek.server.domain.user.domain.repository.UserRepository;
import com.bbeek.server.global.exception.BbeekException;
import com.bbeek.server.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserJoiner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AllergyRepository allergyRepository;
    private final HalalRepository halalRepository;


    @Transactional
    public void joinProcess(JoinUserRequest joinUserRequest) {

        String userId = joinUserRequest.userId();
        String password = joinUserRequest.password();

        Boolean isExist = userRepository.existsByUserId(userId);

        if (isExist) {
            throw new BbeekException(ErrorCode.USER_EXISTED);
        }

        User user = User.normalUserBuilder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .username(joinUserRequest.username())
                .vegetarianType(joinUserRequest.vegetarianType())
                .age(joinUserRequest.age())
                .gender(joinUserRequest.gender())
                .height(joinUserRequest.height())
                .weight(joinUserRequest.weight())
                .build();

        User savedUser = userRepository.save(user);

        // Save allergies
        if (joinUserRequest.allergy() != null) {
            for (String allergyInfo : joinUserRequest.allergy()) {
                Allergy allergy = Allergy.builder()
                        .info(allergyInfo)
                        .userId(savedUser.getId())
                        .build();
                allergyRepository.save(allergy);
            }
        }
        if (joinUserRequest.halal() != null) {
            for (String halalInfo : joinUserRequest.halal()) {
                Halal halal = Halal.builder()
                        .info(halalInfo)
                        .userId(savedUser.getId())
                        .build();
                halalRepository.save(halal);
            }
        }

    }
}
