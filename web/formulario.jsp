<%@ taglib uri="/tags/struts-bean" prefix="bean"%> 
<%@ taglib uri="/tags/struts-html" prefix="html"%> 
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/pager-taglib" prefix="pg"%>

<%@ include file="cabecalho.jsp"%>

	<body>
      </br>
      </br>
		
		<table width="20%" align="center"  class="TextArea_FaleRI" border="0">
		<br>
		  <tr>
		    <td  align="center"><bean:message key="page.formulario.topico"/></td>
		  </tr>
		</table>
		
		</br>
		</br>


		<logic:present name="Dados">
		<bean:size id="items" name="Dados"/>
		<logic:equal name="items" value="0" scope="page">
		  <table align="center" border="0" cellpadding="0" cellspacing="0" class="tabela_dados">
		    <tr>
		      <td height="20">&nbsp;</td>
		    </tr>
		    <tr>
		      <td height="40"><span class="titulo_area">N&atilde;o foi encontrado nenhum documento.</span></td>
		    </tr>
		  </table>
		</logic:equal>

		<logic:notEqual name="items" value="0" scope="page">
		  <%
		    boolean listar    = false;
		    String position   = "both";
		    String index      = "center";
		    int maxPageItems  = 1;
		    int maxIndexPages = 100;
		  %>
		<pg:pager
		    url="pesquisa.do"
		    items="<%= items.intValue() %>"
		    index="<%= index %>"
		    maxPageItems="<%= maxPageItems %>"
		    maxIndexPages="<%= maxIndexPages %>"
		    isOffset="<%= true %>"
		    export="offset,currentPageNumber=pageNumber"
		    scope="request">
		<!-- Configurando paginacao -->
		  <pg:param name="position"/>
		  <pg:param name="index"/>
		  <pg:param name="maxPageItems"/>
		  <pg:param name="maxIndexPages"/>
		  <% 
		    int inicio_da_pagina  = offset.intValue();
		    int fim_da_pagina     = Math.min(maxPageItems + inicio_da_pagina, items.intValue() );
		    int tamanho_da_pagina = fim_da_pagina - inicio_da_pagina;
		  %>

    <table align="center" border="0" cellpadding="0" cellspacing="0" class="tabela_dados">
      <tr>
        <td colspan="2">


		      <table width="90%" align="center" class="TextArea_FaleRI" border="0">
		        <tr>
		          <td width="15%" align="center" bgColor=#bcbcbc><bean:message key="page.formulario.emprNome"/></td>
		          <td width="15%" align="center" bgColor=#bcbcbc><bean:message key="page.formulario.pagNumPagamento"/></td>
		          <td width="15%" align="center" bgColor=#bcbcbc><bean:message key="page.formulario.modPagtoDescricao"/></td>
		          <td width="10%" align="center" bgColor=#bcbcbc><bean:message key="page.formulario.stDescricao"/></td>
		          <td width="15%" align="center" bgColor=#bcbcbc><bean:message key="page.formulario.pagDataVencimento"/></td>
		          <td width="15%" align="center" bgColor=#bcbcbc><bean:message key="page.formulario.pagDataEfetivacaoPagto"/></td>
		          <td width="20%" align="center" bgColor=#bcbcbc><bean:message key="page.formulario.pagNumNotaFiscal"/></td>
		          <td width="15%" align="center" bgColor=#bcbcbc><bean:message key="page.formulario.tpdocDescricao"/></td>          
		          <td width="15%" align="center" bgColor=#bcbcbc><bean:message key="page.formulario.pagValorPagamento"/></td>
		        </tr>
		      </table>

            <table width="90%" align="center" class="borda" border="0">
		      <logic:iterate id="Lista" name="Dados" scope="session" offset="offset" length="tamanho_da_pagina">
            <pg:item>
            <logic:iterate name="Lista" id="Pagamento">

		        <tr>
		          <td width="15%" align="center" class="estilos_ri"><bean:write name="Pagamento" property="emprNome" filter="true" /></td>
		          <td width="15%" align="center" class="estilos_ri"><bean:write name="Pagamento" property="pagNumPagamento" filter="true" /></td>
		          <td width="15%" align="center" class="estilos_ri"><bean:write name="Pagamento" property="modPagtoDescricao" filter="true" /></td>
		          <td width="10%" align="center" class="estilos_ri"><bean:write name="Pagamento" property="stDescricao" filter="true" /></td>
		          <td width="15%" align="center" class="estilos_ri"><bean:write name="Pagamento" property="pagDataVencimento" filter="true" /></td>
		          <td width="15%" align="center" class="estilos_ri"><bean:write name="Pagamento" property="pagDataEfetivacaoPagto" filter="true" /></td>
		          <td width="15%" align="center" class="estilos_ri"><bean:write name="Pagamento" property="pagNumNotaFiscal" filter="true" /></td>
		          <td width="15%" align="center" class="estilos_ri"><bean:write name="Pagamento" property="tpDocDescricao" filter="true" /></td>         
		          <td width="20%" align="center" class="estilos_ri"><bean:write name="Pagamento" property="pagValorPagamento" filter="true" /></td>
		        </tr>

              <logic:notEqual name="Pagamento" property="total" value="">
                <tr>
                  <td width="15%" align="center" bgColor=#bcbcbc><bean:message key="page.formulario.total"/></td>
                  <td width="15%" bgColor=#bcbcbc></td>
                  <td width="15%" bgColor=#bcbcbc></td>
                  <td width="10%" bgColor=#bcbcbc></td>
                  <td width="15%" bgColor=#bcbcbc></td>
                  <td width="15%" bgColor=#bcbcbc></td>
                  <td width="15%" bgColor=#bcbcbc></td>
                  <td width="15%" bgColor=#bcbcbc></td>
                  <td width="20%" align="right" bgColor=#bcbcbc><bean:write name="Pagamento" property="total" filter="true" /></td>
                </tr>  
              </logic:notEqual>

		      </logic:iterate>
            </pg:item>
            </logic:iterate>
          </table>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="hidden" name="pager.offset" value="<%= offset %>"/>
          <div align="center">
            <pg:index>
              <jsp:include page="pager.jsp"/>
            </pg:index>
          </div>
        </td>
      </tr>
    </table>
		
		</pg:pager>
		</logic:notEqual>
		</logic:present>
				
		<br>
		<br>
		
		<html:form action="/formulario">

		<table width="30%" align="center" border="0">
		  <tr>
		    <td align="center"><html:submit>Pesquisar Novamente</html:submit></td>
		  </tr>
		</table>

		</html:form>
		
		<table width="30%" align="center"  class="Topicos_MapaSite">
		  <tr>
		    <td align="center"><html:errors/></td>
		  </tr>
		</table> 
		
	<body>
</html>