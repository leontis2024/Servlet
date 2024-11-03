<%@ page import="java.util.LinkedList" %>
<%@ page import="org.example.leontisservlet.model.Obra" %>
<%@ page import="org.example.leontisservlet.model.Museu" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../erros/paginaErro.jsp" %>

<%LinkedList<Obra> obras = (LinkedList<Obra>) request.getAttribute("obras");
    Museu museu = (Museu) request.getAttribute("museu");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/obras.css">
    <title>Catálogo de Obras</title>
</head>
<body>
<jsp:include page="../../navbar/navbarMuseu.jsp"></jsp:include>


<h1 id="titulo">Catálogo de Obras</h1>
<form action="adicionar-obra" method="post">
    <input type="text" name="id_museu_adm" value="<%=museu.getId_adm_museu()%>" style="display: none">
    <input type="submit" id="addObra" value="Adicionar Obra">
</form>



<div id="obras">

    <%
        for (int i = 0; i < obras.size(); i++) {
    %>
    <form name="formObra_<%=obras.get(i).getId()%>" action="obraInfo" method="post">
        <input type="text" name="id_obra" value="<%=obras.get(i).getId()%>" style="display: none">
        <input type="text" name="id_museu_adm" value="<%=museu.getId_adm_museu()%>" style="display: none">
        <div class="obra" onclick="document.forms['formObra_<%=obras.get(i).getId()%>'].submit()">
            <img src="<%=obras.get(i).getUrlImagem()%>" class="ftObra">
            <h2 class="nomeObra"><%=obras.get(i).getNmObra()%></h2>
        </div>
    </form>
    <%}%>

</div>

<script>
    <%
        String erro = (String) request.getAttribute("erro");
        if (erro != null){
    %>
    alert("<%=erro%>")
    <%}%>
</script>
</body>
</html>