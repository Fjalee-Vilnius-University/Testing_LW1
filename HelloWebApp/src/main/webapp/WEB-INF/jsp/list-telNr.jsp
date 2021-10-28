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
<th>Nr</th>
<th>UserId</th>
<th>Update</th>
<th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${telNr}" var="telNr">
<tr>
<td>${telNr.id}</td>
<td>${telNr.nr}</td>
<td>${telNr.userId}</td>
<td><a type="button" href="/update-telNr/${telNr.id}">UPDATE</a></td>
<td><a type="button" href="/delete-telNr/${telNr.id}">DELETE</a></td>
</tr>
</c:forEach>
</tbody>
<tfoot>
    <tr>
        <td colspan="7">
            <a type="button" href="/add-telNr">
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