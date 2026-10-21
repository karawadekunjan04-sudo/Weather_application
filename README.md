# 🌦️ Weather Application

A web-based Weather Application developed using **Java Servlets, JSP, HTML, CSS, and JavaScript**. It uses the OpenWeather API to fetch and display real-time weather information for a city entered by the user.

## 🚀 Features

- 🔍 **Search by City:** Get weather details for your selected city.
- 🌡️ **Temperature:** Display temperature in Celsius.
- ☁️ **Weather Conditions:** View current weather conditions.
- 💧 **Humidity:** Check the current humidity level.
- 💨 **Wind Speed:** Display wind speed information.
- 📅 **Date Display:** Show the date with the weather details.
- 🌐 **Real-Time Data:** Fetch current weather information through the OpenWeather API.

## 🛠️ Tech Stack

- **Frontend:** HTML, CSS, JavaScript, JSP
- **Backend:** Java Servlets
- **API:** OpenWeather API
- **JSON Parsing:** Gson
- **Server:** Apache Tomcat 10.1
- **JDK:** Java 21
- **IDE:** Eclipse

## 📂 Project Structure

```text
MyWeatherApp/
├── src/
│   └── main/
│       └── webapp/
│           ├── index.html
│           └── index.jsp
├── Java Resources/
│   └── MyServlet.java
└── README.md
```

*Note: Adjust the structure to match your actual Eclipse project.*

## ⚙️ How It Works

1. The user enters a city name in the application.
2. The frontend sends the request to the Java Servlet.
3. The servlet calls the OpenWeather API to retrieve current weather data.
4. Gson parses the JSON response.
5. The servlet forwards the weather details to the JSP page.
6. The JSP displays the temperature, weather condition, humidity, wind speed, and date.

## 🔧 Installation and Setup

### Prerequisites

- Java JDK 21
- Eclipse IDE
- Apache Tomcat 10.1
- Internet connection
- OpenWeather API key

### Steps to Run

1. Clone or download this repository.
2. Import the project into Eclipse.
3. Configure Apache Tomcat 10.1 as the server.
4. Obtain an API key from [OpenWeather](https://openweathermap.org/api).
5. Configure the API key in your servlet code.
6. Run the project on the Tomcat server.
7. Open the application in your browser and search for a city.

## 🔗 API Used

**OpenWeather Current Weather API**

```text
https://api.openweathermap.org/data/2.5/weather
```

The API retrieves current weather information based on the city name and returns the data in JSON format.

## 🎯 Project Objective

The objective of this project is to develop a dynamic weather application that demonstrates Java web development, servlet-based request handling, API integration, JSON parsing, and JSP-based presentation of real-time weather data.
