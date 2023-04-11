package usecase;

import exception.InvalidUpdatePlayerInputException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mediator.Mediator;
import model.command.*;
import model.entity.EsportPlayer;
import port.driven.EsportPlayerPersistencePort;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
public class UpdatePlayerUseCase implements CommandHandler<EsportPlayer, UpdateEsportPlayerAction> {

    private final EsportPlayerPersistencePort persistencePort;
    private final Mediator mediator;

    @Override
    public EsportPlayer handle(UpdateEsportPlayerAction action) {
        if (Objects.isNull(action) || !action.isValid()) {
            throw new InvalidUpdatePlayerInputException(action);
        }

        Optional<EsportPlayer> esportPlayerCandidate = persistencePort.findPlayerByPseudo(action.getPseudo());
        if (esportPlayerCandidate.isEmpty()) {
            return mediator.execute(CreateEsportPlayerAction.builder()
                    .firstName(action.getFirstName())
                    .lastName(action.getLastName())
                    .email(action.getEmail())
                    .pseudo(action.getPseudo())
                    .dateOfBirth(action.getDateOfBirth())
                    .avatar(action.getAvatar())
                    .build());
        }
        EsportPlayer toUpdate = mapPlayerWithUpdate(esportPlayerCandidate.get(), action);

        return persistencePort.save(toUpdate);
    }

    @Override
    public <C extends Command<?>> boolean canHandle(C command) {
        return UpdateEsportPlayerAction.class
                .equals(command.getClass());
    }

    private static EsportPlayer mapPlayerWithUpdate(EsportPlayer esportPlayer, UpdateEsportPlayerAction action) {
        EsportPlayer.EsportPlayerBuilder builder = esportPlayer.toBuilder();
        mapPlayerProperty(action::getFirstName, builder::firstName);
        mapPlayerProperty(action::getLastName, builder::lastName);
        mapPlayerProperty(action::getEmail, builder::email);
        mapPlayerProperty(action::getPseudo, builder::pseudo);
        mapPlayerProperty(action::getDateOfBirth, builder::dateOfBirth);
        mapPlayerProperty(action::getAvatar, builder::avatar);
        return builder.build();
    }

    private static <T> void mapPlayerProperty(Supplier<T> propertyGetter, Function<T, EsportPlayer.EsportPlayerBuilder> builderMethod) {
        if (Objects.nonNull(propertyGetter.get())) {
            builderMethod.apply(propertyGetter.get());
        }
    }
}