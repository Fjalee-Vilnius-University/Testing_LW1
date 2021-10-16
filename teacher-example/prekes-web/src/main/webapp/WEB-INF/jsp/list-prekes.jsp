<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
<H1>List of Prekes:</H1>

<!--
<p>${prekes}</p>
-->

<table border="1">
<caption>Prekes</caption>
<thead>
<tr>
<th>Kodas</th><th>Pavadinimas</th><th>Salis</th><th>Kaina vnt.</th><th>Update</th><th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${prekes}" var="preke">
<tr>
<td>${preke.kodas}</td>
<td>${preke.pavadinimas}</td>
<td>${preke.salis}</td>
<td>${preke.kainaVnt}</td>
<td><a type="button" href="/update-preke/${preke.kodas}">UPDATE</a></td>
<td><a type="button" href="/delete-preke/${preke.kodas}">DELETE</a></td>
</tr>
</c:forEach>

</tbody>
</table>

<div>
<a href="add-preke">ADD Preke</a>
</div>
</div>
<%@ include file="common/footer.jspf"%>