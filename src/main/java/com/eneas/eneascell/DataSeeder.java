package com.eneas.eneascell;

import com.eneas.eneascell.auth.domain.User;
import com.eneas.eneascell.auth.domain.UserRole;
import com.eneas.eneascell.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByEmail("admin@eneas.com").isEmpty()) {

            User admin = new User();
            admin.setNome("Admin");
            admin.setEmail("admin@eneas.com");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setRole(UserRole.ADMIN);

            userRepository.save(admin);

            System.out.println("Admin criado com sucesso!");
        }

    }
}
