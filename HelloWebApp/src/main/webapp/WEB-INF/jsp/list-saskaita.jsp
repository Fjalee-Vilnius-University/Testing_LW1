<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
<H1>List of Saskaita:</H1>
<!--
<p>${saskaita}</p>
-->
<table border="1">
<caption>Saskaita</caption>
<thead>
<tr>
<th>Id</th>
<th>TelNrId</th>
<th>TelNr</th>
<th>Menuo</th>
<th>Suma</th>
<th>Update</th>
<th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${saskaita}" var="saskaita">
<tr>
<td>${saskaita.id}</td>
<td>${saskaita.telNrId}</td>
<td>${saskaita.telNr}</td>
<td>${saskaita.menuo}</td>
<td>${saskaita.suma}</td>
<td><a type="button" href="/update-saskaita/${saskaita.id}">UPDATE</a></td>
<td><a type="button" href="/delete-saskaita/${saskaita.id}">DELETE</a></td>
</tr>
</c:forEach>
</tbody>
<tfoot>
    <tr>
        <td colspan="7">
            <a type="button" href="/add-saskaita">
                <center>ADD</center>
            </a>
        </td>
    </tr>
</tfoot>
</table>
<div>
</div>
</div>
<%@ include file="common/footer.jspf"%>