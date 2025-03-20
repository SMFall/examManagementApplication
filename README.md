# 📝 Exam Management Application

Cette application est un système de gestion d'examens développé en Spring Boot et Thymeleaf, permettant de gérer des utilisateurs (enseignants, étudiants et administrateurs), des cours, des examens, des quiz et des questions. 
Elle offre à la fois une interface web et une API REST pour effectuer les opérations CRUD (Create, Read, Update, Delete) sur les différentes entités.

---  

## 📖 Table des matières  

- [👤 Présentation de notre équipe](#-présentation-de-notre-équipe)  
- [🎯 Présentation du projet](#-présentation-de-notre-équipe)  
- [📁 Structure du dépôt](#-structure-du-dépôt)
- [🗃️ Schéma de la base de données](#-schema-bdd)
- [📦 Dépendances](#-dépendances)
- [🛠️ Modifications et améliorations](#%EF%B8%8F-modifications-et-améliorations)  
- [🚀 Lancer l’application](#-lance-app)   
- [👁️ Observations et points forts](#observations-points-forts)   

---

## 👤 Présentation de notre équipe

Notre équipe est composée de **CASTAGNO Thomas**, **FALL Sokhna Maye**, **RYCKEBUSCH Sabri**

---

## 🎯 Présentation du projet  

L’objectif principal est de proposer un système centralisé pour :

🔹 Gérer des utilisateurs (enseignants, étudiants et administrateurs)

🔹 Créer, consulter, modifier et supprimer des cours, quiz, examens, utilisateurs 

Le **front-end** est réalisé avec **Thymeleaf** (affichage des pages, formulaires) et le **back-end** avec **Spring Boot** et **Spring Data JPA** pour la persistance. 
Une **API REST** est également disponible pour permettre des intégrations ou des **appels AJAX** (par exemple, pour récupérer le premier examen d’un enseignant).

---

## 📁 Structure du dépôt  

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── amu
│   │   │           └── examManagement
│   │   │               ├── controllers
│   │   │               │   ├── AuthController.java
│   │   │               │   ├── ExamRestController.java
│   │   │               │   ├── ExceptionController.java
│   │   │               │   └── MainController.java
│   │   │               ├── model
│   │   │               │   ├── Course.java
│   │   │               │   ├── Exam.java
│   │   │               │   ├── Question.java
│   │   │               │   ├── Quiz.java
│   │   │               │   └── Users.java
│   │   │               ├── repositories
│   │   │               │   ├── CourseRepository.java
│   │   │               │   ├── ExamRepository.java
│   │   │               │   ├── QuestionRepository.java
│   │   │               │   ├── QuizRepository.java
│   │   │               │   └── UsersRepository.java
│   │   │               ├── services
│   │   │               │   ├── CourseService.java
│   │   │               │   ├── CourseServiceImpl.java
│   │   │               │   ├── ExamService.java
│   │   │               │   ├── ExamServiceImpl.java
│   │   │               │   ├── QuestionService.java
│   │   │               │   ├── QuestionServiceImpl.java
│   │   │               │   ├── QuizService.java
│   │   │               │   ├── QuizServiceImpl.java
│   │   │               │   ├── UsersService.java
│   │   │               │   └── UsersServiceImpl.java
│   │   │               └── ExamManagementApplication.java
│   │   ├── resources
│   │   │   ├── static
│   │   │   │   └── css
│   │   │   │       └── style.css
│   │   │   │   └── images
│   │   │   │       └── favicon.ico
│   │   │   └── templates
│   │   │   │   ├── 404.html
│   │   │   │   ├── course-form.html
│   │   │   │   ├── courses.html
│   │   │   │   ├── dashboard.html
│   │   │   │   ├── exam-form.html
│   │   │   │   ├── exams.html
│   │   │   │   ├── homepage.html
│   │   │   │   └── login.html
│   │   └── application.properties
├── .gitattributes
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
└── README.md
```

---

## 🗃️ Schéma de la base de données  

### Les tables principales :

🔹 users : stocke les informations d’authentification et de rôle (admin/teacher/student). 

🔹 question : représente une question d’examen, avec 4 options possibles.

🔹 quiz : représente un quiz.

🔹 course : stocke les informations d’un cours (nom, description, etc.).

🔹 exam : représente un examen, lié à un cours et à un enseignant.

🔹 exam_students : table d’association.

🔹 course_students : table d’association.

🔹 quiz_question : table d’association.

---

## 📦 Dépendances  

Ce projet utilise notamment :  

- **Spring Boot**
- **Spring Data JPA**
- **Gradle Groovy**
- **Thymeleaf**  
- **Hibernate**  
- **H2 Database**

---

## 🛠️ Modifications et améliorations

Au fil du développement, plusieurs améliorations ont été apportées :

🔹 Formulaire d’ajout d’examen, de cours et d'utilisateur.

🔹 Gestion des rôles (admin/teacher/student) pour adapter le contenu du dashboard.

---

## 🚀 Lancer l’application

### Cloner ce dépôt
```text
git clone https://github.com/SMFall/examManagementApplication/exam-management.git
```

### Lancer l’application
```text
Run 'ExamManagementApplication'
```

### Accéder à l’interface
[http://localhost:8081/h2-console/](http://localhost:8081/)

### Accéder à la gestion de la base de données
[http://localhost:8081/h2-console/](http://localhost:8081/h2-console/)
```text
Username : user
Password : password
```

## 👁️ Observations et points forts  

🔹 Architecture claire (séparation Controllers / Services / Repositories / Entités)

🔹 API REST pour les opérations CRUD

🔹 Intégration de Thymeleaf pour le front-end

🔹 Système d’authentification (sécurite car le dashboard nécessite un login).

---

# *Bonne gestion d’examens* ✅
