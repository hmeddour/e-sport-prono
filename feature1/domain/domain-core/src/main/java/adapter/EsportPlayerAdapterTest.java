package adapter;

import exception.InvalidCreationPlayerInputException;
import exception.InvalidUpdatePlayerInputException;
import exception.NullCommandException;
import mediator.BusinessMediator;
import mediator.Mediator;
import mediator.MediatorProxy;
import model.command.CreateEsportPlayerAction;
import model.command.UpdateEsportPlayerAction;
import model.entity.EsportPlayer;
import model.value.Image;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import port.driven.EsportPlayerPersistencePort;
import usecase.CreatePlayerUseCase;
import usecase.UpdatePlayerUseCase;

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
public class EsportPlayerAdapterTest {
    @Mock
    private EsportPlayerPersistencePort esportPlayerPersistencePort;

    private EsportPlayerAdapter underTest;

    public static Stream<CreateEsportPlayerAction> produceInvalidCreatePlayerAction() {
        return Stream.of(
                null,
                CreateEsportPlayerAction.builder().build(),
                CreateEsportPlayerAction.builder()
                        .email(RandomStringUtils.randomAlphabetic(25))
                        .firstName(RandomStringUtils.randomAlphabetic(25))
                        .lastName(RandomStringUtils.randomAlphabetic(25))
                        .pseudo(RandomStringUtils.randomAlphabetic(25))
                        .avatar(Image.builder()
                                .imageId(UUID.randomUUID())
                                .path(RandomStringUtils.randomAlphabetic(25))
                                .build())
                        .build(),
                CreateEsportPlayerAction.builder()
                        .dateOfBirth(LocalDate.now().minusYears(11))
                        .firstName(RandomStringUtils.randomAlphabetic(25))
                        .lastName(RandomStringUtils.randomAlphabetic(25))
                        .pseudo(RandomStringUtils.randomAlphabetic(25))
                        .avatar(Image.builder()
                                .imageId(UUID.randomUUID())
                                .path(RandomStringUtils.randomAlphabetic(25))
                                .build())
                        .build(),
                CreateEsportPlayerAction.builder()
                        .dateOfBirth(LocalDate.now().minusYears(11))
                        .email(RandomStringUtils.randomAlphabetic(25))
                        .lastName(RandomStringUtils.randomAlphabetic(25))
                        .pseudo(RandomStringUtils.randomAlphabetic(25))
                        .avatar(Image.builder()
                                .imageId(UUID.randomUUID())
                                .path(RandomStringUtils.randomAlphabetic(25))
                                .build())
                        .build(),
                CreateEsportPlayerAction.builder()
                        .dateOfBirth(LocalDate.now().minusYears(11))
                        .email(RandomStringUtils.randomAlphabetic(25))
                        .firstName(RandomStringUtils.randomAlphabetic(25))
                        .pseudo(RandomStringUtils.randomAlphabetic(25))
                        .avatar(Image.builder()
                                .imageId(UUID.randomUUID())
                                .path(RandomStringUtils.randomAlphabetic(25))
                                .build())
                        .build(),
                CreateEsportPlayerAction.builder()
                        .dateOfBirth(LocalDate.now().minusYears(11))
                        .email(RandomStringUtils.randomAlphabetic(25))
                        .firstName(RandomStringUtils.randomAlphabetic(25))
                        .lastName(RandomStringUtils.randomAlphabetic(25))
                        .avatar(Image.builder()
                                .imageId(UUID.randomUUID())
                                .path(RandomStringUtils.randomAlphabetic(25))
                                .build())
                        .build(),
                CreateEsportPlayerAction.builder()
                        .dateOfBirth(LocalDate.now().minusYears(11))
                        .email(RandomStringUtils.randomAlphabetic(25))
                        .firstName(RandomStringUtils.randomAlphabetic(25))
                        .lastName(RandomStringUtils.randomAlphabetic(25))
                        .pseudo(RandomStringUtils.randomAlphabetic(25))
                        .build()
        );
    }

    public static Stream<UpdateEsportPlayerAction> produceInvalidUpdatePlayerAction() {
        return Stream.of(
                null,
                UpdateEsportPlayerAction.builder().build(),
                UpdateEsportPlayerAction.builder()
                        .email(RandomStringUtils.randomAlphabetic(25))
                        .firstName(RandomStringUtils.randomAlphabetic(25))
                        .lastName(RandomStringUtils.randomAlphabetic(25))
                        .pseudo(RandomStringUtils.randomAlphabetic(25))
                        .build(),
                UpdateEsportPlayerAction.builder()
                        .firstName(RandomStringUtils.randomAlphabetic(25))
                        .lastName(RandomStringUtils.randomAlphabetic(25))
                        .avatar(Image.builder()
                                .imageId(UUID.randomUUID())
                                .path(RandomStringUtils.randomAlphabetic(25))
                                .build())
                        .build(),
                UpdateEsportPlayerAction.builder()
                        .dateOfBirth(LocalDate.now().minusYears(11))
                        .email(RandomStringUtils.randomAlphabetic(25))
                        .firstName(RandomStringUtils.randomAlphabetic(25))
                        .lastName(RandomStringUtils.randomAlphabetic(25))
                        .pseudo(RandomStringUtils.randomAlphabetic(25))
                        .build()
        );
    }

    @BeforeEach
    void setup() {
        MediatorProxy mediatorProxy = new MediatorProxy(Collections.emptySet());
        underTest = new EsportPlayerAdapter(mediatorProxy);
        CreatePlayerUseCase create = new CreatePlayerUseCase(esportPlayerPersistencePort);
        UpdatePlayerUseCase update = new UpdatePlayerUseCase(esportPlayerPersistencePort, mediatorProxy);

        Mediator mediator = new BusinessMediator(Set.of(create, update));
        mediatorProxy.setDelegate(mediator);
    }

    @Test
    void should_saveNewPlayer_when_calledWithValidAction() {
        // Given
        when(esportPlayerPersistencePort.save(any(EsportPlayer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        CreateEsportPlayerAction action = CreateEsportPlayerAction.builder()
                .dateOfBirth(LocalDate.now().minusYears(11))
                .email(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportPlayer expected = EsportPlayer.builder()
                .dateOfBirth(action.getDateOfBirth())
                .email(action.getEmail())
                .pseudo(action.getPseudo())
                .firstName(action.getFirstName())
                .lastName(action.getLastName())
                .avatar(action.getAvatar())
                .build();

        // WHEN
        EsportPlayer actual = underTest.createPlayer(action);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("produceInvalidCreatePlayerAction")
    void should_throwInvalidException_when_calledWithInvalidCreateAction(CreateEsportPlayerAction action) {
        assertThatThrownBy(() -> underTest.createPlayer(action))
                .isInstanceOfAny(InvalidCreationPlayerInputException.class, NullCommandException.class)
                .hasMessage(Optional.ofNullable(action)
                        .map(fault ->
                                "Invalid input for player creation : [firstName : %s, lastName : %s, email: %s, pseudo: %s, dateOfBirth: %s]"
                                        .formatted(
                                                fault.getFirstName(),
                                                fault.getLastName(),
                                                fault.getEmail(),
                                                fault.getPseudo(),
                                                fault.getDateOfBirth()))
                        .orElse("The provided input was null")
                );
    }

    @Test
    void should_updatePlayer_when_playerExistsAndActionIsValid() {
        // Given
        EsportPlayer original = EsportPlayer.builder()
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .email(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .dateOfBirth(LocalDate.now().minusYears(15))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();
        when(esportPlayerPersistencePort.findPlayerByPseudo(original.getPseudo())).thenReturn(Optional.of(original));
        when(esportPlayerPersistencePort.save(any(EsportPlayer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        UpdateEsportPlayerAction action = UpdateEsportPlayerAction.builder()
                .pseudo(original.getPseudo())
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .email(RandomStringUtils.randomAlphabetic(25))
                .dateOfBirth(LocalDate.now().minusYears(15))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();
        EsportPlayer expected = EsportPlayer.builder()
                .pseudo(action.getPseudo())
                .dateOfBirth(action.getDateOfBirth())
                .firstName(action.getFirstName())
                .lastName(action.getLastName())
                .email(action.getEmail())
                .avatar(action.getAvatar())
                .build();

        // WHEN
        EsportPlayer actual = underTest.updatePlayer(action);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_createPlayer_when_playerDoesNotExistAndActionIsValid() {
        // Given
        String playerPseudo = RandomStringUtils.randomAlphabetic(25);
        when(esportPlayerPersistencePort.findPlayerByPseudo(playerPseudo)).thenReturn(Optional.empty());
        when(esportPlayerPersistencePort.save(any(EsportPlayer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        UpdateEsportPlayerAction action = UpdateEsportPlayerAction.builder()
                .pseudo(playerPseudo)
                .dateOfBirth(LocalDate.now().minusYears(15))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .email(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();
        EsportPlayer expected = EsportPlayer.builder()
                .pseudo(action.getPseudo())
                .dateOfBirth(action.getDateOfBirth())
                .firstName(action.getFirstName())
                .lastName(action.getLastName())
                .avatar(action.getAvatar())
                .email(action.getEmail())
                .build();
        // WHEN
        EsportPlayer actual = underTest.updatePlayer(action);
        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("produceInvalidUpdatePlayerAction")
    void should_throwInvalidException_when_calledWithInvalidUpdateAction(UpdateEsportPlayerAction action) {
        assertThatThrownBy(() -> underTest.updatePlayer(action))
                .isInstanceOfAny(InvalidUpdatePlayerInputException.class, NullCommandException.class)
                .hasMessage(Optional.ofNullable(action)
                        .map(fault ->
                                "Invalid input for player update : [firstName : %s, lastName : %s, email: %s, pseudo: %s, dateOfBirth: %s]"
                                        .formatted(
                                                fault.getFirstName(),
                                                fault.getLastName(),
                                                fault.getEmail(),
                                                fault.getPseudo(),
                                                fault.getDateOfBirth()))
                        .orElse("The provided input was null")
                );
    }
}
