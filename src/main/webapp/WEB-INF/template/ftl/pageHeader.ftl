
<#macro pagerForm  action_list="#" action_listui="#" > 
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
</#macro> 