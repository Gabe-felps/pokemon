package br.com.pokemon.api.service;

import br.com.pokemon.api.config.SecurityConfig;
import br.com.pokemon.api.config.dto.*;
import br.com.pokemon.api.model.Role;
import br.com.pokemon.api.model.User;
import br.com.pokemon.api.repository.UserRepository;
import br.com.pokemon.api.security.AuthUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    @Autowired
    private SecurityConfig securityConfiguration;

    public RecoveryJwtTokenDTO authenticateUser(LoginUserDTO loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();

        return new RecoveryJwtTokenDTO(jwtTokenService.generateToken(userDetails));
    }

    public boolean createUser(CreateUserDTO createUserDto) {

        if (createUserDto.password().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$")) {
            User newUser = User.builder()
                    .name(createUserDto.name())
                    .email(createUserDto.email())
                    .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
                    .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                    .build();

            userRepository.save(newUser);
            return true;
        }
        return false;
    }

    public List<RecoveryUserDTO> getAllUser() {
        return userRepository.findAll().stream().map(user -> new RecoveryUserDTO(user.getId(), user.getName(), user.getEmail(), user.getRoles())).toList();
    }

    public boolean deleteById(Long id, String token) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (hasAuthorization(user, token)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public RecoveryUserDTO editUserById(Long id, EditUserDTO editUserDTO, String token) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (hasAuthorization(user, token)) {
            user.setName(editUserDTO.name());
            user.setEmail(editUserDTO.email());
            userRepository.save(user);
            return new RecoveryUserDTO(user.getId(), user.getName(), user.getEmail(), user.getRoles());
        }
        return null;
    }

    private boolean hasAuthorization(User user, String token) {
        String subject = jwtTokenService.getSubjectFromToken(token.replace("Bearer ", ""));
        User userToken = userRepository.findByEmail(subject).get();
        AuthUserDetails userDetails = (AuthUserDetails) authUserDetailsService.loadUserByUsername(subject);
        return userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) || user.equals(userToken);
    }

}
