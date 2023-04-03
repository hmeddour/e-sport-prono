package usecase;

import exception.InvalidCreationPlayerInputException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.command.Command;
import model.command.CommandHandler;
import model.command.CreateEsportPlayerAction;
import model.entity.EsportPlayer;
import port.driven.EsportPlayerPersistencePort;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class CreatePlayerUseCase implements CommandHandler<EsportPlayer, CreateEsportPlayerAction> {

    private final EsportPlayerPersistencePort persistencePort;

    @Override
    public EsportPlayer handle(CreateEsportPlayerAction action) {
        if (Objects.isNull(action) || !action.isValid()) {
            log.info("The input for the game creation is invalid");
            throw new InvalidCreationPlayerInputException(action);
        }
        EsportPlayer createdPlayer = EsportPlayer.builder()
                .firstName(action.getFirstName())
                .lastName(action.getLastName())
                .email(action.getEmail())
                .pseudo(action.getPseudo())
                .dateOfBirth(action.getDateOfBirth())
                .avatar(action.getAvatar())
                .build();
        return persistencePort.save(createdPlayer);
    }

    @Override
    public boolean canHandle(Command command) {
        return CreateEsportPlayerAction.class
                .equals(command.getClass());
    }
}
