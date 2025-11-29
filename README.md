# 📘 Hotel Room Booking System

A Java Full-Stack application built using Spring Boot, MySQL, and React/Angular that allows users to search rooms, book them, and manage reservations. Includes JWT-based authentication, admin dashboard, and role-based access control.

---


# 📑 Table of Contents

Project Overview

Features

Tech Stack

Architecture

Backend Setup (Spring Boot)

Frontend Setup (React-or-angular)

Database Schema

API Endpoints

Project Structure

Screenshots (Optional)

Future Enhancements

License

---


# 📌 Project Overview

The Hotel Room Booking System is a moderate-level full-stack application where users can:

Search available rooms

Book rooms

View their bookings

Login / register using JWT authentication

Admins can:

Manage rooms (CRUD)

View all bookings

Control availability

This project is ideal for learning Java Full Stack development, covering both backend and frontend with a real-world workflow.

---


# ⭐ Features

# 👤 User Features

Register and login

Search rooms by date, type, or price

Book rooms

View personal booking history

JWT-secured access

---


# 🛠  Admin Features

Add, update, delete rooms

View all bookings

Manage room inventory

Role-based access (Admin/User)

---


# 🚀 Additional Features

Booking conflict validation

Form validations

Notifications for success/error

Protected routes

Responsive UI

---


# 🛠 Tech Stack

Backend

Java 17+

Spring Boot

Spring Data JPA

Spring Security + JWT

MySQL

ModelMapper

Lombok

Frontend

Choose one:

React + Axios + React Router
OR

Angular + HttpClient + Guards

Tools / Build

Maven

Node.js

Postman / Thunder Client

Docker (optional)


---

# 📐 Architecture

Frontend (React/Angular)

        |
        
        | REST API (JSON)
        
        |
        
Backend (Spring Boot)

        |
        
        | JPA/Hibernate
        
        |
        
   MySQL Database

   ---

# 🗄 Database Schema
Tables

users

rooms

bookings

payments (placeholder)

---

# 🔗 API Endpoints

Authentication

Method	       Endpoint	           Description

POST	       /api/auth/register	   Register new user

POST	       /api/auth/login	     Login & get JWT

Rooms

Method	        Endpoint	        Description

GET	            /api/rooms	      Get all rooms

POST	          /api/rooms	      Add room (Admin)

PUT	           /api/rooms/{id}    Update room (Admin)

DELETE	       /api/rooms/{id}    Delete room (Admin)


Booking

Method	     Endpoint	            Description 

POST	      /api/bookings	        Create booking

GET	        /api/bookings/user	  Get user bookings

GET	        /api/bookings/all	    Admin: all bookings

DELETE	    /api/bookings/{id}	   Cancel booking


---


# 📁 Project Structure

Backend

src/main/java/com/project/hotel

│── controller

│── service

│── repository

│── model/entity

│── dto

│── config (JWT, Security)


 Frontend (React)
 
src/

│── components/

│── pages/

│── services/

│── hooks/

│── utils/


---


# 📌 Conclusion

The Hotel Room Booking System provides a complete, production-ready foundation for a real-world Java full-stack application.

By integrating Spring Boot, JWT authentication, MySQL, and a modern frontend framework (React/Angular), this project demonstrates essential skills required for full-stack development, including:

Secure user authentication

Role-based authorization

RESTful API design

Database modeling and validation

Frontend integration with backend services

Admin and user-level workflows

Responsive and user-friendly UI

This project is an excellent addition to any Java developer’s portfolio, showcasing the ability to build a full, end-to-end web application. It can be further extended with real payments, email notifications, or

analytics dashboards, making it a strong foundation for learning or real deployment.
