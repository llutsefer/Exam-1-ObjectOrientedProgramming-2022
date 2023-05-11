import java.util.List;

public class AmbigiousProductException extends Exception{
    List<String> messageList;
    public AmbigiousProductException(List<String> message){
        this.messageList = message;
    }

    @Override
    public String getMessage() {
        String message = "";
        for (String s : messageList) {
            message += s + "\n";
        }
        return message;
    }
}
