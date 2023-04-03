package port.driven;

import model.entity.EsportPlayer;

import java.util.Optional;

/**
 * Persistence port for EsportPlayer domain
 */
public interface EsportPlayerPersistencePort {

    /**
     * Persist a game using the underlying persistence mechanism
     *
     * @param player the player to persist
     * @return the persisted player
     */
    EsportPlayer save(EsportPlayer player);

    /**
     * find a game by pseudo
     *
     * @param pseudo of the game
     * @return retrieved player
     */
    Optional<EsportPlayer> findPlayerByPseudo(String pseudo);
}

