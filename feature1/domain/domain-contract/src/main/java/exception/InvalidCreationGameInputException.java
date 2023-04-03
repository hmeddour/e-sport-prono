package exception;

import model.command.CreateEsportGameAction;

public class InvalidCreationGameInputException extends RuntimeException{

    private CreateEsportGameAction esportGameAction;

    public InvalidCreationGameInputException(CreateEsportGameAction action) {
        this.esportGameAction = action;
    }

    @Override
    public String getMessage() {
        return String.format("Invalid input for game creation : [title : %s, publication date: %s, type: %s, PEGI score: %s]",
                esportGameAction.getTitle(), esportGameAction.getPublicationDate(), esportGameAction.getGameType(), esportGameAction.getPegiScore());
    }
}
