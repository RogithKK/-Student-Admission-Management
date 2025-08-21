# 🎓 Student Admission Management (Java + MySQL + Swing)

A simple Java Swing application to manage student admissions with MySQL.  
It includes two parts:

1. **Admission_Form.java** → Allows new students to register and insert their data into the database.  
2. **AdmissionViewer.java** → Provides a secure login and displays all student records in a table (JTable).

---

## ✨ Features
- Student Admission Form with:
  - Student ID, Name, Father’s Name, Address
  - Gender, Date of Birth, Date of Joining
  - Course selection (dropdown)
  - Phone number (with country code), Email, Nationality
  - High School Mark, Password (with show/hide option)
  - Emergency Contact, Blood Group
- Validation:
  - Phone & Emergency numbers must be **10 digits**
  - Date format check → `DD/MM/YYYY`
- Stores data into MySQL `students` table
- Reset button to clear the form
- Viewer app with login prompt (`root`/`root`)
- Displays records dynamically using JTable

---

## 🛠️ Technologies Used
- **Java Swing (GUI)**
- **JDBC (MySQL Database Connection)**
- **MySQL** (Database)

---

## 📂 Database Setup
1. Install MySQL and create a database:
   ```sql
   CREATE DATABASE admission;
   ```
2. Create the `students` table:
   ```sql
   CREATE TABLE students (
       student_id VARCHAR(20) PRIMARY KEY,
       student_name VARCHAR(100),
       father_name VARCHAR(100),
       address VARCHAR(255),
       dob DATE,
       doj DATE,
       course VARCHAR(100),
       phone VARCHAR(15),
       email VARCHAR(100),
       nationality VARCHAR(50),
       id_mark VARCHAR(100),
       password VARCHAR(100),
       emergency_contact VARCHAR(15),
       blood_group VARCHAR(5)
   );
   ```
3. Update your MySQL **username** and **password** in both files:
   ```java
   String url = "jdbc:mysql://localhost:3306/admission";
   String user = "root";
   String password = "root";
   ```

---

## ▶️ How to Run
1. Compile both files:
   ```bash
   javac -cp .;mysql-connector-j-8.0.xx.jar src/Admission_Form.java src/AdmissionViewer.java
   ```
   *(replace `xx` with your connector version)*
   
2. Run the Admission Form:
   ```bash
   java -cp .;mysql-connector-j-8.0.xx.jar src.Admission_Form
   ```
   
3. Run the Viewer App:
   ```bash
   java -cp .;mysql-connector-j-8.0.xx.jar src.AdmissionViewer
   ```

---

## 🔐 Login for Viewer
- **Username:** `root`  
- **Password:** `root`

*(You can modify this inside `AdmissionViewer.java` or connect it with a users table for real authentication.)*

---

## 📸 Screenshots (optional)
- Admission Form GUI  
<img src="./Screenshot 2025-08-21 140838.png" alt="App Screenshot"/>
- Viewer JTable with data  
<img src="./Screenshot 2025-08-21 140855.png" alt="App Screenshot"/>
<img src="./Screenshot 2025-08-21 140951.png" alt="App Screenshot"/>
---

## 🤝 Contributing
Feel free to fork this repo, improve it, and create pull requests.  

---

## 📜 License
This project is licensed under the MIT License.
