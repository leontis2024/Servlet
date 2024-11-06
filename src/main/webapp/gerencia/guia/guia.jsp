<%@ page import="java.util.LinkedList" %>
<%@ page import="org.example.leontisservlet.model.Guia" %>
<%@ page import="org.example.leontisservlet.model.Museu" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../erros/paginaErro.jsp" %>

<%LinkedList<Guia> guias = (LinkedList<Guia>) request.getAttribute("guias");
    Museu museu = (Museu) request.getAttribute("museu");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/guias.css">
    <link rel="shortcut icon" href="Resources/leon.png" type="image/jpg">
    <title>Catálogo de Guias</title>
</head>
<body>
<jsp:include page="../../navbar/navbarMuseu.jsp"></jsp:include>


<h1 id="titulo">Catálogo de Guias</h1>

<form action="adicionar-guia" method="post">
    <input type="text" name="id_museu_adm" value="<%=museu.getId_adm_museu()%>" style="display: none">
    <input type="submit" id="addGuia" value="Adicionar Guia">
</form>

<div id="guias">
    <%for (int i = 0; i <guias.size() ; i++) {%>
    <div class="guia">
        <div class="infoGuia">
            <h2 class="nomeGuia"><%=guias.get(i).getTituloGuia()%></h2>
            <p class="descGuia"><%=guias.get(i).getDescGuia()%></p>
            <div class="botoes">
                <form action="editar-guia" method="post">
                    <input type="text" name="id_museu_adm" value="<%=museu.getId_adm_museu()%>" style="display: none">
                    <input type="text" name="id_guia" value="<%=guias.get(i).getId()%>" style="display: none">
                    <input type="submit" value="Editar guia" id="editar" class="botao">
                </form>

                <form action="gerencia-obras-guia" method="post">
                    <input type="text" name="id_museu_adm" value="<%=museu.getId_adm_museu()%>" style="display: none">
                    <input type="text" name="id_guia" value="<%=guias.get(i).getId()%>" style="display: none;">
                    <input type="submit" value="Gerenciar obras do guia" id="gerenciar" class="botao">
                </form>

                <form action="excluir-guia" method="post">
                    <input type="text" name="id_museu_adm" value="<%=museu.getId_adm_museu()%>" style="display: none">
                    <input type="text" name="id_guia" value="<%=guias.get(i).getId()%>" style="display: none;">
                    <input type="submit" value="Excluir guia" id="excluir" class="botao">
                </form>
            </div>
        </div>
        <div class="ftGuia">
            <img src="<%=guias.get(i).getUrlImagem()%>">
        </div>

    </div>
    <%}%>
</div>
</body>
</html>
