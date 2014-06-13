package com.excilys.testapp.android.service.message;



import java.util.List;
import com.excilys.testapp.android.model.message.Message;

import java.util.List;

/**
 * Created by excilys on 13/06/14.
 */
public interface MessageService {

    public void create(Message message);

    public List<Message> retrieve();

    public void update(Message message);

    public void delete(String id);

    /*public Fight retrieveById(String id);

    public List<Fight> retrieveByTournamentIdAndTeamsId(String tournamentId, String team1Id, String team2Id);

    public List<Long> retrieveScores(String tournamentId, String teamId1, String teamId2);

    public Fight retrieve(String judoka1Id, String judoka2Id, String tournamentId);*/
}

