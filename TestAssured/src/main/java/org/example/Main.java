package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        String matchPath = "/Users/ankitsharma/Desktop/JAVA_Testing/Ipl project/src/Data/matches.csv";
        String deliveryPath = "/Users/ankitsharma/Desktop/JAVA_Testing/Ipl project/src/Data/deliveries.csv";

        List<Match> matches =  readMatchesFromCSV(matchPath);

        List<Delivery> deliveries = readDeliveriesFromCSV(deliveryPath);

        findMatchesTeamsPlayedPerYear(matches);
        findMatchesWonByTeams(matches);
        int year = 2016;
        findExtraRunsConcededPerTeamIn2016(matches,deliveries,year);
        findTopEconomicalBowlerIn2015(matches,deliveries,2015);

        tossAndMatchWinnerFinder(matches);


    }

    public static List<Match> readMatchesFromCSV(String csvFilePath) {
        List<Match> matches = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");

                Match match = new Match();
                match.setId(Integer.parseInt(fields[0].trim()));
                match.setSeason(Integer.parseInt(fields[1].trim()));
                match.setCity(fields[2].trim());
                match.setDate(fields[3].trim());
                match.setTeam1(fields[4].trim());
                match.setTeam2(fields[5].trim());
                match.setTossWinner(fields[6].trim());
                match.setTossDecision(fields[7].trim());
                match.setResult(fields[8].trim());
                match.setDlApplied(Integer.parseInt(fields[9].trim()));
                match.setWinner(fields[10].trim());
                match.setWinByRuns(Integer.parseInt(fields[11].trim()));
                match.setWinByWickets(Integer.parseInt(fields[12].trim()));
                match.setPlayerOfMatch(fields[13].trim());
                match.setVenue(fields[14].trim());
//                match.setUmpire1(fields[15].trim());
//                match.setUmpire2(fields[16].trim());
//                match.setUmpire3(fields[17].trim());

                matches.add(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matches;
    }

    public static List<Delivery> readDeliveriesFromCSV(String csvFilePath) {
        List<Delivery> deliveries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");

                Delivery delivery = new Delivery();
                delivery.setMatchId(Integer.parseInt(fields[0].trim()));
                delivery.setInning(Integer.parseInt(fields[1].trim()));
                delivery.setBattingTeam(fields[2].trim());
                delivery.setBowlingTeam(fields[3].trim());
                delivery.setOver(Integer.parseInt(fields[4].trim()));
                delivery.setBall(Integer.parseInt(fields[5].trim()));
                delivery.setBatsman(fields[6].trim());
                delivery.setNonStriker(fields[7].trim());
                delivery.setBowler(fields[8].trim());
                delivery.setIsSuperOver(Integer.parseInt(fields[9].trim()));
                delivery.setWideRuns(Integer.parseInt(fields[10].trim()));
                delivery.setByeRuns(Integer.parseInt(fields[11].trim()));
                delivery.setLegByeRuns(Integer.parseInt(fields[12].trim()));
                delivery.setNoBallRuns(Integer.parseInt(fields[13].trim()));
                delivery.setPenaltyRuns(Integer.parseInt(fields[14].trim()));
                delivery.setBatsmanRuns(Integer.parseInt(fields[15].trim()));
                delivery.setExtraRuns(Integer.parseInt(fields[16].trim()));
                delivery.setTotalRuns(Integer.parseInt(fields[17].trim()));
//                delivery.setPlayerDismissed(fields[18].trim());
//                delivery.setDismissalKind(fields[19].trim());
//                delivery.setFielder(fields[20].trim());

                deliveries.add(delivery);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deliveries;
    }

    public static Map<String, Integer> findMatchesTeamsPlayedPerYear(List<Match> matches) {

        Map<Integer, Integer> noOfMatches = new TreeMap<>();

        for(Match match :  matches){
            int matchSeason = match.getSeason();
            int prevMatches = noOfMatches.getOrDefault(matchSeason,0);
            noOfMatches.put(matchSeason,prevMatches+1);
        }

        System.out.println("No of matches played per year:-");

        for (Map.Entry<Integer, Integer> elem : noOfMatches.entrySet()) {
            System.out.println("Season:- " + elem.getKey() + ", No of matches played:- " + elem.getValue());
        }
        return null;
    }

    public static Map<String, Integer> findMatchesWonByTeams(List<Match> matches) {
        Map<String, Integer> matchesWonByTeam = new TreeMap<>();

        for ( Match match : matches) {
            String teamName = match.getWinner();
            int noOfWin = matchesWonByTeam.getOrDefault(teamName, 0);
            matchesWonByTeam.put(teamName, noOfWin+1);

        }

        System.out.println("Matches won by per team is :-");

        for(Map.Entry<String,Integer> elem : matchesWonByTeam.entrySet()){
            System.out.println("Team Name :-" + elem.getKey() + " == Victories by them :-" + elem.getValue());
        }

        return matchesWonByTeam;
    }

    public static Set<Integer>  getMatchYear(List<Match> matches, int year){
        Set<Integer> matchYearId = new HashSet<>();
        for(Match match : matches){
            if(match.getSeason() == year){
                matchYearId.add(match.getId());
            }
        }

        return  matchYearId;
    }
    public static Map<String, Integer> findExtraRunsConcededPerTeamIn2016(List<Match> matches, List<Delivery> deliveries, int year) {
        Set<Integer> matchId = getMatchYear(matches,year);
        Map<String,Integer> extraRunByTeams = new TreeMap<>();

        for(Delivery delivery : deliveries){
            int id = delivery.getMatchId();
            if(matchId.contains(id)){
                int extraRuns = delivery.getExtraRuns();
                String teamName = delivery.getBowlingTeam();
                int presentRun = extraRunByTeams.getOrDefault(teamName,0);
                extraRunByTeams.put(teamName,presentRun + extraRuns);
            }
        }

        System.out.println("Extra run conceeded by team at the year of " + year);

        for(Map.Entry<String,Integer> elem : extraRunByTeams.entrySet() ){
            System.out.println("Team Name :-" + elem.getKey()  + ":-: Extra run conceede by them is " + elem.getValue());
        }

        return extraRunByTeams;
    }

    public static Map<String, Integer> findTopEconomicalBowlerIn2015(List<Match> matches, List<Delivery> deliveries, int year) {
        Set<Integer> matchYearId = getMatchYear(matches, year);
        Map<String, Integer> bowlerRuns = new HashMap<>();
        Map<String, Integer> bowlerBalls = new HashMap<>();
        Map<String, Float> bowlersEconomy = new HashMap<>();

        float lowestEconomy = Float.MAX_VALUE;

        for (Delivery delivery : deliveries) {
            int id = delivery.getMatchId();

            if (matchYearId.contains(id)) {
                String bowler = delivery.getBowler();

                int currRuns = delivery.getTotalRuns();
                int prevRuns = bowlerRuns.getOrDefault(bowler, 0);
                int prevBalls = bowlerBalls.getOrDefault(bowler, 0);

                bowlerRuns.put(bowler, prevRuns + currRuns);
                bowlerBalls.put(bowler, prevBalls + 1);
            }
        }

        bowlerRuns.forEach((bowler, runs) -> {
            int balls = bowlerBalls.get(bowler);
            int overs = balls / 6;
            float economy = ((float) runs / (float) overs);

            bowlersEconomy.put(bowler, economy);
        });

        List<Map.Entry<String, Float>> sortEconomyList = new ArrayList<>(bowlersEconomy.entrySet());
        sortEconomyList.sort(Map.Entry.comparingByValue());
        Map<String, Float> sortedBowlersEconomy = new LinkedHashMap<>();

        for (Map.Entry<String, Float> bowlerEconomy : sortEconomyList) {
            sortedBowlersEconomy.put(bowlerEconomy.getKey(), bowlerEconomy.getValue());
        }


        for(Map.Entry<String,Float> bowlerEconomy: sortedBowlersEconomy.entrySet()){
            if(lowestEconomy == Float.MAX_VALUE || lowestEconomy == bowlerEconomy.getValue()) {
                System.out.println("Bowler:- " + bowlerEconomy.getKey() + ", Economy:- " + bowlerEconomy.getValue());

                lowestEconomy = bowlerEconomy.getValue();
            }
            else{
                break;
            }
        }

        return bowlerRuns;
    }

//      // Display the venue where the team that
//      won toss also won the  match

    public static void tossAndMatchWinnerFinder (List<Match> matches) {

        Map<String,Integer> VenueTossAndMatchWinner = new HashMap<>();

        for(Match match : matches){
            String teamName = match.getWinner();
            String tossWinner  = match.getTossWinner();
            String Venue = match.getVenue();

            if(teamName.equals(tossWinner)){

                int prevRecord = VenueTossAndMatchWinner.getOrDefault(Venue,0);
                VenueTossAndMatchWinner.put(Venue,prevRecord+1);

            }

        }

        List <Map.Entry<String,Integer>> sortedVenueByValue = new ArrayList<>(VenueTossAndMatchWinner.entrySet());
        sortedVenueByValue.sort(Map.Entry.comparingByValue());




//        System.out.println("Winner at the Venue and winner of the toss is :-");
//
//        for(Map.Entry<String,Integer> eleme : VenueTossAndMatchWinner.entrySet()){
//            System.out.println("Stadium name :-" + eleme.getKey() + " :- Times it happend is " + eleme.getValue());
//        }

        int maxValue = Integer.MIN_VALUE;
        String stadiumName = null;

        for(Map.Entry<String,Integer> elem : VenueTossAndMatchWinner.entrySet()){
            if(maxValue < elem.getValue()){
                maxValue = elem.getValue();
                stadiumName = elem.getKey();
            }
        }

        System.out.println("Stadium name :" + stadiumName + " :- No. of time it happend :" + maxValue );


    }

}








