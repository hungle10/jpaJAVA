<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="topnav">
  <a class="active" href="#home">Home</a>
  <a href="#about">About</a>
  <a href="#contact">Contact</a>
  <div class="search-container">
    <form action="${pageContext.request.contextPath }/admin/video/search" method=post>
      <input type="text" placeholder="Search title.." name="search">
      <button type="submit"><i class="fa fa-search"></i></button>
    </form>
  </div>
</div>
<a href="${pageContext.request.contextPath }/admin/video/add">Add Video</a>
<table border="1" width="100%">
    <tr><!-- row -->
        <th>STT</th> <!-- column -->
        <th>Video ID</th>
        <th>Images</th>
        <th>Video title</th>
         <th>Description</th>
        <th>Views</th>
        <th>Category</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listvideo}" var="video" varStatus="STT">
        <tr>
            <td>${STT.index + 1}</td> <!-- data trong 1 ô -->
             <td>
            <c:url value="${video.videoId}"></c:url>
            </td>
            <td>
            <c:if test ="${video.images.substring(0,5) != 'https' }">
            <c:url value="/video?fname=${video.images}" var="videoUrl"></c:url>
            </c:if>
            
            <c:if test ="${video.images.substring(0,5) == 'https' }">
            <c:url value="${video.images}" var="videoUrl"></c:url>
            </c:if>
            
              <video width="500" height="400" controls>
             <source src="${videoUrl}" type="video/mp4">
            </video> 
            </td>
           
             <td>
            <c:url value="${video.title}"></c:url>
            </td>
             <td>
            <c:url value="${video.description}"></c:url>
            </td>
             <td>
            <c:url value="${video.views}"></c:url>
            </td>
            <td>
            <c:url value="${video.category.categoryname}"></c:url>
            </td>
            <td>
            <c:if test="${video.active == 1}">
            <span>Hoạt động</span>
            </c:if>
                        <c:if test="${video.active != 1}">
            <span>Khóa</span>
            </c:if>
            </td>
            <td>
            <a href="<c:url value='/admin/video/edit?videoId=${video.videoId }'/>">Sua</a>
            <a href="<c:url value='/admin/video/delete?videoId=${video.videoId }'/>">Xoa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>