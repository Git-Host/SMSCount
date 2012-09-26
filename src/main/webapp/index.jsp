<!DOCTYPE html>
<html>
  <head>
    <title>SMSCount</title>
    <link href="style/css/bootstrap.min.css" rel="stylesheet">
    <link href="style/css/smscount.css" rel="stylesheet">
  </head>
  <body>
  <div class="container"> 
    <div class="row">
    	<div class="span12">
    	<br/>
    	<br/>
    	<br/>
	</div>
  </div>

    <div class="row">
    	<div class="span12">
			<div class="hero-unit">
			  <h1>SMSCount</h1>
			  <p>Enter your phone number to start counting!</p>
			  
		       	<%
				String error = (String)request.getAttribute("error");
				if(error != null)
				{
		     	%>
		        <div class="alert alert-error">
		         <span><strong><%= error %></strong></span>
			    </div>
		     	<%
				}
		     	%>
		     	
		     	<%
				String success = (String)request.getAttribute("success");
				if(success != null)
				{
		     	%>
		        <div class="alert alert-success">
		         <span><strong><%= success %></strong></span>
			    </div>
		     	<%
				}
		     	%>
			  
			  <form action="/go" method="post" class="form-inline">
  				<input type="text" class="required tel input-xlarge" name="phoneNumber" placeholder="(xxx) xxx-xxxx">
  				<button type="submit" class="button btn btn-inverse">Submit</button>
			  </form>

			</div>
		</div>
	</div>
	</div>
    <script src="style/js/bootstrap.min.js"></script>
  </body>
</html>