package port.driven;

import model.command.CreateEsportPlayerAction;
import model.command.UpdateEsportPlayerAction;
import model.entity.EsportPlayer;

/**
 * Driving port for the EsportGame domain
 */
public interface EsportPlayerPort {

    /**
     * Expose a method to create an Esport Player
     *
     * @param action the creation action
     * @return the created player
     */
    EsportPlayer createPlayer(CreateEsportPlayerAction action);

    /**
     * Expose a method to create an Esport Player
     *
     * @param action the creation action
     * @return the updated player
     */
    EsportPlayer updatePlayer(UpdateEsportPlayerAction action);
}

