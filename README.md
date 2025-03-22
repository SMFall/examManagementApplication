# 📝 Exam Management Application

Cette application est un système de gestion d’examens développé en Spring Boot et Thymeleaf, permettant de gérer des utilisateurs (enseignants, étudiants et administrateurs), des cours, des examens, des quiz et des questions.

Elle offre à la fois : 

🔹 Une interface web (avec Thymeleaf)

🔹 Une API REST pour les opérations CRUD (Create, Read, Update, Delete) sur les différentes entités.

---  

## 📖 Table des matières  

- [👤 Présentation de notre équipe](#-présentation-de-notre-équipe)  
- [🎯 Présentation du projet](#-présentation-du-projet)  
- [📁 Structure du dépôt](#-structure-du-dépôt)
- [🗃️ Schéma de la base de données](#-schéma-de-la-base-de-données)
- [📦 Dépendances](#-dépendances)
- [🛠️ Modifications et améliorations](#%EF%B8%8F-modifications-et-améliorations)  
- [🚀 Lancer l’application](#-lance-application)
- [🌐 Utiliser l’API REST : exemples d’appels](#-utiliser-api-rest)
- [🔑 Gestion des rôles et authentification](#-gestion-auth)
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

### Les tables d'association :

🔹 exam_students

🔹 course_students

🔹 quiz_question

---

## 📦 Dépendances  

Ce projet utilise notamment :  

- **Java 17 (conseillé)**
- **Spring Boot**
- **Spring Data JPA**
- **Gradle (Groovy)**
- **Thymeleaf**  
- **Hibernate**  
- **H2 Database** (base de données en mémoire)

---

## 🛠️ Modifications et améliorations

Au fil du développement, plusieurs améliorations ont été apportées :

🔹 Formulaires d’ajout d’examen, de cours et d’utilisateur.

🔹 Gestion des rôles (admin/teacher/student) pour adapter le contenu du dashboard.

🔹 Sécurité basique (connexion nécessaire pour accéder au dashboard).

🔹 Calendrier pour afficher les examens d’un étudiant.

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
[http://localhost:8081/](http://localhost:8081/)

### Accéder à la gestion de la base de données
[http://localhost:8081/h2-console/](http://localhost:8081/h2-console/)
```text
Username : user
Password : password
```

## 🌐 Utiliser l’API REST : exemples d’appels

### Récupérer la liste de tous les examens
```text
Méthode : GET
URL : /api/exams
```
Exemple :
```text
curl -X GET http://localhost:8081/api/exams
```

### Créer un nouvel examen
```text
Méthode : POST
URL : /api/exams
```
Body (JSON) :
```json
{
  "examTitle": "Examen sur Spring Boot",
  "examDate": "2025-05-10"
}
```

### Obtenir le premier examen d’un enseignant
```text
Méthode : GET
URL : /api/exams/firstExam/{teacherId}
```
Exemple :
```text
curl -X GET http://localhost:8081/api/exams/firstExam/1
```

### Mettre à jour un examen

```text
Méthode : PUT
URL : /api/exams/{id}
```
Body (JSON) :
```json
{
  "examTitle": "Examen modifié",
  "examDate": "2025-06-15"
}
```

### Supprimer un examen

```text
Méthode : DELETE
URL : /api/exams/{id}
```
Exemple :
```text
curl -X DELETE http://localhost:8081/api/exams/10
```

## 🔑 Gestion des rôles et authentification

### Le projet propose une authentification basique

Rôles disponibles : admin, teacher, student

Lorsqu’un utilisateur se connecte, le système vérifie son rôle et affiche un dashboard adapté.

Certaines pages sont restreintes (nécessitent d’être connecté).

### Exemples de scénarios
Un professeur (teacher) verra uniquement ses examens.

Un admin verra tous les examens et pourra administrer l’ensemble des données.

Un étudiant (student) accédera à son calendrier et à la liste de ses examens.


## 👁️ Observations et points forts

🔹 Architecture claire : séparation Controllers / Services / Repositories / Entités.

🔹 API REST pour les opérations CRUD.

🔹 Intégration de Thymeleaf pour le front-end.

🔹 Système d’authentification (sécurité basique, le dashboard nécessite un login).

🔹 Approche modulaire (facile à étendre ou à personnaliser).

---

# *Bonne gestion d’examens* ✅
