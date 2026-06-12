package com.example.myfirstapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Bhai ,  yeh username pehle se kisi ne le rakha hai!");
        }
        //String encryptedPassword = passwordEncoder.encode((user.getPassword()));
        //user.setPassword(encryptedPassword);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User register ho gaya hai ab Bhai tum login kar sakte ho!..");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest){
        System.out.println("-----LOGING METHOD HIT START----------");
        System.out.println("Username received: " +authRequest.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            System.out.println("===AUTHENTICATION SUCCESSS =====");
        }catch (Exception e){
            System.out.println("===== AUTHENTICATION FAILED: " +e.getMessage());
            return ResponseEntity.status(401).body("Username ya password galat hai bhai..");
        }
//        java.util.Optional<User> userOpt = userRepository.findByUsername(authRequest.getUsername());
//        if(userOpt.isEmpty())
//        {
//            return ResponseEntity.status(404).body("Bhai, yeh user authntication ke baad bh DB me nahi mila");
//        }
        //User user = userOpt.get();

        User user = userRepository.findByUsername(authRequest.getUsername()).orElseThrow();
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        System.out.println("--------------------------------");
        System.out.println("GENRETED TOKEN " +token);
        System.out.println("--------------------------------");
        return ResponseEntity.ok(token);
    }
    /*@GetMapping("/hello")
    public ResponseEntity<String> testHello()
    {
        System.out.println("======================================");
        System.out.println("============== DIRECTBHELLO METHOD HIT=============");
        System.out.println("====================================================");
        return ResponseEntity.ok("Backend Zinda hai bhai ");
    }*/

}
