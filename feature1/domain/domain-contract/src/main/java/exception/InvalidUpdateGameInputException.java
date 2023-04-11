package exception;

import model.command.UpdateEsportGameAction;

public class InvalidUpdateGameInputException extends RuntimeException {

    private UpdateEsportGameAction esportGameAction;
    public InvalidUpdateGameInputException(UpdateEsportGameAction action) {
        this.esportGameAction = action;
    }

    @Override
    public String getMessage() {
        return String.format("Invalid input for game update : [title : %s, publication date: %s, type: %s, PEGI score: %s]",
                esportGameAction.getTitle(), esportGameAction.getPublicationDate(), esportGameAction.getGameType(), esportGameAction.getPegiScore());
    }
}
