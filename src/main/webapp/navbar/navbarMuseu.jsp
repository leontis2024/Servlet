<%@ page import="org.example.leontisservlet.model.Museu" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../erros/paginaErro.jsp" %>

<%
    Museu museu = (Museu) request.getAttribute("museu");
    int id_museu_adm = (int) request.getAttribute("id_museu_adm");
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styles/navbarMuseu.css">
</head>
<body>
<nav id="navbarMuseu">
    <div id="left">
        <a href="login.jsp" id="leon"><img src="Resources/leon.png"></a>
    </div>

    <div id="opcoesAtalhos">
        <div id="titOpcoesAtalhos">
            Atalhos
        </div>

        <div id="atalhos">
            <form name="home-form" action="museu" method="POST">
                <input type="text" name="id_museu_adm" value="<%=id_museu_adm%>" style="display: none">
                <a class="atalho" onclick="document.forms['home-form'].submit()">Home</a>
            </form>
            <form name="artista-form" action="artista" method="POST">
                <input type="text" name="id_museu_adm" value="<%=id_museu_adm%>" style="display: none">
                <a class="atalho" onclick="document.forms['artista-form'].submit()">Artista</a>
            </form>
            <form name="obra-form" action="obra" method="POST">
                <input type="text" name="id_museu_adm" value="<%=id_museu_adm%>" style="display: none">
                <a class="atalho" onclick="document.forms['obra-form'].submit()">Obra</a>
            </form>
            <form name="guia-form" action="guia" method="POST">
                <input type="text" name="id_museu_adm" value="<%=id_museu_adm%>" style="display: none">
                <a class="atalho" onclick="document.forms['guia-form'].submit()">Guia</a>
            </form>
        </div>
    </div>

    <div id="navNomeMuseu">
        <%=museu.getNmMuseu()%>
    </div>
</nav>
</body>
</html>


