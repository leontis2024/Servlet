<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true"%>
<%
    String erro = (String) request.getAttribute("erro");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/pagErro.css">
    <link rel="shortcut icon" href="Resources/leon.png" type="image/jpg">
    <title>Erro</title>
</head>
<body>
<div id="erro">
    <div id="imagem">
        <img src="Resources/leonDark.png" id="decorLeontis1">
        <img src="Resources/nome.png" id="decorLeontis2">
    </div>

    <h1 id="tituloErro">Opa! Ocorreu um erro!</h1>
    <p id="descErro">
        <%=erro != null?erro:"Erro interno em nosso sistema! Tente de novo mais tarde"%>
    </p>

    <a href="index.jsp" id="voltar">
        Voltar
    </a>
</div>
</body>
</html>
