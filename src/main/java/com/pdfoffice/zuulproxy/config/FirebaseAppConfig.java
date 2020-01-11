package com.pdfoffice.zuulproxy.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class FirebaseAppConfig {
    private FirebaseApp firebaseApp;

    @Autowired
    ServiceConfig serviceConfig;
    @PostConstruct
    public void init() throws IOException {
        FileInputStream inputStream = new FileInputStream("pdf-office-c3d21-firebase-adminsdk-q4jhe-5db60cf07d.json") ;

     FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .setDatabaseUrl(serviceConfig.getFirebaseDatabaseUrl())
                .build();
     inputStream.close();

        firebaseApp =FirebaseApp.initializeApp(options);
}
public FirebaseApp getFirebaseApp(){
        return firebaseApp;
}
}
