<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="pagerForm" method="post" >
	<input type="hidden" name="pageNum" value="${obj.pager.pageNumber}" /> 
	<input type="hidden" name="numPerPage" value="${obj.pager.pageSize}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<%-- 名称 --%>
	<input type="hidden" name="name" value="${obj.o.name}"/>	
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${base}/system/usr/list" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>账户：<input type="text" name="name" value="${obj.o.name}"/></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a class="button" href="${base}/system/usr/list" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${base}/system/usr/p_add" target="navTab" rel="newPage" title="添加新用户"><span>添加</span></a></li>
			<li><a class="edit" href="#" target="navTab" rel="newPage" title="修改产品设置"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th>编号</th>
		        <th>账户</th>
                <th>部门</th>
                <th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${obj.list}" var="user">
				<tr target="sid_product" rel="${user.id }">
				<td><input name="ids" value="'${user.id}'" type="checkbox"></td>
				<td>${user.id}</a></td>
				<td>${user.name}</a></td>
				<td>${user.department}</td>
				<td>
				<a title="删除用户" target="ajaxTodo" href="${base}/system/usr/view?id=${user.id}" class="btnDel">删除用户</a>
				<a title="查看用户" target="navTab" href="${base}/system/usr/view?id=${user.id}" class="btnView">查看用户</a>
				<a title="编辑用户" target="navTab" href="${base}/system/usr/view?id=${user.id}" class="btnEdit">编辑用户</a>
				</td>
				</tr>
			</c:forEach>
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