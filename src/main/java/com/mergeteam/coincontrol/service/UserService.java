package com.mergeteam.coincontrol.service;

import com.mergeteam.coincontrol.dto.UpdateUserDto;
import com.mergeteam.coincontrol.dto.WalletDto;
import com.mergeteam.coincontrol.entity.User;
import com.mergeteam.coincontrol.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;


//    public Slice<User> findAll(Pageable page) {
//       return userRepository.findAll(page);
//    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

//    public Page<WalletDto> getWalletsForUser(UUID id, Pageable pageable) {
//        User user = userRepository.findById(id)
//                .orElseThrow();
//        List<WalletDto> walletDtos = user.getWallets()
//                .stream()
//                .map(wallet -> new WalletDto(wallet.getId(), wallet.getName(), wallet.getBalance()))
//                .collect(Collectors.toList());
//        long offset = pageable.getOffset();
//        long size = Math.min(pageable.getOffset() + pageable.getPageSize(), walletDtos.size());
//        List<WalletDto> sublist = walletDtos.subList((int) offset, (int) size);
//        return new PageImpl<>(sublist, pageable, walletDtos.size());
//    }

    public User updateUser(UUID id, UpdateUserDto userDto) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setEmail(userDto.email());
            user.setName(userDto.name());
            user.setAvatarPath(userDto.avatarPath());
            return user;
        } else {
            throw new RuntimeException();   // TODO: add advicer to handle runtimeexcep
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
