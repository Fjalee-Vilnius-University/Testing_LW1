<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<p>Add new TelNr:</p>
	<form:form method="post" modelAttribute="telNr">

		<form:input path="id" type="hidden" required="required" />
		<form:errors path="id" />

		<form:label path="nr">Tel.Nr.</form:label>
		<form:input path="nr" type="text" required="required" />
		<form:errors path="nr" />

		<form:label path="userId">UserId</form:label>
		<form:input path="userId" type="text" required="required" />
		<form:errors path="userId" />

		<button type="submit">OK</button>
	</form:form>
</div>
<%@ include file="common/footer.jspf"%>