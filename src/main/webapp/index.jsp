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
			  
			  <form action="/go" method="post">
  				<input type="text" class="required tel input-xlarge" placeholder="(xxx) xxx-xxxx">
  				<button type="submit" class="button btn btn-inverse">Submit</button>
			  </form>

			</div>
		</div>
	</div>
	</div>
    <script src="style/js/bootstrap.min.js"></script>
  </body>
</html>