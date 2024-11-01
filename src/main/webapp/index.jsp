<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page errorPage="erros/paginaErro.jsp" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/login.css">
    <link rel="shortcut icon" href="Resources/leon.png" type="image/jpg">
</head>
<body>
<nav id="navbar">
    <div id="left">
        <a href="Index.html" id="leon"><img src="Resources/leon.png"></a>
    </div>

    <div id="links">
        <a href="SobreApp.html">Sobre o App</a>
        <a href="SobreEmpresa.html">Sobre a Empresa</a>
        <a href="#nossosCont">Nossos Contatos</a>
    </div>
</nav>

<div id="login">
    <div id="formulario">
        <h2 id="titulo">Login</h2>

        <form action="login-adm" method="post">
            <div id="inputEmail">
                <label for="email">E-mail</label>
                <input type="email" name="email" id="email" placeholder="Digite o e-mail da empresa" size="45" required>
            </div>

            <div id="inputSenha">
                <label for="pwd">Senha</label>
                <input type="password" name="passwd" id="pwd" placeholder="Digite a senha da empresa" size="45" required>
                <%
                    if(request.getAttribute("erro") != null){
                %>
                <p style="color: #F5E0D4"><%=request.getAttribute("erro")%></p>
                <%}else{%>
                <p style="opacity: 0;">.</p>
                <%}%>
            </div>

            <div id="finalizar">
                <input type="submit" name="submit" id="submit">
            </div>
        </form>
    </div>
</div>
</body>
</html>