package servlet;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	public static String getStackTrace(Throwable aThrowable) 
	{
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}
}
