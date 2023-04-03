import model.entity.EsportGame;
import model.entity.EsportPlayer;
import model.entity.EsportTeam;
import model.type.GameType;
import model.type.PegiType;
import model.value.Image;
import model.value.Tag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class EsportTeamTest {

    @Test
    void should_beTrue_when_teamHaveMinimumTwoMembers() {
        // Given
        EsportGame game = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();

        EsportPlayer player1 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(14))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportPlayer player2 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(15))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportTeam team = EsportTeam.builder()
                .name("Dream team")
                .creationDate("2023/01/01")
                .game(game)
                .members(new HashSet<>(Arrays.asList(player1, player2)))
                .build();

        // When
        boolean result = team.isActive();
        // Then
        assertThat(result).isTrue();
    }

    @Test
    void should_beFalse_when_teamNotHaveTheRequiredMembers() {
        // Given
        EsportGame game = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();

        EsportPlayer player1 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(14))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportTeam team = EsportTeam.builder()
                .name("Dream team")
                .creationDate("2023/01/01")
                .game(game)
                .members(new HashSet<>(Arrays.asList(player1)))
                .build();

        // When
        boolean result = team.isActive();
        // Then
        assertThat(result).isFalse();
    }

    @Test
    void should_beFalse_when_teamHaveAnEmptyMembers() {
        // Given
        EsportGame game = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();

        EsportTeam team = EsportTeam.builder()
                .name("Dream team")
                .creationDate("2023/01/01")
                .game(game)
                .build();

        // When
        boolean result = team.isActive();
        // Then
        assertThat(result).isFalse();
    }

    @Test
    void should_beTrue_when_playerIsMemberInTeam() {
        // Given
        EsportPlayer player1 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(14))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportPlayer player2 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(15))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportTeam team = EsportTeam.builder()
                .name("Dream team")
                .creationDate("2023/01/01")
                .members(new HashSet<>(Arrays.asList(player1, player2)))
                .build();

        // When
        boolean result = team.isMember(player1);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    void should_beFalse_when_playerIsNotMemberInTeam() {
        // Given
        EsportPlayer player1 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(14))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportPlayer player2 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(15))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportPlayer player3 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(16))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportTeam team = EsportTeam.builder()
                .name("Dream team")
                .creationDate("2023/01/01")
                .members(new HashSet<>(Arrays.asList(player1, player2)))
                .build();

        // When
        boolean result = team.isMember(player3);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    void should_beFalse_when_membersIsEmpty() {
        // Given
        EsportPlayer player = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(14))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportTeam team = EsportTeam.builder()
                .name("Dream team")
                .creationDate("2023/01/01")
                .build();

        // When
        boolean result = team.isMember(player);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    void should_beFalse_when_playerIsNull() {
        // Given
        EsportPlayer player1 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(14))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportPlayer player2 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(15))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportPlayer player3 = null;

        EsportTeam team = EsportTeam.builder()
                .name("Dream team")
                .creationDate("2023/01/01")
                .members(new HashSet<>(Arrays.asList(player1, player2)))
                .build();

        // When
        boolean result = team.isMember(player3);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    void should_beTrue_when_teamPlayerAGame() {
        // Given
        EsportPlayer player1 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(14))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportPlayer player2 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(15))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportGame game = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();

        EsportTeam team = EsportTeam.builder()
                .name("Dream team")
                .creationDate("2023/01/01")
                .game(game)
                .members(new HashSet<>(Arrays.asList(player1, player2)))
                .build();

        // When
        boolean result = team.isPlaying(game);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    void should_beFalse_when_teamPlayerAnotherGame() {
        // Given
        EsportPlayer player1 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(14))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportPlayer player2 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(15))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportGame game1 = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();

        EsportGame game2 = EsportGame.builder()
                .title("2en League")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2010, Month.JANUARY, 19))
                .gameType(GameType.CARD_GAME)
                .pegiScore(PegiType.PEGI_18)
                .build();

        EsportTeam team = EsportTeam.builder()
                .name("Dream team")
                .creationDate("2023/01/01")
                .game(game1)
                .members(new HashSet<>(Arrays.asList(player1, player2)))
                .build();

        // When
        boolean result = team.isPlaying(game2);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    void should_beFalse_when_teamGameIsNull() {
        // Given
        EsportPlayer player1 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(14))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportPlayer player2 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(15))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportGame game = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();

        EsportTeam team = EsportTeam.builder()
                .name("Dream team")
                .creationDate("2023/01/01")
                .members(new HashSet<>(Arrays.asList(player1, player2)))
                .build();

        // When
        boolean result = team.isPlaying(game);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    void should_beFalse_when_gameIsNull() {
        // Given
        EsportPlayer player1 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(14))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportPlayer player2 = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(15))
                .email(RandomStringUtils.randomAlphabetic(25))
                .firstName(RandomStringUtils.randomAlphabetic(25))
                .lastName(RandomStringUtils.randomAlphabetic(25))
                .pseudo(RandomStringUtils.randomAlphabetic(25))
                .avatar(Image.builder()
                        .imageId(UUID.randomUUID())
                        .path(RandomStringUtils.randomAlphabetic(25))
                        .build())
                .build();

        EsportGame game1 = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();

        EsportGame game2 = null;

        EsportTeam team = EsportTeam.builder()
                .name("Dream team")
                .creationDate("2023/01/01")
                .game(game1)
                .members(new HashSet<>(Arrays.asList(player1, player2)))
                .build();

        // When
        boolean result = team.isPlaying(game2);
        // Then
        assertThat(result).isFalse();
    }
}
