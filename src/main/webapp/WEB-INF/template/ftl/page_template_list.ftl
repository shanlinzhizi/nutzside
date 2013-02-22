<#macro Pager_list action_list="#"  action_listui="#"  action_delete   action_view="#"   action_edit="#"  action_name="数据"> 
<form id="pagerForm" method="post" action="${base}/${action_list}">
	<input type="hidden" name="pageNum" value="${obj.pager.pageNumber}" /> 
	<input type="hidden" name="numPerPage" value="${obj.pager.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<#nested/> 
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${base}/${action_list}" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<#nested/> 
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a class="button" href="${base}/${action_listui}" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		       <#nested/> 
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<#nested/> 
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#list ${obj.list} as tobj>
			 <tr target="sid_product" rel="${tobj.id }">
                   <td><input name="ids" value="'${tobj.id}'" type="checkbox"></td>
				       <#nested/> 
				<td>
				<a title="删除${action_name}" target="ajaxTodo" href="${base}/${action_delete}?id=${tobj.id }" class="btnDel">删除${action_name}</a>
				<a title="查看${action_name}" target="navTab" href="${base}/${action_view}?id=${tobj.id }" class="btnView">查看${action_name}</a>
				<a title="编辑${action_name}" target="navTab" href="${base}/${action_edit}/edit?id=${tobj.id}" class="btnEdit">编辑${action_name}</a>
				</td>
				</tr>
		    </#list> 
		</tbody>
	</table>
	
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> 
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
			<c:forEach begin="10" end="40" step="10" varStatus="s">
				<option value="${s.index}" ${obj.pager.pageSize eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
		</select>
			<span>条，共${obj.pager.recordCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="${obj.pager.recordCount}" numPerPage="${obj.pager.pageSize}" pageNumShown="10" currentPage="${obj.pager.pageNumber}"></div>

	</div>
</div>
</#macro> 