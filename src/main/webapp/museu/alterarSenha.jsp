<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../erros/paginaErro.jsp" %>

<%
    int id_museu_adm = (int) request.getAttribute("id_museu_adm");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/alterarSenha.css">
    <link rel="stylesheet" href="styles/navbarMuseu.css">

    <link rel="shortcut icon" href="Resources/leon.png" type="image/jpg">
</head>
<body>
<nav id="navbarMuseu">
</nav>

<div id="formulario">
    <h2 id="titulo">Nova Senha:</h2>

    <form action="alterar-senha" method="post">
        <input type="text" name="id_museu_adm" value="<%=id_museu_adm%>" style="display: none">
        <div id="alterarSenha">
            <label for="altSenha">Digite sua nova senha:</label>
            <input type="password" name="novaSenha" id="altSenha" placeholder="Nova senha" size="45" required>
            <%
                if(request.getAttribute("erro") != null){
            %>
            <p style="color: #F5E0D4"><%=request.getAttribute("erro")%></p>
            <%}else{%>
            <p style="opacity: 0;">.</p>
            <%}%>
        </div>

        <input type="submit" id="submit" value="Alterar Senha">
    </form>
</div>
</body>
</html>