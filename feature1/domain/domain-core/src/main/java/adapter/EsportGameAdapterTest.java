package adapter;

import exception.InvalidCreationGameInputException;
import exception.InvalidUpdateGameInputException;
import exception.NullCommandException;
import mediator.BusinessMediator;
import mediator.Mediator;
import mediator.MediatorProxy;
import model.command.CreateEsportGameAction;
import model.command.UpdateEsportGameAction;
import model.entity.EsportGame;
import model.type.GameType;
import model.type.PegiType;
import model.value.Image;
import model.value.Tag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import port.driven.EsportGamePersistencePort;
import usecase.CreateGameUseCase;
import usecase.UpdateGameUseCase;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EsportGameAdapterTest {
    @Mock
    private EsportGamePersistencePort esportGamePersistencePort;

    private EsportGameAdapter underTest;

    public static Stream<CreateEsportGameAction> produceInvalidCreateAction() {
        return Stream.of(
                null,
                CreateEsportGameAction.builder().build(),
                CreateEsportGameAction.builder()
                        .gameType(GameType.MOBA)
                        .pegiScore(PegiType.PEGI_18)
                        .publicationDate(LocalDate.now().minusYears(5))
                        .build(),
                CreateEsportGameAction.builder()
                        .title(RandomStringUtils.randomAlphabetic(25))
                        .pegiScore(PegiType.PEGI_18)
                        .publicationDate(LocalDate.now().minusYears(5))
                        .build(),
                CreateEsportGameAction.builder()
                        .title(RandomStringUtils.randomAlphabetic(25))
                        .gameType(GameType.MOBA)
                        .publicationDate(LocalDate.now().minusYears(5))
                        .build(),
                CreateEsportGameAction.builder()
                        .title(RandomStringUtils.randomAlphabetic(25))
                        .gameType(GameType.MOBA)
                        .pegiScore(PegiType.PEGI_18)
                        .build(),
                CreateEsportGameAction.builder()
                        .title(RandomStringUtils.randomAlphabetic(25))
                        .gameType(GameType.MOBA)
                        .pegiScore(PegiType.PEGI_18)
                        .publicationDate(LocalDate.now())
                        .build(),
                CreateEsportGameAction.builder()
                        .title(RandomStringUtils.randomAlphabetic(25))
                        .gameType(GameType.MOBA)
                        .pegiScore(PegiType.PEGI_18)
                        .publicationDate(LocalDate.now().plusYears(1))
                        .build()
        );
    }

    public static Stream<UpdateEsportGameAction> produceInvalidUpdateAction() {
        return Stream.of(
                null,
                UpdateEsportGameAction.builder().build(),
                UpdateEsportGameAction.builder()
                        .gameType(GameType.MOBA)
                        .pegiScore(PegiType.PEGI_18)
                        .publicationDate(LocalDate.now().minusYears(5))
                        .build(),
                UpdateEsportGameAction.builder()
                        .title(RandomStringUtils.randomAlphabetic(25))
                        .gameType(GameType.MOBA)
                        .pegiScore(PegiType.PEGI_18)
                        .publicationDate(LocalDate.now())
                        .build(),
                UpdateEsportGameAction.builder()
                        .title(RandomStringUtils.randomAlphabetic(25))
                        .gameType(GameType.MOBA)
                        .pegiScore(PegiType.PEGI_18)
                        .publicationDate(LocalDate.now().plusYears(1))
                        .build()
        );
    }

    @BeforeEach
    void setup() {
        MediatorProxy mediatorProxy = new MediatorProxy(Collections.emptySet());
        underTest = new EsportGameAdapter(mediatorProxy);
        CreateGameUseCase create = new CreateGameUseCase(esportGamePersistencePort);
        UpdateGameUseCase update = new UpdateGameUseCase(esportGamePersistencePort, mediatorProxy);

        Mediator mediator = new BusinessMediator(Set.of(create, update));
        mediatorProxy.setDelegate(mediator);
    }

    @Test
    void should_saveNewGame_when_calledWithValidAction() {
        // Given
        when(esportGamePersistencePort.save(any(EsportGame.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        CreateEsportGameAction action = CreateEsportGameAction.builder()
                .title(RandomStringUtils.randomAlphabetic(25))
                .publicationDate(LocalDate.now().minusYears(14))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_18)
                .build();

        EsportGame expected = EsportGame.builder()
                .title(action.getTitle())
                .publicationDate(action.getPublicationDate())
                .gameType(action.getGameType())
                .pegiScore(action.getPegiScore())
                .build();

        // WHEN
        EsportGame actual = underTest.createGame(action);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("produceInvalidCreateAction")
    void should_throwInvalidException_when_calledWithInvalidCreateAction(CreateEsportGameAction action) {
        assertThatThrownBy(() -> underTest.createGame(action))
                .isInstanceOfAny(InvalidCreationGameInputException.class, NullCommandException.class)
                .hasMessage(Optional.ofNullable(action)
                        .map(fault ->
                                "Invalid input for game creation : [title : %s, publication date: %s, type: %s, PEGI score: %s]"
                                        .formatted(
                                                fault.getTitle(),
                                                fault.getPublicationDate(),
                                                fault.getGameType(),
                                                fault.getPegiScore()))
                        .orElse("The provided input was null")
                );
    }

    @Test
    void should_updateGame_when_gameExistsAndActionIsValid() {
        // Given
        EsportGame original = EsportGame.builder()
                .title(RandomStringUtils.randomAlphabetic(25))
                .publicationDate(LocalDate.now().minusYears(14))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_18)
                .build();
        when(esportGamePersistencePort.findGameByTitle(original.getTitle())).thenReturn(Optional.of(original));
        when(esportGamePersistencePort.save(any(EsportGame.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        UpdateEsportGameAction action = UpdateEsportGameAction.builder()
                .title(original.getTitle())
                .publicationDate(LocalDate.now().minusYears(10))
                .gameType(GameType.FPS)
                .pegiScore(PegiType.PEGI_16)
                .logo(Image.builder().imageId(UUID.randomUUID()).build())
                .tag(Tag.builder().key("multi").label("Multiplayer").build())
                .build();
        EsportGame expected = EsportGame.builder()
                .title(action.getTitle())
                .publicationDate(action.getPublicationDate())
                .gameType(action.getGameType())
                .pegiScore(action.getPegiScore())
                .logo(action.getLogo())
                .tags(action.getTags())
                .build();

        // WHEN
        EsportGame actual = underTest.updateGame(action);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_createGame_when_gameDoesNotExistAndActionIsValid() {
        // Given
        String gameTitle = RandomStringUtils.randomAlphabetic(25);
        when(esportGamePersistencePort.findGameByTitle(gameTitle)).thenReturn(Optional.empty());
        when(esportGamePersistencePort.save(any(EsportGame.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        UpdateEsportGameAction action = UpdateEsportGameAction.builder()
                .title(gameTitle)
                .publicationDate(LocalDate.now().minusYears(10))
                .gameType(GameType.FPS)
                .pegiScore(PegiType.PEGI_16)
                .logo(Image.builder().imageId(UUID.randomUUID()).build())
                .tag(Tag.builder().key("multi").label("Multiplayer").build())
                .build();
        EsportGame expected = EsportGame.builder()
                .title(action.getTitle())
                .publicationDate(action.getPublicationDate())
                .gameType(action.getGameType())
                .pegiScore(action.getPegiScore())
                .logo(action.getLogo())
                .tags(action.getTags())
                .build();
        // WHEN
        EsportGame actual = underTest.updateGame(action);
        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("produceInvalidUpdateAction")
    void should_throwInvalidException_when_calledWithInvalidUpdateAction(UpdateEsportGameAction action) {
        assertThatThrownBy(() -> underTest.updateGame(action))
                .isInstanceOfAny(InvalidUpdateGameInputException.class, NullCommandException.class)
                .hasMessage(Optional.ofNullable(action)
                        .map(fault ->
                                "Invalid input for game update : [title : %s, publication date: %s, type: %s, PEGI score: %s]"
                                        .formatted(
                                                fault.getTitle(),
                                                fault.getPublicationDate(),
                                                fault.getGameType(),
                                                fault.getPegiScore()))
                        .orElse("The provided input was null")
                );
    }

}
