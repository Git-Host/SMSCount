package servlet;

import java.io.IOException;

import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;

@WebServlet(
        name = "SMSCount", 
        urlPatterns = {"/go"}
    )
public class SMSCountServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SMSCountServlet.class.getName());
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String phoneNumber = request.getParameter("phoneNumber");
		if(phoneNumber == null || phoneNumber.trim().length() == 0)
		{
			handleError(request, response, "I am not kidding.  Please enter your phone number!");
			return;
		}
		
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		try
		{
			PhoneNumber usProto = phoneUtil.parse(phoneNumber, "US");
			if(!phoneUtil.isValidNumber(usProto))
			{
				handleError(request, response, "Hmmm.  Check your phone number and enter it again.");
				return;
			}
		}
		catch(NumberParseException e)
		{
			handleError(request, response, "Hmmm.  Check your phone number and enter it again.");
			return;
		}

		try
		{
			TwilioRestClient client = new TwilioRestClient("xxx", "yyy");

			Account account = client.getAccount();
			SmsFactory smsFactory = account.getSmsFactory();
			Map<String,String> smsParams = new HashMap<String,String>();
			smsParams.put("To", phoneNumber); 
			smsParams.put("From", "(415) 373-6437");
			smsParams.put("Body", "Let's count to 10.  The first number is 1.  Please reply with the next number!");
			smsFactory.create(smsParams);
		}
		catch(TwilioRestException e)
		{
			handleError(request, response, "Whoops.  Something is wrong here.  Please try later.");
			log.severe(getStackTrace(e));
			return;
		}
		
		request.setAttribute("success", "Great!  Now check your phone for a text message and start counting.");
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	private void handleError(HttpServletRequest request, HttpServletResponse response, String message) throws IOException, ServletException
	{
		request.setAttribute("error", message);
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
    
}
