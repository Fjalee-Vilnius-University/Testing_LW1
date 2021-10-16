<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
<H1>List of Zmones:</H1>

<!--
<p>${zmones}</p>
-->

<table border="1">
<thead>
<tr>
<th>Id</th><th>Vardas</th><th>Update</th><th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${zmones}" var="zm">
<tr>
<td>${zm.id}</td>
<td>${zm.vardas}</td>
<td><a type="button" href="/update-zmogus/${zm.id}">UPDATE</a></td>
<td><a type="button" href="/delete-zmogus/${zm.id}">DELETE</a></td>
</tr>
</c:forEach>

</tbody>
</table>

<div>
<a href="add-zmogus">ADD Zmogus</a>
</div>
</div>
<%@ include file="common/footer.jspf"%>