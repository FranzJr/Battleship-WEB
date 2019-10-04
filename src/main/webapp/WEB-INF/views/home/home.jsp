<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">




<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>

<body>

	<div class="container">
		<c:if test="${not empty GameError}">
			<div class="alert alert-danger float" role="alert">${GameError}</div>
		</c:if>

		<c:if test="${not empty DetailError}">
			<div class="alert alert-danger float" role="alert">${DetailError}</div>
		</c:if>

		<div class="row mt-5">
			<form action="/create-game" method="POST" class="col">
				<div class="form-group">
					<label for="exampleInputEmail1">Email address</label> <input
						name="emailToGame" type="email" class="form-control"
						id="exampleInputEmail1" aria-describedby="emailHelp"
						placeholder="Enter email"> <small id="emailHelp"
						class="form-text text-muted">We'll never share your email
						with anyone else.</small>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	</div>


</body>

</html>