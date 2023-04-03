package port.driven;

import model.command.CreateEsportGameAction;
import model.command.UpdateEsportGameAction;
import model.entity.EsportGame;

/**
 * Driving port for the EsportGame domain
 */
public interface EsportGamePort {

    /**
     * Expose a method to create an Esport Game
     *
     * @param action the creation action
     * @return the created game
     */
    EsportGame createGame(CreateEsportGameAction action);

    /**
     * Expose a method to create an Esport Game
     *
     * @param action the creation action
     * @return the updated game
     */
    EsportGame updateGame(UpdateEsportGameAction action);
}

