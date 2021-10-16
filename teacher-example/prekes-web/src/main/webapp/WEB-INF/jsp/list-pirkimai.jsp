<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
<H1>List of Pirkimai:</H1>
<!--
<p>${pirkimai}</p>
-->
<table border="1">
<caption>Pirkimai</caption>
<thead>
<tr>
<th>Zmogus Id</th>
<th>PrekeKodas</th>
<th>PrekePav</th>
<th>Kiek vnt.</th>
<th>Data</th>
<th>Update</th>
<th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${pirkimai}" var="pirkimas">
<tr>
<td>${pirkimas.zmogausId}</td>
<td>${pirkimas.prekesKodas}</td>
<td>${pirkimas.prekesPav}</td>
<td>${pirkimas.vnt}</td>
<td>${pirkimas.date}</td>
<td><a type="button" href="/update-pirkimas?id=${pirkimas.zmogausId}">UPDATE</a></td>
<td><a type="button" href="/delete-pirkimas?id=${pirkimas.zmogausId}">DELETE</a></td>
</tr>
</c:forEach>

</tbody>
</table>
<div>
<a href="add-pirkimas">ADD Pirkimas</a>
</div>
</div>
<%@ include file="common/footer.jspf"%>