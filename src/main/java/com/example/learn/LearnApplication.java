package com.example.learn;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseCredentials;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class LearnApplication {

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        String path = LearnApplication.class.getClassLoader().getResource("fir-test-a9194-firebase-adminsdk-7c8ge-9575b1e975.json").getPath();
        FileInputStream serviceAccount = new FileInputStream(path);


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                .setDatabaseUrl("https://fir-test-a9194.firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(options);
        return FirebaseAuth.getInstance();
    }

    public static void main(String[] args) {
        SpringApplication.run(LearnApplication.class, args);
    }


}
