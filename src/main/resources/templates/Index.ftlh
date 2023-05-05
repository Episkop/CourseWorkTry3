<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</head>
<body>
<h3>List Employees</h3>

<hr/>

<table class="table table-bordered table-striped">
<#list profits as
    src="/profit/getProfit"
</#list>
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Department</th>
    </tr>
    </thead>
    <tbody>
    <tr th:each="profit: ${Profits}">
        <td th:text="${profit.}" />
        <td th:text="${employee.email}" />
        <td th:text="${employee.department}" />
    </tr>
    </tbody>

</table>
</div>

</body>
</html>