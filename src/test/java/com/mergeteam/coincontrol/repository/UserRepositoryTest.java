package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.entity.User;
import com.mergeteam.coincontrol.utils.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@RequiredArgsConstructor
public class UserRepositoryTest extends IntegrationBaseClass{

    private final UserRepository userRepository;

    @Test
    void checkPageable() {
        Pageable pageable = PageRequest.of(1,2, Sort.by("id"));
        Slice<User> slice = userRepository.findAllBy(pageable);
        slice.forEach((user -> System.out.println(user.getId())));

        while (slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach((user -> System.out.println(user.getId())));
        }
    }

//    @ParameterizedTest
}
