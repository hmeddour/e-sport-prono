package model.entity;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Class representing an e-sport players team
 */
@Value
@Builder
public class EsportTeam {
    String name;
    String creationDate;
    @Singular
    Set<EsportPlayer> members;
    EsportGame game;

    public boolean isActive() {
        return members.size() >= 2;
    }
    public boolean isPlaying(EsportGame game) {
        return Objects.isNull(this.game) ? false : this.game.equals(game);
    }

    public boolean isMember(EsportPlayer player) {
        return members.contains(player);
    }
}
