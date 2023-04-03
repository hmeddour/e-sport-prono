package model.entity;

import lombok.Builder;
import lombok.Value;
import model.type.GameType;
import model.type.PegiType;
import model.value.Image;
import model.value.Tag;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Class representing an E-Sport Game
 */
@Value
@Builder(toBuilder = true)
public class EsportGame {
    private final String title;
    private final Image logo;
    private final LocalDate publicationDate;
    private final GameType gameType;
    private final PegiType pegiScore;
    private final List<Tag> tags;

    /**
     * Whether a Game can be played by an EsportPalyer given his or her age
     *
     * @param player the player to check against
     * @return true if the player is the right age to play the game, false otherwise
     */
    public boolean isPlayableBy(EsportPlayer player) {
        LocalDate maximumDateOfBirthToBeAbleToPlay = LocalDate.now().minusYears(pegiScore.getMinimalAge());

        return Optional.ofNullable(player)
                .map(EsportPlayer::getDateOfBirth)
                .filter(dateOfBirth -> dateOfBirth.isBefore(maximumDateOfBirthToBeAbleToPlay))
                .isPresent();
    }
}
