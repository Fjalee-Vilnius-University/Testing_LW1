<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<p>Add new Saskaita:</p>
	<form:form method="post" modelAttribute="saskaita">
		<form:input path="id" type="hidden" required="required" />
		<form:errors path="id" />

		<form:label path="telNrId">Tel.Nr.</form:label>
		<form:input path="telNrId" type="text" required="required" />
		<form:errors path="telNrId" />

		<form:label path="menuo">UserId</form:label>
		<form:input path="menuo" type="text" required="required" />
		<form:errors path="menuo" />

		<form:label path="suma">UserId</form:label>
		<form:input path="suma" type="text" required="required" />
		<form:errors path="suma" />

		<button type="submit">OK</button>
	</form:form>
</div>
<%@ include file="common/footer.jspf"%>