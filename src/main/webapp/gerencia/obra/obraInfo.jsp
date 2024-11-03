<%@ page import="org.example.leontisservlet.model.Obra" %>
<%@ page import="org.example.leontisservlet.model.Genero" %>
<%@ page import="org.example.leontisservlet.model.Artista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../erros/paginaErro.jsp" %>

<%Obra obra = (Obra) request.getAttribute("obra");
    Genero genero = (Genero) request.getAttribute("genero");
    Artista artista = (Artista) request.getAttribute("artista");
    int id_museu_adm = (int) request.getAttribute("id_museu_adm");%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/infoObra.css">
    <link rel="shortcut icon" href="Resources/leon.png" type="png">
    <title><%=obra.getNmObra()%></title>
</head>
<body>
<jsp:include page="../../navbar/navbarMuseu.jsp"></jsp:include>

<div class="assunto">
    <div id="obra">
        <div id="infoObra">
            <h1 id="nomeObra"><%=obra.getNmObra()%></h1>

            <p id="descObra">Descrição: <%=obra.getDescObra()%></p>

            <p id="autor">Autor: <%=artista.getNmArtista()%></p>

            <p id="dtInicio">Início da produção: <%=obra.getAnoInicio()%></p>

            <p id="dtFim">Fim da produção: <%=obra.getAnoFinal()%></p>

            <p id="genero">Gênero: <%=genero.getNmGenero()%></p>
            <div id="botoes">
                <form action="editar-obra" method="post">
                    <input type="text" name="id_museu_adm" value="<%=id_museu_adm%>" style="display: none">
                    <input type="text" name="id_obra" value="<%=obra.getId()%>" style="display: none;">
                    <input type="submit" value="Editar" id="editar">
                </form>
                <form action="excluir-obra" method="post">
                    <input type="text" name="id_museu_adm" value="<%=id_museu_adm%>" style="display: none">
                    <input type="text" name="id_obra" value="<%=obra.getId()%>" style="display: none;">
                    <input type="submit" value="Excluir" id="excluir">
                </form>
            </div>
        </div>

        <img src="<%=obra.getUrlImagem()%>" id="fotoObra">
    </div>
</div>
</body>
</html>
