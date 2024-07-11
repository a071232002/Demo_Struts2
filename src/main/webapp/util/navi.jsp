<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<header>
    <div class="index-navi">
        <a href="<%=request.getContextPath()%>/">首頁</a>
        <a href="<%=request.getContextPath()%>/shop/index">商城</a>
        <a href="<%=request.getContextPath()%>/manage/select">後台管理</a>
    </div>
    <div class="user-options">
        <s:if test="#session.user != null">
            <h4 style="display: inline; margin-right:30px;">Hello, <s:property value="#session.user.userName"/></h4>
            <a href="<%=request.getContextPath()%>/user/memCenter">會員中心</a>
            <a href="<%=request.getContextPath()%>/order/query">我的訂單</a>
            <a href="<%=request.getContextPath()%>/cart/query">購物車(<s:property value="#session.cartSize" default="0"/>)</a>
            <a href="<%=request.getContextPath()%>/user/logout">登出</a>
        </s:if>
        <s:else>
            <a href="<%=request.getContextPath()%>/user/register">註冊</a>
            <a href="<%=request.getContextPath()%>/user/login">登入</a>
        </s:else>
    </div>
</header>