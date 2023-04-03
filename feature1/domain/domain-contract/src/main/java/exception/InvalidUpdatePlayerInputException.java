package exception;

import model.command.UpdateEsportPlayerAction;

public class InvalidUpdatePlayerInputException extends RuntimeException{

    private UpdateEsportPlayerAction esportPlayerAction;

    public InvalidUpdatePlayerInputException(UpdateEsportPlayerAction esportPlayerAction) {
        this.esportPlayerAction = esportPlayerAction;
    }

    @Override
    public String getMessage() {
        return String.format("Invalid input for player update : [firstName : %s, lastName : %s, email: %s, pseudo: %s, dateOfBirth: %s]",
                esportPlayerAction.getFirstName(), esportPlayerAction.getLastName(), esportPlayerAction.getEmail(),
                esportPlayerAction.getPseudo(), esportPlayerAction.getDateOfBirth());
    }
}
