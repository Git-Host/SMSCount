package servlet;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Sms;
*/

import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;

@WebServlet(
        name = "SMSCount", 
        urlPatterns = {"/go"}
    )
public class SMSCountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		try
		{
			TwilioRestClient client = new TwilioRestClient("AC6f8edababe938c819e2bf19a9ca6548b", "114795015624e7a9036e9dd2dd50b07f");

			Account account = client.getAccount();

			SmsFactory smsFactory = account.getSmsFactory();
			Map<String,String> smsParams = new HashMap<String,String>();
			smsParams.put("To", "+14152442957"); 
			smsParams.put("From", "(415) 373-6437");
			smsParams.put("Body", "Where's Wallace?");
			Sms sms = smsFactory.create(smsParams);
		}
		catch(TwilioRestException e)
		{

		}
	}
	/*
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
    */
    
}
