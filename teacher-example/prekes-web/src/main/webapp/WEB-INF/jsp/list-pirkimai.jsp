<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<H1>List of Pirkimai:</H1>
<!--
<p>${pirkimai}</p>
-->
<table class="table table-striped" border="1">
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
<td><a type="button" class="btn btn-success" href="/update-pirkimas?id=${pirkimas.zmogausId}">UPDATE</a></td>
<td><a type="button" class="btn btn-warning" href="/delete-pirkimas?id=${pirkimas.zmogausId}">DELETE</a></td>
</tr>
</c:forEach>

</tbody>
</table>
<div>
<p><a class="button" href="add-pirkimas">ADD Pirkimas</a></p>
</div>
</div>
</body>