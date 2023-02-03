package app.dao.api;

import app.entity.VoiceEntity;

import java.util.List;

public interface IVotingDao {

    List<VoiceEntity> getVoiceList();


    void save(VoiceEntity voice);
}
