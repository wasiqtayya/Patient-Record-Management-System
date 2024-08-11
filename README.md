# Patient Record Management System

## Overview

The **Patient Record Management System** is a Java-based application designed to efficiently manage and store patient records. Utilizing Firebase Database, the system ensures real-time data storage and synchronization. It provides functionalities for handling patient information, including personal details and comprehensive patient records.

## Features

- **Patient Registration:** Add new patient records with personal and contact information.
- **Record Management:** Maintain and update detailed patient records.
- **Real-time Data Sync:** Seamless data synchronization across devices using Firebase Database.
- **Data Security:** Secure storage and retrieval of patient data.

## Technologies Used

- **Java:** Core programming language for application development.
- **Firebase Database:** Real-time NoSQL database for storing patient records.
- **Firebase Authentication (Optional):** For user authentication and management.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Firebase account
- Android Studio or any preferred Java IDE

### Setup

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/yourusername/patient-record-management-system.git
    cd patient-record-management-system
    ```

2. **Set Up Firebase:**
    - Go to the [Firebase Console](https://console.firebase.google.com/).
    - Create a new project and add a new Firebase Database.
    - Download the `google-services.json` file from your Firebase project and place it in the `src/main/resources` directory of your project.

3. **Add Firebase Dependencies:**
    - Add the following Firebase dependencies to your `pom.xml` if using Maven, or your `build.gradle` if using Gradle.
    ```xml
    <!-- For Maven -->
    <dependency>
        <groupId>com.google.firebase</groupId>
        <artifactId>firebase-admin</artifactId>
        <version>VERSION_NUMBER</version>
    </dependency>
    ```

    ```gradle
    // For Gradle
    implementation 'com.google.firebase:firebase-admin:VERSION_NUMBER'
    ```

4. **Configure Firebase in Your Application:**
    - Initialize Firebase in your Java application using the `google-services.json` file.

    ```java
    import com.google.firebase.FirebaseApp;
    import com.google.firebase.FirebaseOptions;
    import com.google.auth.oauth2.GoogleCredentials;
    import java.io.FileInputStream;
    import java.io.IOException;

    public class FirebaseInit {
        public static void initializeFirebase() throws IOException {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/google-services.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://your-database-name.firebaseio.com/")
                .build();

            FirebaseApp.initializeApp(options);
        }
    }
    ```

5. **Run the Application:**
    - Compile and run your Java application to start managing patient records.

## Usage

- **Add Patient:** Use the application interface to input patient details and save them to Firebase.
- **Manage Records:** Update and manage comprehensive patient records through the application.

## Contributing

Feel free to submit issues or pull requests if you have suggestions for improvements or fixes.

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any questions or suggestions, please contact [Wasiq Tayyab](mailto:wasiqtayyab30@gmail.com).
