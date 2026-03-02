# Online Bidding System 🔨

A robust, Java-based desktop application designed to facilitate secure and transparent online auctions. This project focuses on managing real-time bidding logic while ensuring data integrity through a structured MySQL backend.

 Overview
The Online Bidding System provides a platform where users can register, list items for auction, and place bids. The system is designed to handle the "Bidding War" logic, ensuring that only higher bids are accepted and the highest bidder is tracked accurately.

🛠️ Tech Stack
 Language: Java (JDK 8+)
 Database: MySQL
 Connectivity: JDBC (Java Database Connectivity)
 IDE: IntelliJ IDEA 

 ✨ Key Features
User Authentication: Secure login and registration for bidders and sellers.
 Live Bidding Logic:Real-time validation to ensure new bids are higher than the current maximum bid.
 Item Management: Sellers can add items with descriptions, starting prices, and end times.
 Database Integration: Persistent storage for users, items, and bid history using MySQL.
 Automatic Winner Determination:The system identifies the highest bidder once an auction concludes.

📂 Project Structure
```text
BiddingSystem/
├── src/                # Java source files (.java)
├── lib/                # Database connectors (MySQL Connector/J)
├── sql/                # Database schema and sample data scripts
└── README.md           # Project documentation

👤 Author
Saras Kumar Upadhyay
    Third Year B.Tech Student
    Computer Science & Engineering
