import org.junit.jupiter.api.Test;


import java.util.*;

import org.example.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_01 {

    @Test
    public void testFindNumberOfMatchesPlayedPerYear() {
        ArrayList<Match> list= new ArrayList<>();
        Match match1= new Match();
        match1.setSeason(2015);

        Match match2= new Match();
        match2.setSeason(2016);

        Match match3= new Match();
        match3.setSeason(2017);

        list.add(match1);
        list.add(match1);
        list.add(match1);

        list.add(match2);
        list.add(match2);
        list.add(match2);

        list.add(match3);
        list.add(match3);
        list.add(match3);
        list.add(match3);
        list.add(match3);

        HashMap<Integer,Integer> result= new HashMap<>();
        result.put(2015,3);
        result.put(2016,3);
        result.put(2017,5);


        assertEquals(result,Main.findMatchesTeamsPlayedPerYear(list),"errror in first method");

    }

    @Test
    public void testFindNumberOfMatchesWonByEachTeam() {

        Match match1= new Match();
        match1.setWinner("Royal Challengers Bangalore");

        Match match2= new Match();
        match2.setWinner("Royal Challengers Bangalore");

        Match match3= new Match();
        match3.setWinner("Mumbai Indians");

        ArrayList<Match> matches= new ArrayList<>();
        matches.add(match1);
        matches.add(match2);
        matches.add(match3);

        HashMap<String,Integer> expected= new HashMap<>();
        expected.put("Mumbai Indians",1);
        expected.put("Royal Challengers Bangalore",2);

        assertEquals(expected,Main.findMatchesWonByTeams(matches));


    }

    @Test
    public void testFindExtraRunsConcededPerTeamIn2016() {
        List<Match> matches = getTestMatchesData();
        List<Delivery> deliveries = getTestDeliveriesData();

        Map<String, Integer> actualResult = Main.findExtraRunsConcededPerTeamIn2016(matches, deliveries,2016);

        assertEquals(2, actualResult.get("Gujarat Lions"));
   }

    @Test
    public void testFindMostEconomicalBowlersIn2015() {
        List<Match> matches = getTestMatchesData();
        List<Delivery> deliveries = getTestDeliveriesData();

        Map<String, Integer> actualResult = Main.findTopEconomicalBowlerIn2015(matches, deliveries, 2015);

        assertEquals(15.00, actualResult.get("YS Chahal"), 0.01);
    }

    

    private List<Match> getTestMatchesData() {
        Match match1 = new Match();
        match1.setSeason(2015);
        match1.setId(1);
        match1.setWinner("Mumbai Indians");
        match1.setVenue("Eden Gardens");

        Match match2 = new Match();
        match2.setSeason(2015);
        match2.setId(2);
        match2.setWinner("Mumbai Indians");
        match2.setVenue("Eden Gardens");

        Match match3 = new Match();
        match3.setSeason(2016);
        match3.setId(4);
        match3.setWinner("Gujarat Lions");
        match3.setVenue("Eden Gardens");

        return Arrays.asList(match1, match2, match3);
    }


    private List<Delivery> getTestDeliveriesData() {
        Delivery delivery1 = new Delivery();
        delivery1.setMatchId(1);
        delivery1.setBowler("YS Chahal");
        delivery1.setExtraRuns(0);
        delivery1.setTotalRuns(6);
        delivery1.setLegByeRuns(0);
        delivery1.setByeRuns(0);
        delivery1.setPenaltyRuns(0);
        delivery1.setBowlingTeam("Royal Challenger Bangalore");

        Delivery delivery2 = new Delivery();
        delivery2.setMatchId(1);
        delivery2.setBowler("YS Chahal");
        delivery2.setTotalRuns(0);
        delivery2.setExtraRuns(0);
        delivery2.setLegByeRuns(0);
        delivery2.setByeRuns(0);
        delivery2.setPenaltyRuns(0);
        delivery2.setBowlingTeam("Royal Challenger Banglore");


        Delivery delivery3 = new Delivery();
        delivery3.setMatchId(2);
        delivery3.setBowler("YS Chahal");
        delivery3.setExtraRuns(0);
        delivery3.setTotalRuns(4);
        delivery3.setLegByeRuns(0);
        delivery3.setByeRuns(0);
        delivery3.setPenaltyRuns(0);
        delivery3.setBowlingTeam("Royal Challenger Banglore");


        Delivery delivery4 = new Delivery();
        delivery4.setMatchId(2);
        delivery4.setBowler("YS Chahal");
        delivery4.setExtraRuns(0);
        delivery4.setTotalRuns(2);
        delivery4.setLegByeRuns(0);
        delivery4.setByeRuns(0);
        delivery4.setPenaltyRuns(0);
        delivery4.setBowlingTeam("Royal Challenger Banglore");


        Delivery delivery5 = new Delivery();
        delivery5.setMatchId(2);
        delivery5.setBowler("YS Chahal");
        delivery5.setExtraRuns(0);
        delivery5.setTotalRuns(1);
        delivery5.setLegByeRuns(0);
        delivery5.setByeRuns(0);
        delivery5.setPenaltyRuns(0);
        delivery5.setBowlingTeam("Royal Challenger Banglore");


        Delivery delivery6 = new Delivery();
        delivery6.setMatchId(2);
        delivery6.setBowler("YS Chahal");
        delivery6.setExtraRuns(0);
        delivery6.setTotalRuns(2);
        delivery6.setLegByeRuns(0);
        delivery6.setByeRuns(0);
        delivery6.setPenaltyRuns(0);
        delivery6.setBowlingTeam("Royal Challenger Banglore");


        Delivery delivery7 = new Delivery();
        delivery7.setMatchId(4);
        delivery7.setBowler("VK Kohli");
        delivery7.setExtraRuns(2);
        delivery7.setTotalRuns(1);
        delivery7.setLegByeRuns(0);
        delivery7.setByeRuns(0);
        delivery7.setPenaltyRuns(0);
        delivery7.setBowlingTeam("Gujarat Lions");




        return Arrays.asList(delivery1,delivery2,delivery3,delivery4,delivery5,delivery6,delivery7);
    }

}
