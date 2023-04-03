import model.entity.EsportGame;
import model.entity.EsportPlayer;
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

public class EsportPlayerTest {

    @Test
    void should_beTrue_when_gameIsPlayingByPlayer() {
        // Given
        EsportGame game1 = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();

        EsportGame game2 = EsportGame.builder()
                .title("2nd League")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.CARD_GAME)
                .pegiScore(PegiType.PEGI_16)
                .build();

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
                .playedGames(new HashSet<>(Arrays.asList(game1, game2)))
                .build();

        // When
        boolean result = player.isPlaying(game1);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    void should_beFalse_when_gameIsNotPlayingByPlayer() {
        // Given
        EsportGame game1 = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();

        EsportGame game2 = EsportGame.builder()
                .title("2nd League")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.CARD_GAME)
                .pegiScore(PegiType.PEGI_16)
                .build();

        EsportGame game3 = EsportGame.builder()
                .title("3rd League")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.STRATEGY_GAME)
                .pegiScore(PegiType.PEGI_18)
                .build();

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
                .playedGames(new HashSet<>(Arrays.asList(game1, game2)))
                .build();

        // When
        boolean result = player.isPlaying(game3);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    void should_beFalse_when_gameIsNull() {
        // Given
        EsportGame game1 = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();

        EsportGame game2 = EsportGame.builder()
                .title("2nd League")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.CARD_GAME)
                .pegiScore(PegiType.PEGI_16)
                .build();

        EsportGame game3 = null;

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
                .playedGames(new HashSet<>(Arrays.asList(game1, game2)))
                .build();

        // When
        boolean result = player.isPlaying(game3);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    void should_beFalse_when_playedGamesIsEmpty() {
        // Given
        EsportGame game = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();

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

        // When
        boolean result = player.isPlaying(game);
        // Then
        assertThat(result).isFalse();
    }
}
