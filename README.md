# MyCommerce: Big Data Analytics Project for E-commerce

![Project Status](https://img.shields.io/badge/Status-Completed-brightgreen.svg)  
![Tech Stack](https://img.shields.io/badge/Tech%20Stack-Java%2017%20|%20Spring%20Boot%20|%20Python%20|%20PySpark%20|%20Kafka%20|%20MySQL-blue)

## 🚀 Overview

**MyCommerce** serves as a testament to the potential of real-time analytics and efficient data handling for order transactions. This project simulates rapid order generation and utilizes industry-standard frameworks to efficiently handle, process, and analyze large-scale data.

Key Features:  
- **Real-time analytics** with Kafka and PySpark.  
- Data insights like **top 10 selling products**, aiding decision-making.  
- E-commerce workflow simulation, including inventory management and order processing.  

---

## 🛠️ Tech Stack

### Languages and Frameworks:
- **Java 17 and Spring Boot**: Core business logic, REST API, and backend functionality.  
- **Python and PySpark**: Big Data processing and analytics. Visualization and additional data manipulation.

### Tools and Libraries:
- **Apache Kafka**: Real-time message streaming.  
- **Liquibase**: Database version control and schema management.  
- **Quartz Scheduler**: Periodic job scheduling for report generation.  
- **Matplotlib**: Data visualization (e.g., bar charts for insights).  

### Database:
- **MySQL**: Relational database for transactional data storage.  


---

## 🔍 Architecture

The project comprises four main components:

1. **Database (MySQL)**:  
   - The relational database management system (RDBMS) comprises essential tables such as `Customer`, `Area`, `Product`, `Order`, `Order-Items`, and `Inventory`.

2. **Producer Application (Java)**:  
   - Simulates rapid electronic appliance order generation at two orders/second.  
   - Generates a report every 15 seconds for top-selling products and transmits it via Kafka.

3. **Kafka**:  
   - Facilitates communication between Java and PySpark applications.

4. **Consumer Application (PySpark)**:  
   - Processes reports from Kafka and visualizes the top 10 selling products using Matplotlib.
     
![arch](https://github.com/user-attachments/assets/63f99467-b300-46c2-a182-1a5d9b4bdf52)

---

## 📊 Results and Insights

- **Insights Generated**:  
   - Top 10 selling products in real-time.  
- **Future Enhancements**:  
   - Deeper trend analytics (e.g., peak sales hours, seasonal variations, top regions having the most sales, etc.).  
   - Predictive analytics to forecast sales.  
   - Improved visualization for enhanced stakeholder understanding.

---

## 🔧 How to Run

### Prerequisites
- **Java 17+**  
- **Apache Kafka**  
- **MySQL**  
- **PySpark**  
- **Python(with Matplotlib and PySpark libraries installed)**
- **Jupyter Notebook** (Optional): For interactive PySpark development and data visualization

### Steps
1. **Clone the repository**.
2. **Start MySQL Server and Kafka broker(s)**.

#### Producer (Java) Application User Guide:  
- Configure the MySQL database username and password and the Kafka brokers in `application.yml`.  
- Initialize the schema using Liquibase on application startup.

#### Consumer (PySpark) Application User Guide:  
- Open the `MyCommerceConsumer.ipynb` file in Jupyter Notebook (or any other alternative tool you choose).  
- Replace the value of `bootstrap_servers` in the code as shown below:
  ```python
  consumer = KafkaConsumer(
      bootstrap_servers='<bootstrap servers>',
      value_deserializer=lambda v: json.loads(v.decode('ascii')),
      auto_offset_reset='earliest'
  )

---

## 📈 Visualization

The following is a demo that shows the project in action:-

https://github.com/user-attachments/assets/8cca73af-3b70-4b5e-907a-1a735943a9d4

---

## Contributions

We welcome contributions to enhance the functionality or address issues. Here’s how you can contribute:
1. **Fork the repository** and create a new branch for your changes.
2. **Submit a pull request** describing the feature or fix.
3. Feel free to open an issue for any bugs or feature suggestions.

Your contributions are greatly appreciated!
