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
<form action="${pageContext.request.contextPath }/admin/video/edit" method="post" enctype="multipart/form-data">


<label for="videoId">Video ID</label><br>
<input type="text" id="videoId" name="videoId" value="${video.videoId }"><br>


<label for="videotitle">Video Title</label><br>
<input type="text" id="videotitle" name="videotitle" value="${video.title }"><br>

<label for="videocount">Video Count</label><br>
<input type="text" id="videocount" name="videocount" value="${video.views }"><br>

<label for="category">Category</label>
<select id="category" name="category">
  <c:forEach var="item" items="${categorylist}">
    <option value="${item.categoryname}" 
      <c:if test="${item.categoryname == video.category.categoryname}">
        selected
      </c:if>
    >${item.categoryname}</option>
  </c:forEach>
</select>
<br>

<label for="status_active">Hoạt động</label>
<input type="radio" id="status_active" name="status" value="1"
  <c:if test="${video.active == 1}">
    checked
  </c:if>
>

<label for="status_inactive">Khóa</label>
<input type="radio" id="status_inactive" name="status" value="0"><br>


<label for="videodescription">Description</label><br>
<input type="text" id="videodescription" name="videodescription" value="${video.description}"><br>


<label for="videos">Videos :</label><br>
<input type="file" id="videos" name="file" onchange="chooseFileVideo(this)" value = ${video.images }><br>


<c:if test ="${video.images.substring(0,5) != 'https' }">
 <c:url value="/video?fname=${video.images}" var="videoUrl"></c:url>
</c:if>
            
<c:if test ="${video.images.substring(0,5) == 'https' }">
<c:url value="${video.images}" var="videoUrl"></c:url>
</c:if>
<video id="videoElement" width="320" height="240" controls>
<source src="${videoUrl}" type="video/mp4">
</video>
 
 
<br><input type="submit" value="Submit">
</body>
</html>