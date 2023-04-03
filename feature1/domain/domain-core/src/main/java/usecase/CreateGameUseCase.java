package usecase;

import exception.InvalidCreationGameInputException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.command.Command;
import model.command.CommandHandler;
import model.command.CreateEsportGameAction;
import model.entity.EsportGame;
import port.driven.EsportGamePersistencePort;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class CreateGameUseCase implements CommandHandler<EsportGame,CreateEsportGameAction> {

    private final EsportGamePersistencePort persistencePort;

    @Override
    public EsportGame handle(CreateEsportGameAction action) {
        if (Objects.isNull(action) || !action.isValid()) {
            log.info("The input for the game creation is invalid");
            throw new InvalidCreationGameInputException(action);
        }
        EsportGame createdGame = EsportGame.builder()
                .title(action.getTitle())
                .publicationDate(action.getPublicationDate())
                .logo(action.getLogo())
                .gameType(action.getGameType())
                .pegiScore(action.getPegiScore())
                .tags(action.getTags())
                .build();
        return persistencePort.save(createdGame);
    }

    @Override
    public boolean canHandle(Command command) {
        return CreateEsportGameAction.class
                .equals(command.getClass());
    }
}
