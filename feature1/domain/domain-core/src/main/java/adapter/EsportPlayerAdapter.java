package adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mediator.BusinessMediator;
import model.command.CreateEsportPlayerAction;
import model.command.UpdateEsportPlayerAction;
import model.entity.EsportPlayer;
import port.driven.EsportPlayerPort;

@Slf4j
@RequiredArgsConstructor
public class EsportPlayerAdapter implements EsportPlayerPort {

    private final BusinessMediator mediator;

    @Override
    public EsportPlayer createPlayer(CreateEsportPlayerAction action) {
        log.info("Creating a new player");
        EsportPlayer created = mediator.execute(action);
        log.info("Created player successfully");
        return created;
    }

    @Override
    public EsportPlayer updatePlayer(UpdateEsportPlayerAction action) {
        log.info("Updating a player");
        EsportPlayer updated = mediator.execute(action);
        log.info("Updated player successfully");
        return updated;
    }
}


