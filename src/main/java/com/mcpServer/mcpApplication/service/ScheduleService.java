package com.mcpServer.mcpApplication.service;

import com.mcpServer.mcpApplication.model.Match;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ScheduleService {

    private List<Match> matches;

    @PostConstruct
    public void init() {
        try {
            loadIPLSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadIPLSchedule() {
        matches = new ArrayList<>();

        // Initialize the schedule with the data from the PDF
        // First part of the schedule
        addMatch(1, 1, "22-03-2025", "Sat", "19:30", "Kolkata Knight Riders", "Royal Challengers Bengaluru", "Kolkata");
        addMatch(2, 2, "23-03-2025", "Sun", "15:30", "Sunrisers Hyderabad", "Rajasthan Royals", "Hyderabad");
        addMatch(3, 2, "23-03-2025", "Sun", "19:30", "Chennai Super Kings", "Mumbai Indians", "Chennai");
        addMatch(4, 3, "24-03-2025", "Mon", "19:30", "Delhi Capitals", "Lucknow Super Giants", "Visakhapatnam");
        addMatch(5, 4, "25-03-2025", "Tue", "19:30", "Gujarat Titans", "Punjab Kings", "Ahmedabad");
        addMatch(6, 5, "26-03-2025", "Wed", "19:30", "Rajasthan Royals", "Kolkata Knight Riders", "Guwahati");
        addMatch(7, 6, "27-03-2025", "Thu", "19:30", "Sunrisers Hyderabad", "Lucknow Super Giants", "Hyderabad");
        addMatch(8, 7, "28-03-2025", "Fri", "19:30", "Chennai Super Kings", "Royal Challengers Bengaluru", "Chennai");
        addMatch(9, 8, "29-03-2025", "Sat", "19:30", "Gujarat Titans", "Mumbai Indians", "Ahmedabad");
        addMatch(10, 9, "30-03-2025", "Sun", "15:30", "Delhi Capitals", "Sunrisers Hyderabad", "Visakhapatnam");
        addMatch(11, 9, "30-03-2025", "Sun", "19:30", "Rajasthan Royals", "Chennai Super Kings", "Guwahati");
        addMatch(12, 10, "31-03-2025", "Mon", "19:30", "Mumbai Indians", "Kolkata Knight Riders", "Mumbai");
        addMatch(13, 11, "01-04-2025", "Tue", "19:30", "Lucknow Super Giants", "Punjab Kings", "Lucknow");
        addMatch(14, 12, "02-04-2025", "Wed", "19:30", "Royal Challengers Bengaluru", "Gujarat Titans", "Bengaluru");
        addMatch(15, 13, "03-04-2025", "Thu", "19:30", "Kolkata Knight Riders", "Sunrisers Hyderabad", "Kolkata");
        addMatch(16, 14, "04-04-2025", "Fri", "19:30", "Lucknow Super Giants", "Mumbai Indians", "Lucknow");
        addMatch(17, 15, "05-04-2025", "Sat", "15:30", "Chennai Super Kings", "Delhi Capitals", "Chennai");
        addMatch(18, 15, "05-04-2025", "Sat", "19:30", "Punjab Kings", "Rajasthan Royals", "New Chandigarh");
        addMatch(19, 16, "06-04-2025", "Sun", "15:30", "Kolkata Knight Riders", "Lucknow Super Giants", "Kolkata");
        addMatch(20, 16, "06-04-2025", "Sun", "19:30", "Sunrisers Hyderabad", "Gujarat Titans", "Hyderabad");
        addMatch(21, 17, "07-04-2025", "Mon", "19:30", "Mumbai Indians", "Royal Challengers Bengaluru", "Mumbai");
        addMatch(22, 18, "08-04-2025", "Tue", "19:30", "Punjab Kings", "Chennai Super Kings", "New Chandigarh");
        addMatch(23, 19, "09-04-2025", "Wed", "19:30", "Gujarat Titans", "Rajasthan Royals", "Ahmedabad");
        addMatch(24, 20, "10-04-2025", "Thu", "19:30", "Royal Challengers Bengaluru", "Delhi Capitals", "Bengaluru");
        addMatch(25, 21, "11-04-2025", "Fri", "19:30", "Chennai Super Kings", "Kolkata Knight Riders", "Chennai");
        addMatch(26, 22, "12-04-2025", "Sat", "15:30", "Lucknow Super Giants", "Gujarat Titans", "Lucknow");
        addMatch(27, 22, "12-04-2025", "Sat", "19:30", "Sunrisers Hyderabad", "Punjab Kings", "Hyderabad");
        addMatch(28, 23, "13-04-2025", "Sun", "15:30", "Rajasthan Royals", "Royal Challengers Bengaluru", "Jaipur");
        addMatch(29, 23, "13-04-2025", "Sun", "19:30", "Delhi Capitals", "Mumbai Indians", "Delhi");
        addMatch(30, 24, "14-04-2025", "Mon", "19:30", "Lucknow Super Giants", "Chennai Super Kings", "Lucknow");
        addMatch(31, 25, "15-04-2025", "Tue", "19:30", "Punjab Kings", "Kolkata Knight Riders", "New Chandigarh");
        addMatch(32, 26, "16-04-2025", "Wed", "19:30", "Delhi Capitals", "Rajasthan Royals", "Delhi");
        addMatch(33, 27, "17-04-2025", "Thu", "19:30", "Mumbai Indians", "Sunrisers Hyderabad", "Mumbai");
        addMatch(34, 28, "18-04-2025", "Fri", "19:30", "Royal Challengers Bengaluru", "Punjab Kings", "Bengaluru");
        addMatch(35, 29, "19-04-2025", "Sat", "15:30", "Gujarat Titans", "Delhi Capitals", "Ahmedabad");
        addMatch(36, 29, "19-04-2025", "Sat", "19:30", "Rajasthan Royals", "Lucknow Super Giants", "Jaipur");
        addMatch(37, 30, "20-04-2025", "Sun", "15:30", "Punjab Kings", "Mumbai Indians", "New Chandigarh");
        addMatch(38, 30, "20-04-2025", "Sun", "19:30", "Mumbai Indians", "Chennai Super Kings", "Mumbai");

        // Second part of the schedule
        addMatch(39, 31, "21-04-2025", "Mon", "19:30", "Kolkata Knight Riders", "Gujarat Titans", "Kolkata");
        addMatch(40, 32, "22-04-2025", "Tue", "19:30", "Royal Challengers Bengaluru", "Chennai Super Kings", "Bengaluru");
        addMatch(41, 33, "23-04-2025", "Wed", "19:30", "Sunrisers Hyderabad", "Delhi Capitals", "Hyderabad");
        addMatch(42, 34, "24-04-2025", "Thu", "19:30", "Mumbai Indians", "Rajasthan Royals", "Mumbai");
        addMatch(43, 35, "25-04-2025", "Fri", "19:30", "Punjab Kings", "Lucknow Super Giants", "Dharamsala");
        addMatch(44, 36, "26-04-2025", "Sat", "15:30", "Chennai Super Kings", "Sunrisers Hyderabad", "Chennai");
        addMatch(45, 36, "26-04-2025", "Sat", "19:30", "Kolkata Knight Riders", "Delhi Capitals", "Kolkata");
        addMatch(46, 37, "27-04-2025", "Sun", "15:30", "Gujarat Titans", "Royal Challengers Bengaluru", "Ahmedabad");
        addMatch(47, 37, "27-04-2025", "Sun", "19:30", "Lucknow Super Giants", "Mumbai Indians", "Lucknow");
        addMatch(48, 38, "28-04-2025", "Mon", "19:30", "Rajasthan Royals", "Punjab Kings", "Jaipur");
        addMatch(49, 39, "29-04-2025", "Tue", "19:30", "Delhi Capitals", "Gujarat Titans", "Delhi");
        addMatch(50, 40, "30-04-2025", "Wed", "19:30", "Royal Challengers Bengaluru", "Mumbai Indians", "Bengaluru");
        addMatch(51, 41, "01-05-2025", "Thu", "19:30", "Sunrisers Hyderabad", "Chennai Super Kings", "Hyderabad");
        addMatch(52, 42, "02-05-2025", "Fri", "19:30", "Kolkata Knight Riders", "Rajasthan Royals", "Kolkata");
        addMatch(53, 43, "03-05-2025", "Sat", "15:30", "Punjab Kings", "Delhi Capitals", "Dharamsala");
        addMatch(54, 43, "03-05-2025", "Sat", "19:30", "Mumbai Indians", "Gujarat Titans", "Mumbai");
        addMatch(55, 44, "04-05-2025", "Sun", "15:30", "Chennai Super Kings", "Lucknow Super Giants", "Chennai");
        addMatch(56, 44, "04-05-2025", "Sun", "19:30", "Rajasthan Royals", "Sunrisers Hyderabad", "Jaipur");
        addMatch(57, 45, "05-05-2025", "Mon", "19:30", "Royal Challengers Bengaluru", "Kolkata Knight Riders", "Bengaluru");
        addMatch(58, 46, "06-05-2025", "Tue", "19:30", "Delhi Capitals", "Chennai Super Kings", "Delhi");
        addMatch(59, 47, "07-05-2025", "Wed", "19:30", "Gujarat Titans", "Punjab Kings", "Ahmedabad");
        addMatch(60, 48, "08-05-2025", "Thu", "19:30", "Lucknow Super Giants", "Rajasthan Royals", "Lucknow");
        addMatch(61, 49, "09-05-2025", "Fri", "19:30", "Mumbai Indians", "Delhi Capitals", "Mumbai");
        addMatch(62, 50, "10-05-2025", "Sat", "15:30", "Sunrisers Hyderabad", "Royal Challengers Bengaluru", "Hyderabad");
        addMatch(63, 50, "10-05-2025", "Sat", "19:30", "Chennai Super Kings", "Gujarat Titans", "Chennai");
        addMatch(64, 51, "11-05-2025", "Sun", "15:30", "Kolkata Knight Riders", "Punjab Kings", "Kolkata");
        addMatch(65, 51, "11-05-2025", "Sun", "19:30", "Rajasthan Royals", "Mumbai Indians", "Jaipur");
        addMatch(66, 52, "12-05-2025", "Mon", "19:30", "Lucknow Super Giants", "Delhi Capitals", "Lucknow");
        addMatch(67, 53, "13-05-2025", "Tue", "19:30", "Punjab Kings", "Sunrisers Hyderabad", "Dharamsala");
        addMatch(68, 54, "14-05-2025", "Wed", "19:30", "Gujarat Titans", "Lucknow Super Giants", "Ahmedabad");
        addMatch(69, 55, "15-05-2025", "Thu", "19:30", "Royal Challengers Bengaluru", "Rajasthan Royals", "Bengaluru");
        addMatch(70, 56, "16-05-2025", "Fri", "19:30", "Delhi Capitals", "Kolkata Knight Riders", "Delhi");
        addMatch(71, 57, "17-05-2025", "Sat", "15:30", "Chennai Super Kings", "Mumbai Indians", "Chennai");
        addMatch(72, 57, "17-05-2025", "Sat", "19:30", "Punjab Kings", "Royal Challengers Bengaluru", "Dharamsala");
        addMatch(73, 58, "18-05-2025", "Sun", "15:30", "Sunrisers Hyderabad", "Gujarat Titans", "Hyderabad");
        addMatch(74, 58, "18-05-2025", "Sun", "19:30", "Mumbai Indians", "Lucknow Super Giants", "Mumbai");

        // Playoff matches
        addMatch(75, 60, "20-05-2025", "Tue", "19:30", "TBD", "TBD", "Hyderabad", "Qualifier 1");
        addMatch(76, 61, "21-05-2025", "Wed", "19:30", "TBD", "TBD", "Hyderabad", "Eliminator");
        addMatch(77, 63, "23-05-2025", "Fri", "19:30", "TBD", "TBD", "Kolkata", "Qualifier 2");
        addMatch(78, 65, "25-05-2025", "Sun", "19:30", "TBD", "TBD", "Kolkata", "Final");
    }
    private void addMatch(int matchNumber, int matchDay, String dateStr, String day, String timeStr,
                          String homeTeam, String awayTeam, String venue) {
        addMatch(matchNumber, matchDay, dateStr, day, timeStr, homeTeam, awayTeam, venue, "Regular");
    }

    private void addMatch(int matchNumber, int matchDay, String dateStr, String day, String timeStr,
                          String homeTeam, String awayTeam, String venue, String matchType) {
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));

        Match match = new Match(matchNumber, matchDay, date, day, time, homeTeam, awayTeam, venue, matchType);
        matches.add(match);
    }

    // Get all matches
    @Tool(description = "Get all matches for IPL 2025")
    public List<Match> getAllMatches() {
        return matches;
    }

    // Get matches by team (either home or away)
    @Tool(description = "Get matches by team (either home or away)")
    public List<Match> getMatchesByTeam(String teamName) {
        return matches.stream()
                .filter(match -> match.getHomeTeam().equalsIgnoreCase(teamName) ||
                        match.getAwayTeam().equalsIgnoreCase(teamName))
                .collect(Collectors.toList());
    }

    // Get matches by date
    @Tool(description = "Get matches by date : format yyyy-MM-dd")
    public List<Match> getMatchesByDate(LocalDate date) {
        return matches.stream()
                .filter(match -> match.getDate().equals(date))
                .collect(Collectors.toList());
    }

    // Get matches by date range
    @Tool(description = "Get matches by date range : format yyyy-MM-dd")
    public List<Match> getMatchesByDateRange(LocalDate startDate, LocalDate endDate) {
        return matches.stream()
                .filter(match -> (match.getDate().isEqual(startDate) || match.getDate().isAfter(startDate)) &&
                        (match.getDate().isEqual(endDate) || match.getDate().isBefore(endDate)))
                .collect(Collectors.toList());
    }

    // Get matches by venue
    @Tool(description = "Get matches by venue")
    public List<Match> getMatchesByVenue(String venue) {
        return matches.stream()
                .filter(match -> match.getVenue().equalsIgnoreCase(venue))
                .collect(Collectors.toList());
    }

    // Get match by match number
    @Tool(description = "Get match by match number")
    public Match getMatchByNumber(int matchNumber) {
        return matches.stream()
                .filter(match -> match.getMatchNumber() == matchNumber)
                .findFirst()
                .orElse(null);
    }
}