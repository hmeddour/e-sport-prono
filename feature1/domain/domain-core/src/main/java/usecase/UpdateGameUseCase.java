package usecase;

import exception.InvalidUpdateGameInputException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mediator.Mediator;
import model.command.Command;
import model.command.CommandHandler;
import model.command.CreateEsportGameAction;
import model.command.UpdateEsportGameAction;
import model.entity.EsportGame;
import port.driven.EsportGamePersistencePort;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
public class UpdateGameUseCase implements CommandHandler<EsportGame, UpdateEsportGameAction> {

    private final EsportGamePersistencePort persistencePort;
    private final Mediator mediator;

    @Override
    public EsportGame handle(UpdateEsportGameAction action) {
        if (Objects.isNull(action) || !action.isValid()) {
            throw new InvalidUpdateGameInputException(action);
        }

        Optional<EsportGame> esportGameCandidate = persistencePort.findGameByTitle(action.getTitle());
        if (esportGameCandidate.isEmpty()) {
            return mediator.execute(CreateEsportGameAction.builder()
                    .title(action.getTitle())
                    .publicationDate(action.getPublicationDate())
                    .logo(action.getLogo())
                    .gameType(action.getGameType())
                    .pegiScore(action.getPegiScore())
                    .tags(action.getTags())
                    .build());
        }
        EsportGame toUpdate = mapGameWithUpdate(esportGameCandidate.get(), action);

        return persistencePort.save(toUpdate);
    }

    @Override
    public <C extends Command<?>> boolean canHandle(C command) {
        return UpdateEsportGameAction.class
                .equals(command.getClass());
    }

    private static EsportGame mapGameWithUpdate(EsportGame esportGame, UpdateEsportGameAction action) {
        EsportGame.EsportGameBuilder builder = esportGame.toBuilder();
        mapGameProperty(action::getPublicationDate, builder::publicationDate);
        mapGameProperty(action::getLogo, builder::logo);
        mapGameProperty(action::getGameType, builder::gameType);
        mapGameProperty(action::getPegiScore, builder::pegiScore);
        mapGameProperty(action::getTags, builder::tags);
        return builder.build();
    }

    private static <T> void mapGameProperty(Supplier<T> propertyGetter, Function<T, EsportGame.EsportGameBuilder> builderMethod) {
        if (Objects.nonNull(propertyGetter.get())) {
            builderMethod.apply(propertyGetter.get());
        }
    }
}