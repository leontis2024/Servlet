<%@ page import="org.example.leontisservlet.model.Artista" %>
<%@ page import="org.example.leontisservlet.model.Genero" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../erros/paginaErro.jsp" %>

<%Artista artista = (Artista) request.getAttribute("artista");
int id_museu_adm = (int) request.getAttribute("id_museu_adm");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/infoArtista.css">
    <link rel="shortcut icon" href="Resources/leon.png" type="png">
    <title><%=artista.getNmArtista()%></title>

</head>
<body>
<jsp:include page="../../navbarMuseu.jsp"></jsp:include>


<div class="assunto">
    <div id="artista">
        <div id="infoArtista">
            <h1 id="nomeArtista"><%=artista.getNmArtista()%></h1>

            <p id="descArtista">Descrição: <%=artista.getDescArtista()%></p>

            <p id="nascimento">Nascimento: <%=artista.getLocalNasc()%>, <%=artista.getDtNascArtista()%></p>

            <%if(artista.getDtFalecimento() != null){%>
                <p id="morte">Falecimento: <%=artista.getLocalMorte().isEmpty() || artista.getLocalMorte() == null?"Desconhecido":artista.getLocalMorte()%>, <%=artista.getDtFalecimento()%></p>
            <%}%>
            <div id="botoes">
                <form action="editar-artista" method="post">
                    <input type="text" name="id_museu_adm"  value="<%=id_museu_adm%>" style="display: none">
                    <input type="text" name="id_artista" value="<%=artista.getId()%>" style="display: none;">
                    <input type="submit" value="Editar" id="editar">
                </form>
            </div>
        </div>

        <img src="<%=artista.getUrlImagem()%>" id="fotoArtista">
    </div>
</div>

</body>
</html>
