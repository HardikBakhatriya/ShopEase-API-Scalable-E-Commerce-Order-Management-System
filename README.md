# 🛒 **ShopEase API: Scalable E-Commerce Order Management System**  
_A RESTful API-based Order Processing System using **Java Servlets, JDBC, and MySQL**_  

---

## 📌 **Project Overview**  
**ShopEase API** is a **scalable e-commerce order management system** designed to handle **order processing, customer management, and product management** efficiently. It enables:  
✅ **Customer and product management**  
✅ **Order creation, updating, and deletion**  
✅ **Order item management**  
✅ **RESTful API communication using HTTP methods (GET, POST, PUT, DELETE)**  

---

## 🏗️ **Technologies Used**  
| Technology  | Purpose |
|-------------|--------------------------------------------------|
| **Java Servlets** | Backend API handling |
| **MySQL** | Database to store orders, customers & products |
| **JDBC** | Connecting Java application with MySQL |
| **Gson (Google JSON Library)** | JSON parsing for request & response handling |
| **Postman** | API testing tool |

---

## 📂 **Project Structure**  
```
📦 src/
 ┣ 📂 db/               # Database connection setup
 ┃ ┗ 📜 DBConnector.java
 ┣ 📂 model/            # Business logic layer
 ┃ ┣ 📜 OrderModel.java
 ┃ ┣ 📜 OrderItem.java
 ┃ ┗ 📜 BookingAuthenticator.java
 ┣ 📂 controller/       # API handlers (Servlets)
 ┃ ┣ 📜 CreateOrderServlet.java
 ┃ ┣ 📜 RetrieveOrderServlet.java
 ┃ ┣ 📜 UpdateOrderServlet.java
 ┃ ┣ 📜 DeleteOrderServlet.java
 ┃ ┣ 📜 AddOrderItemServlet.java
 ┃ ┗ 📜 DeleteOrderItemServlet.java
```

---

## 🛠 **Database Schema**  
This project consists of **five main tables**:  

### **1️⃣ Customer Table**  
Stores customer details.  
| Column       | Data Type      | Constraints |
|-------------|---------------|-------------|
| customer_id | INT           | PRIMARY KEY, AUTO_INCREMENT |
| first_name  | VARCHAR(50)   | NOT NULL |
| last_name   | VARCHAR(50)   | NOT NULL |

### **2️⃣ Contact_Mech Table**  
Stores customer contact details.  
| Column       | Data Type      | Constraints |
|-------------|---------------|-------------|
| contact_mech_id | INT           | PRIMARY KEY, AUTO_INCREMENT |
| customer_id     | INT           | FOREIGN KEY REFERENCES Customer(customer_id) |
| street_address  | VARCHAR(100)  | NOT NULL |
| city           | VARCHAR(50)   | NOT NULL |
| state          | VARCHAR(50)   | NOT NULL |
| postal_code    | VARCHAR(20)   | NOT NULL |
| phone_number   | VARCHAR(20)   | NULL |
| email          | VARCHAR(100)  | NULL |

### **3️⃣ Product Table**  
Stores product details.  
| Column       | Data Type      | Constraints |
|-------------|---------------|-------------|
| product_id  | INT           | PRIMARY KEY, AUTO_INCREMENT |
| product_name| VARCHAR(100)  | NOT NULL |
| color       | VARCHAR(30)   | NULL |
| size        | VARCHAR(10)   | NULL |

### **4️⃣ Order_Header Table**  
Stores order information.  
| Column                   | Data Type      | Constraints |
|--------------------------|---------------|-------------|
| order_id                | INT           | PRIMARY KEY, AUTO_INCREMENT |
| order_date              | DATE          | NOT NULL |
| customer_id             | INT           | FOREIGN KEY REFERENCES Customer(customer_id) |
| shipping_contact_mech_id| INT           | FOREIGN KEY REFERENCES Contact_Mech(contact_mech_id) |
| billing_contact_mech_id | INT           | FOREIGN KEY REFERENCES Contact_Mech(contact_mech_id) |

---

## 🚀 **API Endpoints & Usage**  

### **1️⃣ Create an Order**  
🔹 **Endpoint:** `POST /orders`  
🔹 **Description:** Creates a new order for a customer.  
🔹 **Request Body:**  
```json
{
  "order_date": "2024-02-27",
  "customer_id": 1,
  "shipping_contact_mech_id": 101,
  "billing_contact_mech_id": 102,
  "order_items": [
    {
      "product_id": 1,
      "quantity": 2,
      "status": "Pending"
    }
  ]
}
```
🔹 **Response:**  
```json
{ "message": "Order created successfully", "order_id": 101 }
```

### **2️⃣ Retrieve Order Details**  
🔹 **Endpoint:** `GET /orders/{order_id}`  
🔹 **Response:**  
```json
{
  "order_id": 101,
  "order_date": "2024-02-27",
  "customer_id": 1,
  "items": [
    { "product_id": 1, "quantity": 2, "status": "Pending" }
  ]
}
```

### **3️⃣ Update an Order**  
🔹 **Endpoint:** `PUT /orders/{order_id}`  
🔹 **Request Body:**  
```json
{ "order_date": "2024-02-28" }
```
🔹 **Response:**  
```json
{ "message": "Order updated successfully" }
```

### **4️⃣ Delete an Order**  
🔹 **Endpoint:** `DELETE /orders/{order_id}`  
🔹 **Response:**  
```json
{ "message": "Order deleted successfully" }
```

---

## 🛠 **Setup & Installation**  

### **1️⃣ Setup MySQL Database**  
```sql
CREATE DATABASE ecommerce_db;
USE ecommerce_db;
-- Run SQL scripts to create tables
```

### **2️⃣ Run the Java Application**  
```bash
javac -cp gson-2.8.9.jar;. src/controller/*.java src/model/*.java src/db/DBConnector.java
java -cp gson-2.8.9.jar;. src.controller.CreateOrderServlet
```

### **3️⃣ Test API with Postman**  
- **Import API endpoints** into **Postman**.  
- **Send test requests** and **validate responses**.

---

## 🔥 **Future Enhancements**  
✅ **🔐 JWT Authentication** → Secure API endpoints  
✅ **📊 Admin Dashboard** → Manage orders & users  
✅ **📦 Inventory Management System** → Track stock availability  

---

## 📜 **Author**  
👨‍💻 **Hardik**  
📧 Contact: [Your Email]  
📌 GitHub Repo: [Your Repo Link]  

---

## 🎯 **Conclusion**  
**ShopEase API** is a **highly scalable** and **efficient e-commerce order management system**. It ensures **smooth order processing, structured database design, and RESTful API integration** for real-world e-commerce applications.  

---

Bhai, ab ye **properly structured, GitHub-friendly, aur professional README.md** file **tera project represent karne ke liye perfect hai!** 🚀🔥  
Agar koi **aur customization** chahiye ho to bata, I'll tweak it for you! 😃💯
