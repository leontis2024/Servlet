<%@ page import="org.example.leontisservlet.model.Guia" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../erros/paginaErro.jsp" %>

<%
    int id_museu_adm = (int) request.getAttribute("id_museu_adm");

    Guia guia = (Guia) request.getAttribute("guia");
    boolean objGuia = guia != null;
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%=(objGuia && guia.getId() > 0)?"Editando":"Cadastro"%></title>
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/cadastroGuia.css">
    <link rel="shortcut icon" href="Resources/leon.png" type="image/jpg">
</head>
<body>
<jsp:include page="../../navbar/navbarMuseu.jsp"></jsp:include>


<form action="cadastro-guia" method="post" enctype="multipart/form-data">
    <input type="text" name="id_museu_adm" value="<%=request.getAttribute("id_museu_adm")%>" style="display: none">
    <input type="text" name="id_guia" value="<%=objGuia?guia.getId():-1%>" style="display: none">
    <div id="formulario">

        <h1 id="titCadastro"><%=(objGuia && guia.getId() > 0) ? ("Atualizando "+ guia.getTituloGuia()) : "Cadastro de Guia"%></h1>
        <div id="nome">
            <label for="nomeGuia">Tiítulo do Guia</label>
            <input type="text" name="nomeGuia" id="nomeGuia" size="45" required value="<%=objGuia?guia.getTituloGuia():""%>">
        </div>

        <div id="ftGuia">
            <label for="image">Foto do Guia</label>
            <input type="file" name="image" id="image">
        </div>

        <div id="desc">
            <label for="descricao">Descrição</label>
            <textarea name="descricao" id="descricao"><%=objGuia?guia.getDescGuia():""%></textarea>
        </div>

        <div id="finalizar">
            <input type="submit" name="submit" id="submit" value="Enviar" class="botao">
        </div>
    </div>
</form>
</body>
</html>
