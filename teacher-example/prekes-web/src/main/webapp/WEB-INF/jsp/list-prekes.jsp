<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<H1>List of Prekes:</H1>

<!--
<p>${prekes}</p>
-->

<table class="table table-striped" border="1">
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
<td><a type="button" class="btn btn-success" href="/update-preke?id=${preke.kodas}">UPDATE</a></td>
<td><a type="button" class="btn btn-warning" href="/delete-preke?id=${preke.kodas}">DELETE</a></td>
</tr>
</c:forEach>

</tbody>
</table>

<div>
<p><a class="button" href="add-preke">ADD Preke</a></p>
</div>
</div>
</body>