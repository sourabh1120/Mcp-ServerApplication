# IPL 2025 Schedule Service

This is a Spring Boot application that provides an IPL 2025 schedule service. It offers various endpoints to retrieve information about IPL matches, including filtering by team, date, venue, and match number. The schedule is preloaded with match data for the IPL 2025 season.

## Features

- Preloaded IPL 2025 match data.
- Endpoints to retrieve matches by:
    - Team (home/away)
    - Date
    - Date range
    - Venue
    - Match number
- Methods to fetch all matches or filtered matches based on criteria.

## Tech Stack

- **Java**: 17+
- **Spring Boot**: 2.x or higher
- **Spring Web**: For creating RESTful web services
- **Spring Dependency Injection**: For managing the services
- **Java 8 Streams**: For filtering and processing match data
- **JPA (optional)**: If you need persistence or database support (not included by default)
- **Lombok**: For reducing boilerplate code (optional)

## Installation

### 1. Clone the Repository

```bash
https://github.com/sourabh1120/McpServerRepository.git

 Build the Project
mvn clean install

```API Endpoints

The service exposes the following endpoints:

1. Get All Matches

Retrieve all IPL 2025 matches.

GET /api/ipl/schedule/matches

2. Get Matches by Team

Retrieve all matches where a specific team is playing (either home or away).

GET /api/ipl/schedule/team/{teamName}

Example:

GET /api/ipl/schedule/team/Kolkata Knight Riders

3. Get Matches by Date

Retrieve all matches on a specific date.

GET /api/ipl/schedule/date/{date}

Example:

GET /api/ipl/schedule/date/2025-03-22

4. Get Matches by Date Range

Retrieve all matches between a specified date range.

GET /api/ipl/schedule/dates/{startDate}/{endDate}

Example:

GET /api/ipl/schedule/dates/2025-03-22/2025-04-01

5. Get Matches by Venue

Retrieve all matches held at a specific venue.

GET /api/ipl/schedule/venue/{venue}

Example:

GET /api/ipl/schedule/venue/Kolkata

6. Get Match by Match Number

Retrieve a specific match by its unique match number.

GET /api/ipl/schedule/match/{matchNumber}

Example:

GET /api/ipl/schedule/match/1

Configuration

You can customize the IPL schedule data or add more logic as per your requirements.

Modify the ScheduleService class to update match data or add new functionality.

The data is currently hardcoded in the loadIPLSchedule() method.