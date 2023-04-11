import model.entity.EsportGame;
import model.entity.EsportPlayer;
import model.type.GameType;
import model.type.PegiType;
import model.value.Image;
import model.value.Tag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class EsportGameTest {
    @Test
    void should_beTrue_when_gameIsPlayableByPlayer() {
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
        boolean result = game.isPlayableBy(player);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    void should_beFalse_when_playerIsUnderMinimumAge() {
        // Given
        EsportGame game = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();
        EsportPlayer player = EsportPlayer.builder()
                .dateOfBirth(LocalDate.now().minusYears(11))
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
        boolean result = game.isPlayableBy(player);
        // Then
        Assertions.assertFalse(result);
    }

    @Test
    void should_beFalse_when_playerIsNull() {
        // Given
        EsportGame game = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();
        EsportPlayer player = null;
        // When
        boolean result = game.isPlayableBy(player);
        // Then
        Assertions.assertFalse(result);
    }

    @Test
    void should_beFalse_when_playerDateOfBirthIsNull() {
        // Given
        EsportGame game = EsportGame.builder()
                .title("League of Legends")
                .tags(Arrays.asList(Tag.builder().key("type").label("Best Game ever").build()))
                .publicationDate(LocalDate.of(2009, Month.OCTOBER, 27))
                .gameType(GameType.MOBA)
                .pegiScore(PegiType.PEGI_12)
                .build();
        EsportPlayer player = EsportPlayer.builder()
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
        boolean result = game.isPlayableBy(player);
        // Then
        Assertions.assertFalse(result);
    }
}
