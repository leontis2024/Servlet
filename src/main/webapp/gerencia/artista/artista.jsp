<%@ page import="java.util.LinkedList" %>
<%@ page import="org.example.leontisservlet.model.Artista" %>
<%@ page import="org.example.leontisservlet.model.Museu" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../erros/paginaErro.jsp" %>
<%
    LinkedList<Artista> artistas = (LinkedList<Artista>) request.getAttribute("artistas");
    Museu museu = (Museu) request.getAttribute("museu");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/artistas.css">
    <title>Catálogo de Artistas</title>
</head>
<body>

<jsp:include page="../../navbar/navbarMuseu.jsp"></jsp:include>

<h1 id="titulo">Catálogo de Artistas</h1>
<form action="adicionar-artista" method="post">
    <input type="text" name="id_museu_adm" value="<%=museu.getId_adm_museu()%>" style="display: none">
    <input type="submit" id="addArtista" value="Adicionar Artista">
</form>


<div id="artistas">
    <%
        for (int i = 0; i < artistas.size(); i++) {
    %>
    <form name="formArtista_<%=artistas.get(i).getId()%>" action="artistaInfo" method="post">
        <input type="text" name="id_artista" value="<%=artistas.get(i).getId()%>" style="display: none">
        <input type="text" name="id_museu_adm" id="id_museu_adm" value="<%=museu.getId_adm_museu()%>" style="display: none">
        <div class="artista" onclick="document.forms['formArtista_<%=artistas.get(i).getId()%>'].submit()">
            <img src="<%=artistas.get(i).getUrlImagem()%>" class="ftArtista">
            <h2 class="nomeArtista"><%=artistas.get(i).getNmArtista()%></h2>
        </div>
    </form>
    <%}%>

</div>
</body>
</html>