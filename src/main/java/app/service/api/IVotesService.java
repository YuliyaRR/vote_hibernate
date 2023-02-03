package app.service.api;

import app.dto.SavedVoiceDTO;
import app.dto.VoiceDTO;

import java.util.List;

public interface IVotesService {

    void save(VoiceDTO voice);

    List<SavedVoiceDTO> get();

}
