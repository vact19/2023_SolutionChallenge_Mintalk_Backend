# Introducing Mintalk.
Mintalk was created to alleviate mental pain and suffering.

### Frontend Github
https://github.com/GDSC-SKHU/Solution-Team4-minTalk-FrontEnd

## API docs
https://documenter.getpostman.com/view/16786212/2s93CGQF2V

## The features of Mintalk include
- Self-psychological tests (in progress)
- Real-time chat with counselors (in progress)
- Matching with psychiatrists.

## 1. We would like to share the problem that we are trying to solve.

We developed the site with a focus on situations where the happiness index is significantly low. Therefore, we developed features to eliminate the distance or discomfort people may feel when seeking psychological counseling, which can help improve mental health. We believed that many people could benefit from psychological counseling if we could address the stigma or discomfort that people may feel about seeking such help. By doing so, we hope to alleviate the mental pain and suffering of many people.

## 2. How do we plan to solve the problem?

I believe that if we can develop self-tests with an element of fun and allow users to converse with mental health professionals as if they were chatting with friends, they will be able to have a more intuitive understanding of their own mental health. Additionally, if people start saying things like 'Go take a test on Mintalk' naturally and positively, then I believe our site can actually contribute to improving people's mental health.

## 3. Please take a look at our service.

<p>
<img width="450" alt="initial" src="https://user-images.githubusercontent.com/104067367/228135196-395ff858-808d-44d7-88c9-00020e932c16.png">
<img width="400" alt="initial2" src="https://user-images.githubusercontent.com/104067367/228136301-fc6a6590-5ff3-41ee-bfb9-9eb73e95b46e.png">
<img width="450" alt="initial3" src="https://user-images.githubusercontent.com/104067367/228136810-8fcb7865-c3fe-42cf-97bc-e4d423ee5a2d.png">
<img width="385" alt="스크린샷 2023-03-28 오후 2 28 30" src="https://user-images.githubusercontent.com/104067367/228137320-af74efc2-1350-45d1-858f-ee7eb167902e.png">
<img width="1600" alt="스크린샷 2023-03-28 오후 2 34 39" src="https://user-images.githubusercontent.com/104067367/228138227-0a618466-870b-426f-8195-b26406d4369e.png">
</p>

## 4. We have developed using

- Spring Boot 2.7.8
- JDK 11
- MySQL, Spring Data JPA

## 5. directory tree (src/main/java/xyz/hugme/hugmebackend)


-   /api: Presentation Layer. includes controllers and DTOs directly related to user requests/responses. Subdirectories typically include controller, dto, and service. The controller has Handler Methods that process requests, while service classes in the api directory play an intermediary role, such as converting DTOs between service classes in the domain directory.
    -   /auth: Handles authentication-related requests.
    -   /client, /counselor: Code related to requests from counselors and clients.
    -   /common: Contains elements commonly used within the "/api" directory, such as HTTP response templates.
-   /domain: Business/Persistence Layer. Contains the core elements that make up the project. Code within this directory does not know about client requests.
    -   /file: Manages files, such as counselor profile pictures.
    -   /common: Contains elements commonly used within the "/domain" directory, such as a class for recording the time when data is saved to the database.
    -   /user: Contains information about counselors and clients.
    -   /usersession: Stores the server's session information in the database to provide automatic login functionality. Includes UserSession information for maintaining login status.
-   /global: Features that have a global impact on the project.
    -   /auth: User authentication logic.
    -   /config: Project configuration.
    -   /converter: Converter that helps with converting user input values. ex) user input value to Java Enum.
    -   /exception: Exception Handler for managing exceptions and Error Response Templates for consistent responses.
