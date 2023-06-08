package BeansUtility;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesNotification {
	public static void showDoneMessage(String header, String body) {
        addMessage(FacesMessage.SEVERITY_INFO, header, body);
    }
    
    public static void showErrorMessage(String header, String body)
    {
        addMessage(FacesMessage.SEVERITY_ERROR, header,body );
    }
    
	public static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
