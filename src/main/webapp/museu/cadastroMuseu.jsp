<%@ page import="org.example.leontisservlet.model.Museu" %>
<%@ page import="org.example.leontisservlet.model.DiaFuncionamento" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page errorPage="../erros/paginaErro.jsp" %>


<%
    Museu museu = (Museu) request.getAttribute("museu");
//    Caso ele mande um objeto de museu, inseriremos o valor dele, entao para facilitar, criamos uma variavel para isso
    boolean objMuseu = museu != null;

    //<editor-fold desc="Atributos dia funcionamento">
    DiaFuncionamento segunda = (DiaFuncionamento) request.getAttribute("segunda");
    boolean objSegunda = segunda != null;
    DiaFuncionamento terca = (DiaFuncionamento) request.getAttribute("terca");
    boolean objTerca = terca != null;
    DiaFuncionamento quarta = (DiaFuncionamento) request.getAttribute("quarta");
    boolean objQuarta = quarta != null;
    DiaFuncionamento quinta = (DiaFuncionamento) request.getAttribute("quinta");
    boolean objQuinta = quinta != null;
    DiaFuncionamento sexta = (DiaFuncionamento) request.getAttribute("sexta");
    boolean objSexta = sexta != null;
    DiaFuncionamento sabado = (DiaFuncionamento) request.getAttribute("sabado");
    boolean objSabado = sabado != null;
    DiaFuncionamento domingo = (DiaFuncionamento) request.getAttribute("domingo");
    boolean objDomingo = domingo != null;
    //</editor-fold>
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        <%=(objMuseu && museu.getId() > 0)?"Editando":"Cadastro"%>
    </title>
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/cadastroMuseu.css">
    <link rel="shortcut icon" href="Resources/leon.png" type="image/jpg">
</head>
<body>
<nav id="navbar">
    <a href="index.jsp" id="leon"><img src="Resources/leon.png"></a>

</nav>

<form action="cadastro-museu" method="post" enctype="multipart/form-data">
    <input type="text" name="id_museu_adm" value="<%=request.getAttribute("id_museu_adm")%>" style="display: none">
    <input type="text" name="id_museu" value="<%=objMuseu ? museu.getId() : -1%>" style="display: none">
    <input type="text" name="id_dom" value="<%=objDomingo ? domingo.getId() : "-1" %>" style="display: none">
    <input type="text" name="id_seg" value="<%=objSegunda ? segunda.getId() : "-1" %>" style="display: none">
    <input type="text" name="id_ter" value="<%=objTerca ? terca.getId() : "-1" %>" style="display: none">
    <input type="text" name="id_qua" value="<%=objQuarta ? quarta.getId() : "-1" %>" style="display: none">
    <input type="text" name="id_qui" value="<%=objQuinta ? quinta.getId() : "-1" %>" style="display: none">
    <input type="text" name="id_sex" value="<%=objSexta ? sexta.getId() : "-1" %>" style="display: none">
    <input type="text" name="id_sab" value="<%=objSabado ? sabado.getId() : "-1" %>" style="display: none">

    <div id="formulario">
        <h1 id="titCadastro">
                <%=(objMuseu && museu.getId() > 0) ? ("Atualizando "+museu.getNmMuseu()) : "Cadastro de Museu"%>
        </h1>
        <div id="nomeDiv">
            <label for="nome">Nome do Museu:</label>
            <input type="text" name="nome" id="nome" placeholder="Digite qual o nome do museu" size="45" value="<%=objMuseu?museu.getNmMuseu():""%>" required>
        </div>

        <div id="inauguracaoDiv">
            <label for="dtInauguracao">Inauguração:</label>
            <input type="date" name="dtInauguracao" id="dtInauguracao" required value="<%=objMuseu?museu.getDtInauguracao():""%>">
        </div>

        <div id="telDiv">
            <label for="tel">Telefone:</label>
            <input type="text" name="tel" id="tel" value="<%=objMuseu?museu.getNrTelMuseu():""%>">
        </div>

        <div id="ftMuseuDiv">
            <label for="image">Foto do museu:</label>
            <input type="file" name="image" id="image">
        </div>

        <div id="descDiv">
            <label for="desc">Descrição:</label>
            <textarea name="desc" id="desc" cols="60" rows="5"><%=objMuseu?museu.getDescMuseu():""%></textarea>
        </div>

        <div id="estadoDiv">
            <%
                String[] estados = {
                        "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará",
                        "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso",
                        "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba",
                        "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro",
                        "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia",
                        "Roraima", "Santa Catarina", "São Paulo", "Sergipe",
                        "Tocantins", "Distrito Federal"
                };
                String[] siglas = {
                        "AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
                        "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF"
                };
                String estadoSelecionado = objMuseu? museu.getEstado() : "";
            %>
            <label for="estado">Estado:</label>
            <select name="estado" id="estado" required size="4">
                <%
                    for (int i = 0; i<estados.length; i++) {
                        String estado = estados[i];
                        String sigla = siglas[i];
                %>
                <option value="<%=sigla%>" <%=sigla.equals(estadoSelecionado)?"selected":""%>><%=estado%></option>
                <%}%>
            </select>
        </div>

        <div id="cidadeDiv">
            <label for="cidade">Cidade:</label>
            <input type="text" name="cidade" id="cidade" required value="<%=objMuseu?museu.getCidade():""%>">
        </div>

        <div id="diasFunc">
            <h2 id="titDiasFunc">Dias de Funcionamento:</h2>

            <div class="diaSemana">
                <p>Domingo</p>
                <input type="time" name="aberDom" class="hrAbertura" value="<%=objDomingo ? domingo.getHrInicio() : "" %>">
                <input type="time" name="fechDom" class="hrFechamento" value="<%=objDomingo ? domingo.getHrTermino() : "" %>">
                <input type="number" name="valorDom" class="valorIngresso" min="0" value="<%=objDomingo ? domingo.getPrDiaFuncionamento() : "" %>">
            </div>

            <div class="diaSemana">
                <p>Segunda</p>
                <input type="time" name="aberSeg" class="hrAbertura" value="<%=objSegunda ? segunda.getHrInicio() : ""%>">
                <input type="time" name="fechSeg" class="hrFechamento" value="<%=objSegunda ? segunda.getHrTermino() : ""%>">
                <input type="number" name="valorSeg" class="valorIngresso" min="0" value="<%=objSegunda ? segunda.getPrDiaFuncionamento() : ""%>">
            </div>

            <div class="diaSemana">
                <p>Terça</p>
                <input type="time" name="aberTer" class="hrAbertura" value="<%=objTerca ? terca.getHrInicio() : ""%>">
                <input type="time" name="fechTer" class="hrFechamento" value="<%=objTerca ? terca.getHrTermino() : ""%>">
                <input type="number" name="valorTer" class="valorIngresso" min="0" value="<%=objTerca ? terca.getPrDiaFuncionamento() : ""%>">
            </div>

            <div class="diaSemana">
                <p>Quarta</p>
                <input type="time" name="aberQua" class="hrAbertura" value="<%=objQuarta ? quarta.getHrInicio() : ""%>">
                <input type="time" name="fechQua" class="hrFechamento" value="<%=objQuarta ? quarta.getHrTermino() : ""%>">
                <input type="number" name="valorQua" class="valorIngresso" min="0" value="<%=objQuarta ? quarta.getPrDiaFuncionamento() : ""%>">
            </div>

            <div class="diaSemana">
                <p>Quinta</p>
                <input type="time" name="aberQui" class="hrAbertura" value="<%=objQuinta ? quinta.getHrInicio() : ""%>">
                <input type="time" name="fechQui" class="hrFechamento" value="<%=objQuinta ? quinta.getHrTermino() : ""%>">
                <input type="number" name="valorQui" class="valorIngresso" min="0" value="<%=objQuinta ? quinta.getPrDiaFuncionamento() : ""%>">
            </div>

            <div class="diaSemana">
                <p>Sexta</p>
                <input type="time" name="aberSex" class="hrAbertura" value="<%=objSexta ? sexta.getHrInicio() : ""%>">
                <input type="time" name="fechSex" class="hrFechamento" value="<%=objSexta ? sexta.getHrTermino() : ""%>">
                <input type="number" name="valorSex" class="valorIngresso" min="0" value="<%=objSexta ? sexta.getPrDiaFuncionamento() : ""%>">
            </div>

            <div class="diaSemana">
                <p>Sábado</p>
                <input type="time" name="aberSab" class="hrAbertura" value="<%=objSabado ? sabado.getHrInicio() : ""%>">
                <input type="time" name="fechSab" class="hrFechamento" value="<%=objSabado ? sabado.getHrTermino() : ""%>">
                <input type="number" name="valorSab" class="valorIngresso" min="0" value="<%=objSabado ? sabado.getPrDiaFuncionamento() : ""%>">
            </div>
        </div>
        <div id="ruaDiv">
            <label for="rua">Rua:</label>
            <input type="text" name="rua" id="rua" required value="<%=objMuseu?museu.getRua():""%>">
        </div>

        <div id="cnpjDiv">
            <label for="cnpj">Cnpj:</label>
            <input type="text" name="cnpj" id="cnpj" required value="<%=objMuseu?museu.getCnpj():""%>">
        </div>

        <div id="cepDiv">
            <label for="cep">Cep:</label>
            <input type="text" name="cep" id="cep" required value="<%=objMuseu?museu.getCep():""%>">
        </div>

        <div id="pontoRefDiv">
            <label for="pontoRef">Ponto de referência:</label>
            <input type="text" name="pontoRef" id="pontoRef" value="<%=objMuseu?museu.getPontoReferencia():""%>">
        </div>

       <div id="finalizar">
            <input type="submit" name="submit" id="submit" value="enviar" class="botao">
       </div>
    </div>
</form>

<%--Dando alert nos erros de dados inseridos no modelo incorreto--%>
<script>
    <%
        LinkedList<String> erros = (LinkedList<String>) request.getAttribute("erros");
        if (erros != null){
            for(int i=0; i<erros.size(); i++){
    %>
                alert("<%=erros.get(i)%>")
    <%      }
        }%>
</script>

</body>
</html>