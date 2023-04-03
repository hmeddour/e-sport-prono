package model.command;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import model.entity.EsportGame;
import model.type.GameType;
import model.type.PegiType;
import model.value.Image;
import model.value.Tag;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Value
@Builder
public class CreateEsportGameAction implements Command<EsportGame> {
    String title;
    Image logo;
    LocalDate publicationDate;
    GameType gameType;
    PegiType pegiScore;
    @Singular
    List<Tag> tags;

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
                title,
                publicationDate,
                gameType,
                pegiScore).noneMatch(Objects::isNull) &&
                publicationDate.isBefore(LocalDate.now());
    }

    @Override
    public Class<EsportGame> resultClass() {
        return EsportGame.class;
    }
}
