package exception;

import model.command.CreateEsportPlayerAction;

public class InvalidCreationPlayerInputException extends RuntimeException{

    private CreateEsportPlayerAction esportPlayerAction;
    public InvalidCreationPlayerInputException(CreateEsportPlayerAction action) {
        this.esportPlayerAction = action;
    }

    @Override
    public String getMessage() {
        return String.format("Invalid input for player creation : [firstName : %s, lastName : %s, email: %s, pseudo: %s, dateOfBirth: %s]",
                esportPlayerAction.getFirstName(), esportPlayerAction.getLastName(), esportPlayerAction.getEmail(),
                esportPlayerAction.getPseudo(), esportPlayerAction.getDateOfBirth());
    }
}
