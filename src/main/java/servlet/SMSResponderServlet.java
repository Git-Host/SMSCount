package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    	/*

        HttpSession session = request.getSession(true);
        Integer counter = (Integer)session.getAttribute("counter");
        if (counter == null) {
            counter = new Integer(1);
        }
 
        int count = counter.intValue();
        if(count == 10)
        count++;
        session.setAttribute("counter", new Integer(count));
                Sms sms = new Sms(counter.toString());

        */
        String body = request.getParameter("Body");
        Sms sms = new Sms(body);

        
        TwiMLResponse twiml = new TwiMLResponse();
        try {
            twiml.append(sms);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
 
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
    }
}
