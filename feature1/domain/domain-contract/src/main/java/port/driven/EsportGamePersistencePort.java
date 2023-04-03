package port.driven;

import model.entity.EsportGame;

import java.util.Optional;

/**
 * Persistence port for EsportGame domain
 */
public interface EsportGamePersistencePort {

    /**
     * Persist a game using the underlying persistence mechanism
     *
     * @param game the game to persist
     * @return the persisted game
     */
    EsportGame save(EsportGame game);

    /**
     * find a game by title
     *
     * @param title of the game
     * @return retrieved game
     */
    Optional<EsportGame> findGameByTitle(String title);
}

