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

    	String message = null;
        String body = request.getParameter("Body");
        try
        {
        	Integer responseCount = Integer.parseInt(body);

            HttpSession session = request.getSession(true);
            Integer counter = (Integer)session.getAttribute("counter");
            if (counter == null) {
                counter = new Integer(1);
            }

            int count = counter.intValue();
            if(responseCount.intValue() == count + 1)
            {
            	count = responseCount.intValue() + 1;
            	if(count > 10)
            	{
            		message = "That was fun!";
            		session.setAttribute("counter", new Integer(1));
            	}
            	else
            	{
            		counter = new Integer(count);
            		session.setAttribute("counter", counter);
            		message = counter.toString();
            	}
            }
        }
        catch(NumberFormatException e)
        {
        	message = "Please reply with a number";
        }
    	
        Sms sms = new Sms(message);

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
