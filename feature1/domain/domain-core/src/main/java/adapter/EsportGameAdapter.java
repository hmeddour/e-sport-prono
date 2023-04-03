package adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mediator.BusinessMediator;
import model.command.CreateEsportGameAction;
import model.command.UpdateEsportGameAction;
import model.entity.EsportGame;
import port.driven.EsportGamePort;

@Slf4j
@RequiredArgsConstructor
public class EsportGameAdapter implements EsportGamePort {

    private final BusinessMediator mediator;

    @Override
    public EsportGame createGame(CreateEsportGameAction action) {
        log.info("Creating a new game");
        EsportGame created = mediator.execute(action);
        log.info("Created game successfully");
        return created;
    }

    @Override
    public EsportGame updateGame(UpdateEsportGameAction action) {
        log.info("Updating a game");
        EsportGame updated = mediator.execute(action);
        log.info("Updated game successfully");
        return updated;
    }
}


