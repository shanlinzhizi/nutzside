<#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>主页管理</title>

<link href="${base}/res/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${base}/res/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${base}/res/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${base}/res/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<script src="${base}/res/js/speedup.js" type="text/javascript"></script>
<script src="${base}/res/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${base}/res/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${base}/res/js/jquery.validate.js" type="text/javascript"></script>
<script src="${base}/res/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${base}/res/xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="${base}/res/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="${base}/res/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="${base}/res/chart/raphael.js"></script>
<script type="text/javascript" src="${base}/res/chart/g.raphael.js"></script>
<script type="text/javascript" src="${base}/res/chart/g.bar.js"></script>
<script type="text/javascript" src="${base}/res/chart/g.line.js"></script>
<script type="text/javascript" src="${base}/res/chart/g.pie.js"></script>
<script type="text/javascript" src="${base}/res/chart/g.dot.js"></script>

<script src="${base}/res/js/dwz.core.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.drag.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.tree.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.ui.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.theme.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.tab.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.resize.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.stable.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.database.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.effects.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.panel.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.history.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.combox.js" type="text/javascript"></script>
<script src="${base}/res/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${base}/res/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${base}/res/dwz.frag.xml", {
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${base}/res/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>
<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<ul class="nav">
					<li><span class="welcome"><strong><@shiro.principal /></strong>&nbsp;您好!&nbsp;</span></li>
					<li><a href="${base}/logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>主菜单</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="tabsPage.html" target="navTab">面板</a>
								<ul>
								     <li><a href="${base}/erp/Product/list" target="navTab" rel="product">产品设置</a></li>
									 <li><a href="${base}/system/usr/list" target="navTab" rel="product">用户管理</a></li>
									  <li><a href="${base}/system/role/all" target="navTab" rel="product">角色管理</a></li>
									  <li><a href="${base}/system/permission/all" target="navTab" rel="product">权限管理</a></li>
								</ul>
							</li>
							</ul>
					</div>
					
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="right">
							</div>
							<p><span>nutzside</span></p>
							<p>nutz,shiro和freemarker</p>
						</div>
					</div>

					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2010 nutzside</a> </div>


</body>
</html>