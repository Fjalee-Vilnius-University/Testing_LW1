<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
<H1>List of TelNr:</H1>
<!--
<p>${telNr}</p>
-->
<table border="1">
<caption>TelNr</caption>
<thead>
<tr>
<th>Id</th>
<th>TelNr</th>
<th>UserId</th>
<th>Update</th>
<th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${telNr}" var="nr">
<tr>
<td>${nr.id}</td>
<td>${nr.telNr}</td>
<td>${nr.userId}</td>
<td><a type="button" href="/update-telNr/${nr.id}">UPDATE</a></td>
<td><a type="button" href="/delete-telNr/${nr.id}">DELETE</a></td>
</tr>
</c:forEach>

</tbody>
</table>
<div>
</div>
</div>
<%@ include file="common/footer.jspf"%>