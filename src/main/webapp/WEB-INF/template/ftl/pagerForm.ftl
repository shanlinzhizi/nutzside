<#macro pagerForm  action_list="#"> 
<form id="pagerForm" method="post" action="${base}/${action_list}">
	<input type="hidden" name="pageNum" value="${obj.pager.pageNumber}" /> 
	<input type="hidden" name="numPerPage" value="${obj.pager.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
    <#nested/> 
</form>
</#macro> 