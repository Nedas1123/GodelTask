# GodelTask
Description:
This is a simple REST API for shortening URLs, similar to Bitly

## Purpose:
Helps with making those long and boring urls look short, nice and easy to share

## List of Features:
- Shorten long URLs.
- Retrieve shortened URLs.
- Error handling for invalid URLs.
- Prevent duplicate URLs from being saved.

## Technologies Used:
- Programming Language: Java.
- Framework: Spring Boot.
- Dependencies: apache.commons,org.springframework.boot.

## Installation:
- Prerequisites: Java version 17, Maven version 3 
- Installation Steps:
- Clone the repository:
- git clone <repository-url>
Navigate to the project directory:
cd <project-directory>
Build the project using Maven:
mvn clean install
Run the application:
mvn spring-boot:run

## Usage:
Save Short URL:
Endpoint: POST /api/shorten
Request Body:
Plain text
Response: Returns the shortened URL.
Get Short URL:
Endpoint: GET /api/getShortUrl
Parameters: url (the original URL).
Response: Returns the shortened URL if it exists.

## Error Handling
Common Error Responses:
Invalid URL format.
URL already exists.
URL not found.
