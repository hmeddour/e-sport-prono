package model.entity;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import model.value.Image;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * Class representing an e-sport player
 */
@Value
@Builder(toBuilder = true)
public class EsportPlayer {
    String lastName;
    String firstName;
    String pseudo;
    Image avatar;
    String email;
    LocalDate dateOfBirth;
    @Singular
    Set<EsportGame> playedGames;

    public boolean isPlaying(EsportGame game) {
        if(Objects.isNull(game)) {
            return false;
        }

        return playedGames.contains(game);
    }
}
