package model.command;

import lombok.Builder;
import lombok.Value;
import model.entity.EsportPlayer;
import model.value.Image;

import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Stream;

@Value
@Builder
public class CreateEsportPlayerAction implements Command<EsportPlayer> {
    String lastName;
    String firstName;
    String pseudo;
    Image avatar;
    String email;
    LocalDate dateOfBirth;

    /**
     * A method to check if this action is valid for a game creation.
     * Mandatory fields include :
     *   - the title
     *   - the publication date,
     *   - the game type,
     *   - the PEGI score
     * The publication date must be before the current date
     *
     * @return true if this is a valid action, false otherwise
     */
    public boolean isValid() {
        return Stream.of(
                lastName,
                firstName,
                email,
                pseudo,
                dateOfBirth,
                avatar).noneMatch(Objects::isNull) &&
                dateOfBirth.isBefore(LocalDate.now());
    }

    @Override
    public Class<EsportPlayer> resultClass() {
        return EsportPlayer.class;
    }
}
