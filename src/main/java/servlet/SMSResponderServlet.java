package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Sms;

/**
 * Servlet implementation class SMSResponderServlet
 */
@WebServlet(
        name = "SMSResponder", 
        urlPatterns = {"/reply"}
    )
public class SMSResponderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TwiMLResponse twiml = new TwiMLResponse();
        Sms sms = new Sms("Hello, Mobile Monkey");
        try {
            twiml.append(sms);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
 
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
    }
}
